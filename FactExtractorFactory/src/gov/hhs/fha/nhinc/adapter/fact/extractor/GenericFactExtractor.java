/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.xml.XMLUtils;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

/**
 *
 * @author chrisjan
 */
public class GenericFactExtractor extends XpathFactExtractorImpl {

    protected static Log log = LogFactory.getLog(GenericFactExtractor.class);

    /**
     * XPath expression to extract the total number facts.
     */
    protected String factsCountXpath;

    public GenericFactExtractor(XPath xpath) {
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
        // Pull out fact name for debugging
        //--------------------------------------------------------------------------
        String factName;
        int nameIdx = factClassName.lastIndexOf('.');
        if (nameIdx == -1) {
            factName = factClassName;
        }
        else {
            factName = factClassName.substring(nameIdx+1);
        }

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
            log.debug(factName + " count xpath=" + this.factsCountXpath);
            log.debug(factName + " xpath=" + this.factBaseXpath);
        }

        //--------------------------------------------------------------------------
        // extract the total number of facts from XML document.
        //--------------------------------------------------------------------------
        String tmpExpr = factsCountXpath;
        XPathExpression expr = xpath.compile(tmpExpr);

        String val = expr.evaluate(doc);
        int factCount = Integer.parseInt(val);
        log.debug("# of " + factName + "=" + factCount);

        //--------------------------------------------------------------------------
        // iterate thru each fact
        //--------------------------------------------------------------------------
        for (int factIdx = 1; factIdx <= factCount; factIdx++) {
            tmpExpr = this.factBaseXpath;
            tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(factIdx));
            //--------------------------------------------------------------------------

            // parse XML document to obtain only relevant node for further processing
            Node factNode = (Node) xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
            if (factNode != null && factNode.hasChildNodes()) {
                if (log.isDebugEnabled()) {
                    XMLUtils.printNode(factNode, System.out);
                }

                //----------------------------------------------------------------------
                // extract procedure fact
                //----------------------------------------------------------------------
                Object factObj = extract(factNode, factIdx);

                // debug info
                if (log.isDebugEnabled()) {
                    log.debug(factObj);
                }

                //----------------------------------------------------------------------
                // add to facts list
                //----------------------------------------------------------------------
                if (factObj != null) {
                    ((FactType) factObj).setHistorical(true);
                    facts.add(factObj);
                }
            } else {
                log.debug("No nodes found - xpath \"" + tmpExpr + "\"!");
            }
        }
    }
}
