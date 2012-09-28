/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.VitalFactType;
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
public class VitalsFactExtractor extends XpathFactExtractorImpl {

  private static Log log = LogFactory.getLog(VitalsFactExtractor.class);
  /**
   * XPath expression to extract the total number facts.
   */
  protected String eventsCountXpath;

  public VitalsFactExtractor(XPath xpath) {
    super(xpath);
  }

  public String getEventsCountXpath() {
    return eventsCountXpath;
  }

  public void setEventsCountXpath(String factsCountXpath) {
    this.eventsCountXpath = factsCountXpath;
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
      //--------------------------------------------------------------------------

      // parse XML document to obtain only relevant node for further processing
      Node eventNode = (Node) xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
      if (eventNode != null && eventNode.hasChildNodes()) {
        if (log.isDebugEnabled()) {
          XMLUtils.printNode(eventNode, System.out);
        }

        //----------------------------------------------------------------------
        // extract vital fact
        //----------------------------------------------------------------------
        Object vitalObj = extract(eventNode, eventIdx);

        // debug info
        if (log.isDebugEnabled()) {
          log.debug(vitalObj);
        }

        //----------------------------------------------------------------------
        // add to facts list
        //----------------------------------------------------------------------
        if (vitalObj != null) {
          ((FactType) vitalObj).setHistorical(true);
          VitalFactType vitalFact = (VitalFactType) vitalObj;
          facts.add(vitalFact);
        }
      } else {
        log.debug("No nodes found - xpath \"" + tmpExpr + "\"!");
      }
    }
  }

}
