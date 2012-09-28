package gov.hhs.fha.nhinc.adapter.fact;



import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for LabOrderFactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabOrderFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}GenericOrderFactType">
 *       &lt;sequence>
 *         &lt;element name="orderCode" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="specimenAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabOrderFactType", propOrder = {
  "orderCode",
  "specimenAction"
})
public class LabOrderFactType
        extends GenericOrderFactType
        implements Serializable
{

  /**
   * [HITSP C154/24.09] [OBR.4]
   *
   * The order code for the requested observation, test, and/or battery. This
   * can be based on local and/or standardized (LOINC order codes) order codes
   */
  @XmlElement(name = "orderCode", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair orderCode;

  /** [OBR.4] The order code for the requested observation, test, and/or battery. This
      can be based on local and/or standardized (LOINC order codes) order codes  */
  //@XmlElement(name = "orderCode", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  //protected CodeLabelPair orderCode;
  
  /** Identifies the action to be taken with respect to the specimens that accompany or precede this order  */
  @XmlElement(name = "specimenAction")
  protected String specimenAction;
  
  /** !!! NOT IMPLEMENTED YET !!!
      [OBR.10] The person, department, or facility that collected the specimen may include
      both a name and an identifier)  */
  //@XmlElement(name = "specimenCollector")
  //protected PersonFactType specimentCollector;
  
  /** !!! NOT IMPLEMENTED YET !!!
      [OBR.28] The person, department, or facility that collected the specimen (may include
      both a name and an identifier).  */
  //@XmlElement(name = "resultsDistributionList")
  //protected List<PersonFactType> resultsDistributionList;


  /**
   * Gets the value of the orderCode property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
//  public CodeLabelPair getOrderCode() {
//    return orderCode;
//  }

  /**
   * Sets the value of the orderCode property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
//  public void setOrderCode(CodeLabelPair value) {
//    this.orderCode = value;
//  }

  /**
   * Gets the value of the specimenAction property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSpecimenAction() {
    return specimenAction;
  }

  /**
   * Sets the value of the specimenAction property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setSpecimenAction(String value) {
    this.specimenAction = value;
  }


  /**
   * Gets the value of the orderCode property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getOrderCode() {
    return orderCode;
  }

  /**
   * Sets the value of the orderCode property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setOrderCode(CodeLabelPair value) {
    this.orderCode = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("LabOrderFactType(");
    str.append(super.toString());
    str.append(System.getProperty("line.separator"));
    str.append("  orderCode=" + orderCode + System.getProperty("line.separator"));
    str.append("  specimenAction=" + specimenAction + System.getProperty("line.separator"));
    str.append(")");

    return str.toString();
  }
}
