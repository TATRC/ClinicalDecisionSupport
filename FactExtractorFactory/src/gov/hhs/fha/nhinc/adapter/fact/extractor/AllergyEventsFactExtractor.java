/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.AllergyFactType;
import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.ReactionFactType;
import gov.hhs.fha.nhinc.adapter.fact.xml.XMLUtils;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

/**
 *
 * @author kim
 */
public class AllergyEventsFactExtractor extends XpathFactExtractorImpl {

  private static Log log = LogFactory.getLog(AllergyEventsFactExtractor.class);
  /**
   * XPath expression to extract the total number of care events.
   */
  protected String eventsCountXpath;
  /**
   * The extractor to use for extracting reactions info
   */
  protected FactExtractor reactionExtractor;

  public AllergyEventsFactExtractor(XPath xpath) {
    super(xpath);
  }

  /**
   *
   * @param eventCountXpath
   */
  public void setEventsCountXpath(String eventCountXpath) {
    this.eventsCountXpath = eventCountXpath;
  }

  /**
   *
   * @param resultExtractor
   */
  public void setReactionExtractor(FactExtractor reactionExtractor) {
    this.reactionExtractor = reactionExtractor;
  }

  @Override
  public void extract(Object doc) throws Exception {

    //--------------------------------------------------------------------------
    // clear out fact objects list(s)
    //--------------------------------------------------------------------------
    this.deleteFacts();   

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
      log.debug("Problem events count xpath=" + this.eventsCountXpath);
      log.debug("Problem event xpath=" + this.factBaseXpath);
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
      //--------------------------------------------------------------------------

      // parse XML document to obtain only relevant node for further processing
      Node eventNode = (Node) xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
      if (eventNode != null && eventNode.hasChildNodes()) {
        if (log.isDebugEnabled()) {
          XMLUtils.printNode(eventNode, System.out);
        }

        //----------------------------------------------------------------------
        // extract allergy fact
        //----------------------------------------------------------------------
        Object allergyObj = extract(eventNode, eventIdx);

        // debug info
        if (log.isDebugEnabled()) {
          log.debug(allergyObj);
        }
        
        //----------------------------------------------------------------------
        // override default xpath expressions of reactions extractor with relevant
        // node indexing value.
        //----------------------------------------------------------------------
        tmpExpr = reactionFactXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
        ((XPathFactExtractor) reactionExtractor).setFactBaseXpath(tmpExpr);

        tmpExpr = reactionFactsCountXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
        ((ReactionFactExtractor) reactionExtractor).setFactsCountXpath(tmpExpr);
        
        //----------------------------------------------------------------------
        // extract reaction fact in this event
        //----------------------------------------------------------------------
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
        // add to facts list
        //----------------------------------------------------------------------
        if (allergyObj != null) {
          ((FactType) allergyObj).setHistorical(true);
          AllergyFactType allergyFact = (AllergyFactType) allergyObj;
          if (reactionExtractor.getFacts().size() > 0) {
            for (int i = 0; i < reactionExtractor.getFacts().size(); i++) {
              ReactionFactType reactionFact = (ReactionFactType) reactionExtractor.getFacts().get(i);
              allergyFact.getReaction().add(reactionFact);
            }
          }
            
          facts.add(allergyFact);
        }
      } else {
        log.debug("No nodes found - xpath \"" + tmpExpr + "\"!");
      }
    }
  }
}
