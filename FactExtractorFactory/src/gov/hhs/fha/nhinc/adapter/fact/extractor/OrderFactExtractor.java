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
public class OrderFactExtractor extends XpathFactExtractorImpl {

    public static final String ORDER_TYPE_EXPR = "hl7:act/hl7:code/@code";
    public static final String ORDER_TYPE_MED = "MEDOE";
    public static final String ORDER_TYPE_LAB = "LABOE";

    protected static Log log = LogFactory.getLog(OrderFactExtractor.class);

    /**
     * XPath expression to extract the total number facts.
     */
    protected String factsCountXpath;

    protected GenericFactExtractor labOrderExtractor;
    protected GenericFactExtractor medOrderExtractor;

    public OrderFactExtractor(XPath xpath) {
        super(xpath);
    }

    public String getFactsCountXpath() {
        return factsCountXpath;
    }

    public void setFactsCountXpath(String factsCountXpath) {
        this.factsCountXpath = factsCountXpath;
    }

    public void setLabOrderExtractor(GenericFactExtractor labOrderExtractor) {
        this.labOrderExtractor = labOrderExtractor;
    }

    public void setMedOrderExtractor(GenericFactExtractor medOrderExtractor) {
        this.medOrderExtractor = medOrderExtractor;
    }

    @Override
    public void extract(Object doc) throws Exception {

        //--------------------------------------------------------------------------
        // Pull out fact name
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

                //Check why type of order this is
                expr = xpath.compile(ORDER_TYPE_EXPR);
                val = expr.evaluate(factNode);

                GenericFactExtractor subExtractor = null;
                if (ORDER_TYPE_MED.equals(val)) {
                    subExtractor = medOrderExtractor;
                }
                else if (ORDER_TYPE_LAB.equals(val)) {
                    subExtractor = labOrderExtractor;
                }

                if (subExtractor == null) {
                    log.error("Unknown order type: " + val);
                    continue;
                }

                //Call extractor for appropriate order type
                subExtractor.setFactBaseXpath(tmpExpr);
                subExtractor.setFactsCountXpath("1");
                subExtractor.extract(factNode);
                facts.addAll(subExtractor.getFacts());

            } else {
                log.debug("No nodes found - xpath \"" + tmpExpr + "\"!");
            }
        }
    }
}
