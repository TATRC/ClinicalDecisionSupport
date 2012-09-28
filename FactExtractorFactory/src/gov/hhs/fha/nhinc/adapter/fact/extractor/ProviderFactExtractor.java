/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.AddressFactType;
import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;
import gov.hhs.fha.nhinc.adapter.fact.HealthcareProviderFactType;
import gov.hhs.fha.nhinc.adapter.fact.NameFactType;
import gov.hhs.fha.nhinc.adapter.fact.TelecomFactType;
import gov.hhs.fha.nhinc.adapter.fact.ValueType;
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
public class ProviderFactExtractor extends XpathFactExtractorImpl {

  //private static Log log = LogFactory.getLog(ProviderFactExtractor.class);
  /**
   * XPath expression to extract the total number of providers.
   */
  protected String providersCountXpath;

  public ProviderFactExtractor(XPath xpath) {
    super(xpath);
    log = LogFactory.getLog(ProviderFactExtractor.class);
  }

  public void setProvidersCountXpath(String providersCountXpath) {
    this.providersCountXpath = providersCountXpath;
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
      log.debug("Providers xpath=" + this.factBaseXpath);
      log.debug("Providers count xpath=" + this.providersCountXpath);
    }

    //--------------------------------------------------------------------------
    // extract the total number of patients from XML document.
    //--------------------------------------------------------------------------
    String tmpExpr = providersCountXpath;
    XPathExpression expr = xpath.compile(tmpExpr);

    String val = expr.evaluate(doc);
    int providers = Integer.parseInt(val);
    log.debug("# of providers=" + providers);

    //--------------------------------------------------------------------------
    // iterate thru each provider`
    //--------------------------------------------------------------------------
    for (int providerIdx = 1; providerIdx <= providers; providerIdx++) {
      tmpExpr = this.factBaseXpath;
      tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(providerIdx));

      // parse XML document to obtain only relevant node for further processing
      Node ptNode = (Node) xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
      if (ptNode != null && ptNode.hasChildNodes()) {
//        if (log.isDebugEnabled()) {
//          XMLUtils.printNode(ptNode, System.out);
//        }

        //--------------------------------------------------------------------------
        // extract provider fact
        //--------------------------------------------------------------------------
        HealthcareProviderFactType providerObj = (HealthcareProviderFactType) extract(ptNode, providerIdx);

        // add to facts list
        if (providerObj != null) {
          providerObj.setHistorical(true);

          // update the label of name type code
          for (NameFactType nameFact : providerObj.getOtherName()) {
            if (nameFact.getNameType() != null) {
              nameFact.getNameType().setLabel(FactExtractorFactory.getInstance().getNameTypeLabel(nameFact.getNameType().getCode()));
            }
          }

          // set legal name
          NameFactType nameFactObj = FactUtils.getLegalName(providerObj.getOtherName());
          if (nameFactObj != null) {
            providerObj.setLegalName(nameFactObj);
            providerObj.getOtherName().remove(nameFactObj);
          }

          // set national provider id
          ValueType npiObj = FactUtils.getNPI(providerObj.getId());
          if (npiObj != null) {
              providerObj.setNationalProviderId(npiObj);
              providerObj.getId().remove(npiObj);
          }

          // set free text type if not provided
          if ((providerObj.getFreeTextProviderType() == null) || (providerObj.getFreeTextProviderType().isEmpty())) {
              CodeLabelPair pType = providerObj.getCodedProviderType();
              if (pType != null) {
                providerObj.setFreeTextProviderType(pType.getLabel());
              }
          }

          // update the label of telecom type code
          for (TelecomFactType telecomFact : providerObj.getTelecom()) {
            if (telecomFact.getTelecomType() != null) {
              telecomFact.getTelecomType().setLabel(FactExtractorFactory.getInstance().getTelecomUseTypes(telecomFact.getTelecomType().getCode()));
            }
          }

          // update the label of address type code
          for (AddressFactType addressFact : providerObj.getAddress()) {
            if (addressFact.getAddressType() != null) {
              addressFact.getAddressType().setLabel(FactExtractorFactory.getInstance().getAddressUseTypes(addressFact.getAddressType().getCode()));
            }
          }
          facts.add(providerObj);

          // debug info
          log.debug(providerObj);
        }

      } //end if has child nodes

    } //end loop through patients

  }

}
