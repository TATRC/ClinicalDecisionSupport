/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.extractor;

import gov.hhs.fha.nhinc.adapter.fact.AddressFactType;
import gov.hhs.fha.nhinc.adapter.fact.NameFactType;
import gov.hhs.fha.nhinc.adapter.fact.PersonFactType;
import gov.hhs.fha.nhinc.adapter.fact.SupportContactFactType;
import gov.hhs.fha.nhinc.adapter.fact.TelecomFactType;
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
public class PersonFactExtractor extends XpathFactExtractorImpl {

  //private static Log log = LogFactory.getLog(PersonFactExtractor.class);
  /**
   * XPath expression to extract the total number of patients.
   */
  protected String patientsCountXpath;
  /**
   * The extractor to use for extracting test results info
   */
  protected FactExtractor supportExtractor;

  public PersonFactExtractor(XPath xpath) {
    super(xpath);
    log = LogFactory.getLog(PersonFactExtractor.class);
  }

  public void setPatientsCountXpath(String patientsCountXpath) {
    this.patientsCountXpath = patientsCountXpath;
  }

  public void setSupportExtractor(FactExtractor extractor) {
    this.supportExtractor = extractor;
  }

  @Override
  public void extract(Object doc) throws Exception {

    //--------------------------------------------------------------------------
    // clear out fact objects list(s)
    //--------------------------------------------------------------------------
    this.deleteFacts();
    supportExtractor.deleteFacts();

    //--------------------------------------------------------------------------
    // return due to error(s)
    //--------------------------------------------------------------------------
    if (doc == null) {
      return;
    }

    //--------------------------------------------------------------------------
    // keep copy of original xpath expressions
    //--------------------------------------------------------------------------
    String supportContactXpath = ((SupportFactExtractor) supportExtractor).getFactBaseXpath();

    //--------------------------------------------------------------------------
    // debug info
    //--------------------------------------------------------------------------
    if (log.isDebugEnabled()) {
      log.debug("Demographics xpath=" + this.factBaseXpath);
      log.debug("Demographics count xpath=" + this.patientsCountXpath);
    }

    //--------------------------------------------------------------------------
    // extract the total number of patients from XML document.
    //--------------------------------------------------------------------------
    String tmpExpr = patientsCountXpath;
    XPathExpression expr = xpath.compile(tmpExpr);

    String val = expr.evaluate(doc);
    int patients = Integer.parseInt(val);
    log.debug("# of patients=" + patients);

    //--------------------------------------------------------------------------
    // iterate thru each patient`
    //--------------------------------------------------------------------------
    for (int personIdx = 1; personIdx <= patients; personIdx++) {
      tmpExpr = this.factBaseXpath;
      tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(personIdx));

      // parse XML document to obtain only relevant node for further processing
      Node ptNode = (Node) xpath.evaluate(tmpExpr, doc, XPathConstants.NODE);
      if (ptNode != null && ptNode.hasChildNodes()) {
//        if (log.isDebugEnabled()) {
//          XMLUtils.printNode(ptNode, System.out);
//        }

        //--------------------------------------------------------------------------
        // extract demographics fact
        //--------------------------------------------------------------------------
        PersonFactType personObj = (PersonFactType) extract(ptNode, personIdx);

        // add to facts list
        if (personObj != null) {
          personObj.setHistorical(true);

          // update the label of name type code
          for (NameFactType nameFact : personObj.getOtherName()) {
            if (nameFact.getNameType() != null) {
              nameFact.getNameType().setLabel(FactExtractorFactory.getInstance().getNameTypeLabel(nameFact.getNameType().getCode()));
            }
          }

          // set legal name
          NameFactType nameFactObj = FactUtils.getLegalName(personObj.getOtherName());
          if (nameFactObj != null) {
            personObj.setLegalName(nameFactObj);
            personObj.getOtherName().remove(nameFactObj);
          }

          // update the label of telecom type code
          for (TelecomFactType telecomFact : personObj.getTelecom()) {
            if (telecomFact.getTelecomType() != null) {
              telecomFact.getTelecomType().setLabel(FactExtractorFactory.getInstance().getTelecomUseTypes(telecomFact.getTelecomType().getCode()));
            }
          }

          // update the label of address type code
          for (AddressFactType addressFact : personObj.getAddress()) {
            if (addressFact.getAddressType() != null) {
              addressFact.getAddressType().setLabel(FactExtractorFactory.getInstance().getAddressUseTypes(addressFact.getAddressType().getCode()));
            }
          }

          facts.add(personObj);

          // debug info
          //log.debug(personObj);
        }

        //----------------------------------------------------------------------
        // override current base xpath for lab test info
        //----------------------------------------------------------------------
        tmpExpr = supportContactXpath;
        tmpExpr = tmpExpr.replace(this.factIndexCharset, String.valueOf(personIdx));
        ((SupportFactExtractor) supportExtractor).setFactBaseXpath(tmpExpr);

        //--------------------------------------------------------------------------
        // extract support contact fact(s)
        //--------------------------------------------------------------------------
        supportExtractor.extract(ptNode);

        if (supportExtractor.getFacts().size() > 0) {
          for (int i = 0; i < supportExtractor.getFacts().size(); i++) {
            SupportContactFactType supportFactObj = (SupportContactFactType) supportExtractor.getFacts().get(i);

            // add patient that associated with this contact party
            supportFactObj.setContactOfPatient(personObj.getId().get(0));

            // debug info
            log.debug("Support Contact #" + i + ": " + supportFactObj);

            // add to facts list
            facts.add(supportFactObj);
          }
        }

      } //end if has child nodes

      supportExtractor.deleteFacts();
    } //end loop through patients

  }

}
