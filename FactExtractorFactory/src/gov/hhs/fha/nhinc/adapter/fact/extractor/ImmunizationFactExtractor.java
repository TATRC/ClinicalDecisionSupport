/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.ImmunizationFactType;
import gov.hhs.fha.nhinc.adapter.fact.MedicationFactType;
import gov.hhs.fha.nhinc.adapter.fact.OrderFactType;
import gov.hhs.fha.nhinc.adapter.fact.ReactionFactType;
import gov.hhs.fha.nhinc.adapter.fact.xml.XMLUtils;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author 
 */
public class ImmunizationFactExtractor extends XpathFactExtractorImpl {

   private static Log log = LogFactory.getLog(ImmunizationFactExtractor.class);
   /**
    * XPath expression to extract the total number of care events.
    */
   protected String eventsCountXpath;
   /**
    * The extractor to use for extracting test results info
    */
   protected FactExtractor reactionExtractor;

   public ImmunizationFactExtractor(XPath xpath) {
      super(xpath);
   }

   /**
    *
    * @param eventCountXpath
    */
   public void setEventsCountXpath(String eventCountXpath) {
      this.eventsCountXpath = eventCountXpath;
   }

   public void setReactionExtractor(FactExtractor extractor) {
      this.reactionExtractor = extractor;
   }

   @Override
   public void extract(Object doc) throws Exception {

      //--------------------------------------------------------------------------
      // clear out fact objects list(s)
      //--------------------------------------------------------------------------
      this.deleteFacts();
      this.reactionExtractor.deleteFacts();

      //--------------------------------------------------------------------------
      // return due to error(s)
      //--------------------------------------------------------------------------
      if (doc == null) {
         return;
      }

      //--------------------------------------------------------------------------
      // keep copy of original xpath expressions
      //--------------------------------------------------------------------------
      String reactionFactXpath = ((XPathFactExtractor) reactionExtractor).getFactBaseXpath();
      String reactionFactsCountXpath = ((ReactionFactExtractor) reactionExtractor).getFactsCountXpath();

      //--------------------------------------------------------------------------
      // debug info
      //--------------------------------------------------------------------------
      if (log.isDebugEnabled()) {
         log.debug("Immunization events count xpath=" + this.eventsCountXpath);
         log.debug("Immunization event xpath=" + this.factBaseXpath);
         log.debug("Reaction xpath=" + reactionFactXpath);
         log.debug("Reactions count xpath=" + reactionFactsCountXpath);
      }

      //--------------------------------------------------------------------------
      // extract the total number of care events from XML document.
      //--------------------------------------------------------------------------
      String tmpExpr = eventsCountXpath;
      XPathExpression expr = xpath.compile(tmpExpr);

      String val = expr.evaluate(doc);
      int events = Integer.parseInt(val);
      log.debug("# of care events=" + events);

      //--------------------------------------------------------------------------
      // iterate thru each care event
      //--------------------------------------------------------------------------
      for (int eventIdx = 1; eventIdx <= events; eventIdx++) {
         tmpExpr = this.factBaseXpath;
         tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));

         // parse XML document to obtain only relevant node for further processing
         Node eventNode = (Node) xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
         if (eventNode != null && eventNode.hasChildNodes()) {
            if (log.isDebugEnabled()) {
               XMLUtils.printNode(eventNode, System.out);
            }

            //----------------------------------------------------------------------
            // extract Immunization fact
            //----------------------------------------------------------------------
            Object immuneObj = extract(eventNode, eventIdx);

            //--------------------------------------------------------------------
            // override current base xpath for reaction info
            //--------------------------------------------------------------------
            tmpExpr = reactionFactXpath;
            tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
            ((XPathFactExtractor) reactionExtractor).setFactBaseXpath(tmpExpr);

            tmpExpr = reactionFactsCountXpath;
            tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
            ((ReactionFactExtractor) reactionExtractor).setFactsCountXpath(tmpExpr);

            //--------------------------------------------------------------------
            // extract reaction fact in this event
            //--------------------------------------------------------------------
            reactionExtractor.deleteFacts();
            reactionExtractor.extract(eventNode);

            // debug info
            if (log.isDebugEnabled()) {
               log.debug("Event #" + eventIdx + ":reaction facts=" + reactionExtractor.getFacts().size());
               for (int i = 0; i < reactionExtractor.getFacts().size(); i++) {
                  log.debug(reactionExtractor.getFacts().get(i));
               }
            }

            //----------------------------------------------------------------------
            // add to facts lists
            //----------------------------------------------------------------------
            if (immuneObj != null) {
               ((FactType) immuneObj).setHistorical(true);

               ImmunizationFactType immuneFact = (ImmunizationFactType) immuneObj;
               if (reactionExtractor.getFacts().size() > 0) {
                  for (int i = 0; i < reactionExtractor.getFacts().size(); i++) {
                     ReactionFactType reactionFact = (ReactionFactType) reactionExtractor.getFacts().get(i);
                     immuneFact.getReaction().add(reactionFact);
                  }
               }
               facts.add(immuneObj);
            }
         } else {
            log.debug("No nodes found - xpath \"" + tmpExpr + "\"!");
         }
      }

      //----------------------------------------------------------------------
      // debug info
      //----------------------------------------------------------------------
      for (int i = 0; i < getFacts().size(); i++) {
         log.debug(getFacts().get(i));
      }
   }
}
