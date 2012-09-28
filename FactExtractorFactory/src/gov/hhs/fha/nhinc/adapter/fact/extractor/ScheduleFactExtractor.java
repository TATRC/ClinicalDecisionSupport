/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.SlotFactType;
import gov.hhs.fha.nhinc.adapter.fact.TelecomFactType;
import gov.hhs.fha.nhinc.adapter.fact.xml.XMLUtils;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

/**
 *
 * @author kim
 */
public class ScheduleFactExtractor extends XpathFactExtractorImpl {

  //private static Log log = LogFactory.getLog(ProviderFactExtractor.class);
  /**
   * XPath expression to extract the total number of appointment slots.
   */
  protected String slotsCountXpath;

  public ScheduleFactExtractor(XPath xpath) {
    super(xpath);
    log = LogFactory.getLog(ScheduleFactExtractor.class);
  }

  public void setSlotsCountXpath(String slotsCountXpath) {
    this.slotsCountXpath = slotsCountXpath;
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

    if (log.isDebugEnabled()) {
      log.debug("Slots xpath=" + this.factBaseXpath);
      log.debug("Slots count xpath=" + this.slotsCountXpath);
    }

    //--------------------------------------------------------------------------
    // extract the total number of patients from XML document.
    //--------------------------------------------------------------------------
    String tmpExpr = slotsCountXpath;
    XPathExpression expr = xpath.compile(tmpExpr);

    String val = expr.evaluate(doc);
    int slots = Integer.parseInt(val);
    log.debug("# of slots=" + slots);

    //--------------------------------------------------------------------------
    // iterate thru each appointment slot`
    //--------------------------------------------------------------------------
    for (int slotIdx = 1; slotIdx <= slots; slotIdx++) {
      tmpExpr = this.factBaseXpath;
      tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(slotIdx));

      // parse XML document to obtain only relevant node for further processing
      Node ptNode = (Node) xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
      if (ptNode != null && ptNode.hasChildNodes()) {
        if (log.isDebugEnabled()) {
          XMLUtils.printNode(ptNode, System.out);
        }

        // extract schedule fact
        SlotFactType obj = (SlotFactType) extract(ptNode, slotIdx);

        // add to facts list
        if (obj != null) {
          obj.setHistorical(true);

          // update the label of name type code
          if (obj.getPerformer().getNameType() != null) {
              obj.getPerformer().getNameType().setLabel(
                FactExtractorFactory.getInstance().getNameTypeLabel(obj.getPerformer().getNameType().getCode()));
          }

          // update the label of telecom type code
          for (TelecomFactType telecomFact : obj.getPerformerTelecom()) {
            if (telecomFact.getTelecomType() != null) {
              telecomFact.getTelecomType().setLabel(FactExtractorFactory.getInstance().getTelecomUseTypes(telecomFact.getTelecomType().getCode()));
            }
          }

          facts.add(obj);

          // debug info
          log.debug(obj);
        }

      } //end if has child nodes

    } //end loop through patients

  }

}
