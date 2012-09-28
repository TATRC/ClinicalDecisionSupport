/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.NameFactType;
import gov.hhs.fha.nhinc.adapter.fact.ResultFactType;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author kim
 */
public class ResultFactExtractor extends XpathFactExtractorImpl {

  //private static Log log = LogFactory.getLog(ResultFactExtractor.class);
  /**
   * XPath expression to extract the total number facts.
   */
  protected String factsCountXpath;

  public ResultFactExtractor(XPath xpath) {
    super(xpath);
    log = LogFactory.getLog(ResultFactExtractor.class);
  }

  public String getFactsCountXpath() {
    return factsCountXpath;
  }

  public void setFactsCountXpath(String factsCountXpath) {
    this.factsCountXpath = factsCountXpath;
  }

  @Override
  public void extract(Object doc) throws Exception {

    //--------------------------------------------------------------------------
    // debug info
    //--------------------------------------------------------------------------
    log.debug("(results) base fact xpath=" + factBaseXpath);
    log.debug("results count xpath=" + factsCountXpath);

    //--------------------------------------------------------------------------
    // return due to error(s)
    //--------------------------------------------------------------------------
    if (factsCountXpath == null || factsCountXpath.isEmpty() ||
            doc == null) {
      return;
    }

    //--------------------------------------------------------------------------
    // extract the total number of results from XML document.
    //--------------------------------------------------------------------------
    String tmpExpr = factsCountXpath;
    XPathExpression expr = xpath.compile(tmpExpr);
    String val = expr.evaluate(doc);
    int results = Integer.parseInt(val);
    log.debug("# of results=" + results);

    //--------------------------------------------------------------------------
    // extract base XML node
    //--------------------------------------------------------------------------
    Object node = null;
    if (factBaseXpath != null && !factBaseXpath.isEmpty()) {
      // parse XML document to obtain only requested nodes for further processing
      node = xpath.evaluate(factBaseXpath, doc, XPathConstants.NODE);
    } else {
      node = doc;
    }

    //--------------------------------------------------------------------------
    // iterate thru each result
    //--------------------------------------------------------------------------
    if (node != null) {
//      if (log.isDebugEnabled()) {
//        XMLUtils.printNode((Node) node, System.out);
//      }

      for (int resultIdx = 1; resultIdx <= results; resultIdx++) {
        ResultFactType resultObj = (ResultFactType) extract(node, resultIdx);
        if (resultObj != null) {
          // update the label of name type code
          if (resultObj.getOrderingProvider() != null) {
            resultObj.getOrderingProvider().getNameType().setLabel(
                    FactExtractorFactory.getInstance().getNameTypeLabel(resultObj.getOrderingProvider().getNameType().getCode()));
          }
          facts.add(resultObj);
        }
      }
    } else {
      log.error("Invalid XML document!");
      throw new Exception("Invalid XML document!");
    }
  }
}
