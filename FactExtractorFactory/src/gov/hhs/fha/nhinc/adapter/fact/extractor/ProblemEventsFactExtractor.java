/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.NameFactType;
import gov.hhs.fha.nhinc.adapter.fact.ProblemFactType;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

/**
 *
 * @author kim
 */
public class ProblemEventsFactExtractor extends XpathFactExtractorImpl {

  //private static Log log = LogFactory.getLog(ProblemEventsFactExtractor.class);
  /**
   * XPath expression to extract the total number of care events.
   */
  protected String eventsCountXpath;

  public ProblemEventsFactExtractor(XPath xpath) {
    super(xpath);
    log = LogFactory.getLog(ProblemEventsFactExtractor.class);
  }

  /**
   *
   * @param eventCountXpath
   */
  public void setEventsCountXpath(String eventCountXpath) {
    this.eventsCountXpath = eventCountXpath;
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
    // debug info
    //--------------------------------------------------------------------------
    if (log.isDebugEnabled()) {
      log.debug("Problem events count xpath=" + this.eventsCountXpath);
      log.debug("Problem event xpath=" + this.factBaseXpath);
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
        //if (log.isDebugEnabled())
        //  XMLUtils.printNode(eventNode, System.out);

        //----------------------------------------------------------------------
        // extract medication fact
        //----------------------------------------------------------------------
        Object problemObj = extract(eventNode, eventIdx);

        //----------------------------------------------------------------------
        // debug info
        //----------------------------------------------------------------------
//        if (log.isDebugEnabled())
//          log.debug(problemObj);

        //----------------------------------------------------------------------
        // add to facts list
        //----------------------------------------------------------------------
        if (problemObj != null) {
          ((FactType) problemObj).setHistorical(true);

          // update the label of name type code
          for (NameFactType nameFact : ((ProblemFactType) problemObj).getTreatingProvider()) {
            if (nameFact.getNameType() != null) {
              nameFact.getNameType().setLabel(FactExtractorFactory.getInstance().getNameTypeLabel(nameFact.getNameType().getCode()));
            }
          }

          facts.add(problemObj);
        }
      }
//      else {
//        log.debug("No nodes found - xpath \"" + tmpExpr + "\"!");
//      }
    }
  }
}
