/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.AddressFactType;
import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;
import gov.hhs.fha.nhinc.adapter.fact.NameFactType;
import gov.hhs.fha.nhinc.adapter.fact.SupportContactFactType;
import gov.hhs.fha.nhinc.adapter.fact.TelecomFactType;
import gov.hhs.fha.nhinc.adapter.fact.def.SimplePropertyDefinition;
import gov.hhs.fha.nhinc.adapter.fact.utils.FactUtils;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Node;

/**
 *
 * @author kim
 */
public class SupportFactExtractor extends XpathFactExtractorImpl {

  //private static Log log = LogFactory.getLog(SupportFactExtractor.class);
  
  /**
   * XPath expression to extract the total number facts.
   */
  protected String factsCountXpath;
  /**
   * XPath expression to extract the contact type.
   */
  protected String contactTypeXpath;
  
  public SupportFactExtractor(XPath xpath) {
    super(xpath);
    log = LogFactory.getLog(SupportFactExtractor.class);
  }

  public String getFactsCountXpath() {
    return factsCountXpath;
  }

  public void setFactsCountXpath(String value) {
    this.factsCountXpath = value;
  }

  public void setContactTypeXpath(String value) {
    this.contactTypeXpath = value;
  }

  @Override
  public void extract(Object doc) throws Exception {

    //--------------------------------------------------------------------------
    // debug info
    //--------------------------------------------------------------------------
    log.debug("Base fact xpath=" + factBaseXpath);
    log.debug("Support contacts count xpath=" + factsCountXpath);
    log.debug("Support contacts xpath=" + factBaseXpath);

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
    int contacts = Integer.parseInt(val);
    log.debug("# of contacts=" + contacts);

    //--------------------------------------------------------------------------
    // iterate thru each care event
    //--------------------------------------------------------------------------
    CodeLabelPair contactType = null;
    for (int contactIdx = 1; contactIdx <= contacts; contactIdx++) {
      // locate support contact type from XML
      tmpExpr = this.contactTypeXpath;
      tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(contactIdx));
      SimplePropertyDefinition propDef = new SimplePropertyDefinition("CodedContactType", "java.lang.String", tmpExpr);
      String propValue = (String) extractPropertyValue(doc, propDef);
      
      if (propValue != null && !propValue.isEmpty()) {
        contactType = new CodeLabelPair((String) propValue, FactExtractorFactory.getInstance().getContactType(propValue), FactExtractorFactory.CONTACT_TYPE_SYSTEM, FactExtractorFactory.CONTACT_TYPE_SYSTEM_NAME);
      }
        
      tmpExpr = this.factBaseXpath;
      tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(contactIdx));

      // parse XML document to obtain only relevant node for further processing
      Node contactPartyNode = (Node) xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
      if (contactPartyNode != null) {
        //XMLUtils.printNode(contactPartyNode, ystem.out);

        //----------------------------------------------------------------------
        // extract support contact fact
        //----------------------------------------------------------------------
        SupportContactFactType obj = (SupportContactFactType) extract(contactPartyNode, contactIdx);
        
        if (obj != null) {
          obj.setCodedContactType(contactType);

          // update the label of name type code
          for (NameFactType nameFact: obj.getOtherName()) {
            if (nameFact.getNameType() != null)
              nameFact.getNameType().setLabel(nameFact.getNameType().getCode());
          }

          // set legal name
          NameFactType nameFactObj = FactUtils.getLegalName(obj.getOtherName());
          if (nameFactObj != null) {
            obj.setLegalName(nameFactObj);
            obj.getOtherName().remove(nameFactObj);
          }

          // update the label of telecom type code
          for (TelecomFactType telecomFact : obj.getTelecom()) {
            if (telecomFact.getTelecomType() != null) {
              telecomFact.getTelecomType().setLabel(FactExtractorFactory.getInstance().getTelecomUseTypes(telecomFact.getTelecomType().getCode()));
            }
          }

          // update the label of address type code
          for (AddressFactType addressFact : obj.getAddress()) {
            if (addressFact.getAddressType() != null) {
              addressFact.getAddressType().setLabel(FactExtractorFactory.getInstance().getAddressUseTypes(addressFact.getAddressType().getCode()));
            }
          }

          facts.add(obj);
        }
      }
//      else {
//        log.debug("No nodes found - xpath \"" + tmpExpr + "\"!");
//      }
    }
  }
}
