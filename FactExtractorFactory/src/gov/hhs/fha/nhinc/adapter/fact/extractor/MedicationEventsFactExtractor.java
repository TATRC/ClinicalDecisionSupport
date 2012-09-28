/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.MedicationFactType;
import gov.hhs.fha.nhinc.adapter.fact.OrderFactType;
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
public class MedicationEventsFactExtractor extends XpathFactExtractorImpl {

  private static Log log = LogFactory.getLog(MedicationEventsFactExtractor.class);
  /**
   * XPath expression to extract the total number of care events.
   */
  protected String eventsCountXpath;
  /**
   * The extractor to use for extracting test results info
   */
  protected FactExtractor orderExtractor;

  public MedicationEventsFactExtractor(XPath xpath) {
    super(xpath);
  }

  /**
   *
   * @param eventCountXpath
   */
  public void setEventsCountXpath(String eventCountXpath) {
    this.eventsCountXpath = eventCountXpath;
  }

  public void setOrderExtractor(FactExtractor extractor) {
    this.orderExtractor = extractor;
  }

  @Override
  public void extract(Object doc) throws Exception {

    //--------------------------------------------------------------------------
    // clear out fact objects list(s)
    //--------------------------------------------------------------------------
    this.deleteFacts();
    this.orderExtractor.deleteFacts();

    //--------------------------------------------------------------------------
    // return due to error(s)
    //--------------------------------------------------------------------------
    if (doc == null) {
      return;
    }

    //--------------------------------------------------------------------------
    // keep copy of original xpath expressions
    //--------------------------------------------------------------------------
    String orderXpath = ((MedOrderFactExtractor) orderExtractor).getFactBaseXpath();

    //--------------------------------------------------------------------------
    // debug info
    //--------------------------------------------------------------------------
    if (log.isDebugEnabled()) {
      log.debug("Medication events count xpath=" + this.eventsCountXpath);
      log.debug("Medication event xpath=" + this.factBaseXpath);
      log.debug("orderXpath=" + orderXpath);
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
        // extract medication fact
        //----------------------------------------------------------------------
        Object medObj = extract(eventNode, eventIdx);

        //--------------------------------------------------------------------
        // override current base xpath for order info
        //--------------------------------------------------------------------
        tmpExpr = orderXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(eventIdx));
        ((MedOrderFactExtractor) orderExtractor).setFactBaseXpath(tmpExpr);

        //--------------------------------------------------------------------
        // extract order fact in this event
        //--------------------------------------------------------------------
        orderExtractor.extract(eventNode);
        if (orderExtractor.getFacts().size() > 0) {
          if (medObj != null) {
            ((MedicationFactType) medObj).setOrder((OrderFactType) orderExtractor.getFacts().get(0));
          }
        }

        // debug info
        if (orderExtractor.getFacts().size() > 0) {
          log.debug("Medication Event #" + eventIdx + ": medication facts=" + orderExtractor.getFacts().size());
          for (int i = 0; i < orderExtractor.getFacts().size(); i++) {
            log.debug(orderExtractor.getFacts().get(i));
          }
        }

        //----------------------------------------------------------------------
        // add to facts lists
        //----------------------------------------------------------------------
        if (medObj != null) {
          ((FactType) medObj).setHistorical(true);
          facts.add(medObj);
        }

        // clear out extractor fact lists
        orderExtractor.deleteFacts();
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
