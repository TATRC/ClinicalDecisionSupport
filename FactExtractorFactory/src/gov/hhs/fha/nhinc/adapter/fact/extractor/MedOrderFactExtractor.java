/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.OrderFactType;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author kim
 */
public class MedOrderFactExtractor extends XpathFactExtractorImpl {

  //private static Log log = LogFactory.getLog(MedOrderFactExtractor.class);

  public MedOrderFactExtractor(XPath xpath) {
    super(xpath);
    log = LogFactory.getLog(MedOrderFactExtractor.class);
  }

  @Override
  public void extract(Object doc) throws Exception {

    //--------------------------------------------------------------------------
    // debug info
    //--------------------------------------------------------------------------
    log.debug("Order fact base xpath=" + factBaseXpath);

    //--------------------------------------------------------------------------
    // return due to error(s)
    //--------------------------------------------------------------------------
    if (doc == null) {
      return;
    }

    //--------------------------------------------------------------------------
    // extract base XML node
    //--------------------------------------------------------------------------
    Object node = null;
    String tmpExpr = factBaseXpath;
    if (factBaseXpath != null && !factBaseXpath.isEmpty()) {
      node = xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
    } else {
      node = doc;
    }

    //--------------------------------------------------------------------------
    // extract order fact
    //--------------------------------------------------------------------------
    if (node != null) {
      //XMLUtils.printNode((Node) node, System.out);
      OrderFactType obj = (OrderFactType) extract(node, -1);
      if (obj != null) {
        // update the label of name type code
        if (obj.getOrderingProvider() != null) {
          if (obj.getOrderingProvider().getNameType() != null) {
            String label = FactExtractorFactory.getInstance().getNameTypeLabel(obj.getOrderingProvider().getNameType().getCode());
            obj.getOrderingProvider().getNameType().setLabel(label);
          }
        }
        
        if (obj != null)
          facts.add(obj);
      }
    } else {
      log.error("Invalid XML document!");
      throw new Exception("Invalid XML document!");
    }
  }
}
