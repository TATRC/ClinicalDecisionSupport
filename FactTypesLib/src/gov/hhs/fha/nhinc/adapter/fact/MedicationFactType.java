package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for MedicationFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="MedicationFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="freeTextProductName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="freeTextBrandName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedProductName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="codedBrandName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="drugManufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productConcentration" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedTypeOfMedication" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="codedStatusOfMedication" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="sigFreeText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedSigRoute" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="sigDose" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair" minOccurs="0"/>
 *         &lt;element name="sigFrequency" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair" minOccurs="0"/>
 *         &lt;element name="sigMedicationStopped" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="freeTextIndication" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientInstructions" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reaction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sigCodedVehicle" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sigDoseIndicator" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="order" type="{urn:gov:hhs:fha:nhinc:adapter:fact}OrderFactType"/>
 *         &lt;element name="fulfillment" type="{urn:gov:hhs:fha:nhinc:adapter:fact}FulfillmentFactType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MedicationFactType", propOrder = {
  "patientId",
  "freeTextProductName",
  "freeTextBrandName",
  "codedProductName",
  "codedBrandName",
  "drugManufacturer",
  "productConcentration",
  "codedTypeOfMedication",
  "codedStatusOfMedication",
  "sigFreeText",
  "codedSigRoute",
  "sigDose",
  "sigFrequency",
  "sigMedicationStopped",
  "freeTextIndication",
  "patientInstructions",
  "reaction",
  "sigCodedVehicle",
  "sigDoseIndicator",
  "order",
  "fulfillment"
})
public class MedicationFactType
        extends FactType
        implements Serializable
{

  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  @XmlElement(name = "freeTextProductName", required = true)
  protected String freeTextProductName;
  @XmlElement(name = "freeTextBrandName")
  protected String freeTextBrandName;
  @XmlElement(name = "codedProductName", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedProductName;
  @XmlElement(name = "codedBrandName", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedBrandName;
  @XmlElement(name = "drugManufacturer")
  protected String drugManufacturer;
  @XmlElement(name = "productConcentration")
  protected String productConcentration;
  @XmlElement(name = "codedTypeOfMedication", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedTypeOfMedication;
  @XmlElement(name = "codedStatusOfMedication", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedStatusOfMedication;
  @XmlElement(name = "sigFreeText")
  protected String sigFreeText;
  @XmlElement(name = "codedSigRoute", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedSigRoute;
  @XmlElement(name = "sigDose", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair sigDose;
  @XmlElement(name = "sigFrequency", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair sigFrequency;
  @XmlElement(name = "sigMedicationStopped")
  protected String sigMedicationStopped;
  @XmlElement(name = "freeTextIndication")
  protected String freeTextIndication;
  @XmlElement(name = "patientInstructions")
  protected String patientInstructions;
  @XmlElement(name = "reaction")
  protected String reaction;
  @XmlElement(name = "sigCodedVehicle")
  protected List<CodeLabelPair> sigCodedVehicle;
  @XmlElement(name = "sigDoseIndicator")
  protected List<String> sigDoseIndicator;
  @XmlElement(name = "order", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected OrderFactType order;
  @XmlElement(name = "fulfillment", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<FulfillmentFactType> fulfillment;


  /**
   * Sets the value of the patientId property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setPatientId(ValueType value) {
    this.patientId = value;
  }

  /**
   * Gets the value of the freeTextProduct property.
   *
   * @return
   *     possible object is
   *     {@link ValueType }
   *
   */
  public ValueType getPatientId() {
    return patientId;
  }
  
  /**
   * Gets the value of the freeTextProductName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextProductName() {
    return freeTextProductName;
  }

  /**
   * Sets the value of the freeTextProductName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextProductName(String value) {
    this.freeTextProductName = value;
  }

  /**
   * Gets the value of the freeTextBrandName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextBrandName() {
    return freeTextBrandName;
  }

  /**
   * Sets the value of the freeTextBrandName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextBrandName(String value) {
    this.freeTextBrandName = value;
  }

  /**
   * Gets the value of the codedProductName property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedProductName() {
    return codedProductName;
  }

  /**
   * Sets the value of the codedProductName property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedProductName(CodeLabelPair value) {
    this.codedProductName = value;
  }

  /**
   * Gets the value of the codedBrandName property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedBrandName() {
    return codedBrandName;
  }

  /**
   * Sets the value of the codedBrandName property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedBrandName(CodeLabelPair value) {
    this.codedBrandName = value;
  }

  /**
   * Gets the value of the drugManufacturer property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getDrugManufacturer() {
    return drugManufacturer;
  }

  /**
   * Sets the value of the drugManufacturer property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setDrugManufacturer(String value) {
    this.drugManufacturer = value;
  }

  /**
   * Gets the value of the productConcentration property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getProductConcentration() {
    return productConcentration;
  }

  /**
   * Sets the value of the productConcentration property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setProductConcentration(String value) {
    this.productConcentration = value;
  }

  /**
   * Gets the value of the codedTypeOfMedication property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedTypeOfMedication() {
    return codedTypeOfMedication;
  }

  /**
   * Sets the value of the codedTypeOfMedication property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedTypeOfMedication(CodeLabelPair value) {
    this.codedTypeOfMedication = value;
  }

  /**
   * Gets the value of the codedStatusOfMedication property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedStatusOfMedication() {
    return codedStatusOfMedication;
  }

  /**
   * Sets the value of the codedStatusOfMedication property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedStatusOfMedication(CodeLabelPair value) {
    this.codedStatusOfMedication = value;
  }

  /**
   * Gets the value of the sigFreeText property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSigFreeText() {
    return sigFreeText;
  }

  /**
   * Sets the value of the sigFreeText property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setSigFreeText(String value) {
    this.sigFreeText = value;
  }

  /**
   * Gets the value of the codedSigRoute property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedSigRoute() {
    return codedSigRoute;
  }

  /**
   * Sets the value of the codedSigRoute property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedSigRoute(CodeLabelPair value) {
    this.codedSigRoute = value;
  }

  /**
   * Gets the value of the sigDose property.
   *
   * @return
   *     possible object is
   *     {@link ValueUnitPair }
   *
   */
  public ValueUnitPair getSigDose() {
    return sigDose;
  }

  /**
   * Sets the value of the sigDose property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueUnitPair }
   *
   */
  public void setSigDose(ValueUnitPair value) {
    this.sigDose = value;
  }

  /**
   * Gets the value of the sigFrequency property.
   *
   * @return
   *     possible object is
   *     {@link ValueUnitPair }
   *
   */
  public ValueUnitPair getSigFrequency() {
    return sigFrequency;
  }

  /**
   * Sets the value of the sigFrequency property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueUnitPair }
   *
   */
  public void setSigFrequency(ValueUnitPair value) {
    this.sigFrequency = value;
  }

  /**
   * Gets the value of the sigMedicationStopped property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSigMedicationStopped() {
    return sigMedicationStopped;
  }

  /**
   * Sets the value of the sigMedicationStopped property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setSigMedicationStopped(String value) {
    this.sigMedicationStopped = value;
  }
  
  /**
   * Gets the value of the freeTextIndication property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextIndication() {
    return freeTextIndication;
  }

  /**
   * Sets the value of the freeTextIndication property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextIndication(String value) {
    this.freeTextIndication = value;
  }

  /**
   * Gets the value of the patientInstructions property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPatientInstructions() {
    return patientInstructions;
  }

  /**
   * Sets the value of the patientInstructions property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPatientInstructions(String value) {
    this.patientInstructions = value;
  }

  /**
   * Gets the value of the reaction property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getReaction() {
    return reaction;
  }

  /**
   * Sets the value of the reaction property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setReaction(String value) {
    this.reaction = value;
  }

  /**
   * Gets the value of the sigCodedVehicle property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the sigCodedVehicle property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getSigCodedVehicle().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link CodeLabelPair }
   *
   *
   */
  public List<CodeLabelPair> getSigCodedVehicle() {
    if (sigCodedVehicle == null) {
      sigCodedVehicle = new ArrayList<CodeLabelPair>();
    }
    return this.sigCodedVehicle;
  }

  /**
   * Gets the value of the sigDoseIndicator property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the sigDoseIndicator property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getSigDoseIndicator().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link String }
   *
   *
   */
  public List<String> getSigDoseIndicator() {
    if (sigDoseIndicator == null) {
      sigDoseIndicator = new ArrayList<String>();
    }
    return this.sigDoseIndicator;
  }

//  /**
//   * Gets the value of the administrationInfo property.
//   *
//   * @return
//   *     possible object is
//   *     {@link AdministrationInfoType }
//   *
//   */
//  public AdministrationFactType getAdministrationInfo() {
//    return administrationInfo;
//  }
//
//  /**
//   * Sets the value of the administrationInfo property.
//   *
//   * @param value
//   *     allowed object is
//   *     {@link AdministrationInfoType }
//   *
//   */
//  public void setAdministrationInfo(AdministrationFactType value) {
//    this.administrationInfo = value;
//  }

  /**
   * Gets the value of the order property.
   *
   * @return
   *     possible object is
   *     {@link OrderFactType }
   *
   */
  public OrderFactType getOrder() {
    return order;
  }

  /**
   * Sets the value of the order property.
   *
   * @param value
   *     allowed object is
   *     {@link OrderFactType }
   *
   */
  public void setOrder(OrderFactType value) {
    this.order = value;
  }

  /**
   * Gets the value of the fullfillment property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the fullfillment property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getFullfillment().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link FullfillmentFactType }
   *
   *
   */
  public List<FulfillmentFactType> getFulfillment() {
    if (fulfillment == null) {
      fulfillment = new ArrayList<FulfillmentFactType>();
    }
    return this.fulfillment;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  public void setFulfillment(ArrayList<FulfillmentFactType> value) {
    this.fulfillment = value;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("MedicationFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  freeTextProductName=" + freeTextProductName + System.getProperty("line.separator"));
    str.append("  freeTextBrandName=" + freeTextBrandName + System.getProperty("line.separator"));
    str.append("  codedProductName=" + (codedProductName != null ? codedProductName : "") + System.getProperty("line.separator"));
    str.append("  codedTypeOfMedication=" + (codedTypeOfMedication != null ? codedTypeOfMedication : "") + System.getProperty("line.separator"));
    str.append("  codedStatusOfMedication=" + (codedStatusOfMedication != null ? codedStatusOfMedication : "") + System.getProperty("line.separator"));
    str.append("  sigFreeText=" + sigFreeText + System.getProperty("line.separator"));
    str.append("  codedSigRoute=" + (codedSigRoute != null ? codedSigRoute : "") + System.getProperty("line.separator"));
    str.append("  sigDose=" + (sigDose != null ? sigDose : "") + ", sigFrequency=" + (sigFrequency != null ? sigFrequency : "") + System.getProperty("line.separator"));
    str.append("  sigMedicationStopped=" + sigMedicationStopped + System.getProperty("line.separator"));
    str.append("  order=" + (order != null ? order : "") + System.getProperty("line.separator"));
    if (fulfillment != null) {
      for (Object fact: fulfillment.toArray())
      str.append(fact + System.getProperty("line.separator"));
    }
    str.append("]");

    return str.toString();
  }
}
