/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author kim
 */
public class LabTestFactExtractor extends XpathFactExtractorImpl {

  private static Log log = LogFactory.getLog(LabTestFactExtractor.class);

  public LabTestFactExtractor(XPath xpath) {
    super(xpath);
  }

  @Override
  public void extract(Object doc) throws Exception {

    //--------------------------------------------------------------------------
    // debug info
    //--------------------------------------------------------------------------
    log.debug("Lab Test fact base xpath=" + factBaseXpath);

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
    }
    else
      node = doc;

    //--------------------------------------------------------------------------
    // extract lab test fact
    //--------------------------------------------------------------------------
    if (node != null) {
      //XMLUtils.printNode((Node) node, System.out);
      Object obj = extract(node, -1);
      if (obj != null) {
          facts.add(obj);
        }
    } else {
      log.error("Invalid XML document!");
      throw new Exception("Invalid XML document!");
    }
  }
}
