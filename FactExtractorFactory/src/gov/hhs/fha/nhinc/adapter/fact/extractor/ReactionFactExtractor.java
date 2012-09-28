/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

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
 * @author kim
 */
public class ReactionFactExtractor extends XpathFactExtractorImpl {

  private static Log log = LogFactory.getLog(ReactionFactExtractor.class);
  /**
   * XPath expression to extract the total number facts.
   */
  protected String factsCountXpath;

  public ReactionFactExtractor(XPath xpath) {
    super(xpath);
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
    log.debug("Base fact xpath=" + factBaseXpath);
    log.debug("reactions count xpath=" + factsCountXpath);
    log.debug("reaction xpath=" + factBaseXpath);

    //--------------------------------------------------------------------------
    // return due to error(s)
    //--------------------------------------------------------------------------
    if (factsCountXpath == null || factsCountXpath.isEmpty() ||
            doc == null) {
      return;
    }

    //--------------------------------------------------------------------------
    // Extract the total number of "sourceOf" elements from XML document. Because
    // reaction has the same typeCode (MFST) as product, we have to do some special
    // processing here.
    //--------------------------------------------------------------------------
    String tmpExpr = factsCountXpath;
    XPathExpression expr = xpath.compile(tmpExpr);
    String val = expr.evaluate(doc);
    int results = Integer.parseInt(val);
    log.debug("# of \"sourceOf\" elements=" + results);

    //--------------------------------------------------------------------------
    // extract base XML node
    //--------------------------------------------------------------------------
    Node node = null;
    boolean isReactionNode = false;
    if (factBaseXpath != null && !factBaseXpath.isEmpty()) {
      for (int sourceOfIdx = 1; sourceOfIdx <= results; sourceOfIdx++) {
        tmpExpr = factBaseXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(sourceOfIdx));
        
        // parse XML document to obtain only requested node for further processing
        node = (Node) xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
        XMLUtils.printNode((Node) node, System.out);

        //----------------------------------------------------------------------
        // If there is a child node of type "substanceAdministration", then this 
        // is not the sourceOf Node that we want.
        //----------------------------------------------------------------------
        isReactionNode = false;
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength() && !isReactionNode; i++) {
          Node tmpNode = nodes.item(i);
          //System.out.println("node name = " + tmpNode.getNodeName());
          if (tmpNode.getNodeName().contains("observation")) {
            isReactionNode = true;
            break;
          }
          else {
            isReactionNode = false;
          }
        }

        //--------------------------------------------------------------------
        // Found a reaction node, process it
        //--------------------------------------------------------------------
        if (isReactionNode) {
          Object obj = extract(node, sourceOfIdx);
          //log.debug(obj);
          if (obj != null) {
            facts.add(obj);
          }
        }
      }
    } else {
      log.error("Invalid XML document!");
      throw new Exception("Invalid XML document!");
    }
  }
}
