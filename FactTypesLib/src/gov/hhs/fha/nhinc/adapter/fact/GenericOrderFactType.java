package gov.hhs.fha.nhinc.adapter.fact;



import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for GenericOrderFactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenericOrderFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="patient" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="patientClass" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="orderGroupNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="placerOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fillerOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderPriority" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="orderStartDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="orderEffeciveTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="orderExpirationDateTime" type="{http://www.w3.org/2001/XMLSchema}datetime" minOccurs="0"/>
 *         &lt;element name="orderingProvider" type="{urn:gov:hhs:fha:nhinc:adapter:fact}PersonFactType"/>
 *         &lt;element name="orderEnteredBy" type="{urn:gov:hhs:fha:nhinc:adapter:fact}PersonFactType" minOccurs="0"/>
 *         &lt;element name="orderVerifiedBy" type="{urn:gov:hhs:fha:nhinc:adapter:fact}PersonFactType" minOccurs="0"/>
 *         &lt;element name="orderSettingType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericOrderFactType", propOrder = {
  "patientId",
  "patient",
  "patientClass",
  "orderGroupNumber",
  "parentOrderNumber",
  "placerOrderNumber",
  "fillerOrderNumber",
  "orderStatus",
  "orderPriority",
  "orderDateTime",
  "orderStartDateTime",
  "orderExpirationDateTime",
  "orderingProvider",
  "orderEnteredBy",
  "orderVerifiedBy",
  "orderSettingType"
})
@XmlSeeAlso({
    LabOrderFactType.class
})
public class GenericOrderFactType
        extends FactType
        implements Serializable
{

  /**
   * Unique identifier of patient within the clinical system.
   */
  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;

  /**
   * Name of patient.
   */
  @XmlElement(name = "patient", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType patient;
  
  /** [PV1.2] Uses to categorize patient by site where encounter occurred. Value set derived
      from 2.2.3.9.5 of HITSP C80.
      OID: 2.16.840.1.113883.3.88.12.80.66 (Patient Class)
      Values:  EMER - emergency, IMP - inpatient encounter, AMB - Ambulatory  */
  @XmlElement(name = "patientClass", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair patientClass;

  /** !!! NOT IMPLEMENTED YET !!!
      A code indicating the priority of the admission (e.g., Emergency, Urgent, Elective, et cetera).
      Conditional required (if patientClass = IMP)  */
  //@XmlElement(name = "admissionType")
  //protected String admissionType;
  
  /** [ORC.4] An order group is a list of orders associated with an -placer group number  */
  @XmlElement(name = "orderGroupNumber")
  protected String orderGroupNumber;

  /** [ORC.8, OBR.29] The Order number of the Parent Order which may have spawned this order  */
  @XmlElement(name = "parentOrderNumber")
  protected String parentOrderNumber;
  
  /**
   * Report the status of an order either upon request or when the status changes [HITSP C154/24.02] [ORC.5].
   */
  @XmlElement(name = "orderStatus")
  protected String orderStatus;
  
  /** [ORC.9] The date and time of the order transaction  */
  @XmlElement(name = "orderDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date orderDateTime;

  /**
   * The date/time when the ordering provider is requesting the execution of orders [HITSP C154/24.08][ORC.15].
   */
  @XmlElement(name = "orderStartDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date orderStartDateTime;

  /**
   * The expiration date and time of the order [HITSP C154/24.08][ORC.15].
   */
  @XmlElement(name = "orderExpirationDateTime", type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date orderExpirationDateTime;

  /** 
   * [ORC.10] The identity of the person who actually keyed the request into the order application
   * (may include both a name and/or an identifier)
   */
  @XmlElement(name = "orderEnteredBy", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected PersonFactType orderEnteredBy;

  /**
   * [ORC.11] The identity of the person who verified the accuracy of the entered request
   * may include both a name and/or an identifier)
   */
  @XmlElement(name = "orderVerifiedBy", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected PersonFactType orderVerifiedBy;

  /**
   * Indicates the care setting in which the order is executed [HITSP C154/24.07] [ORC.29].
   * Value set derived from 2.2.3.6.9 of HITSP C80 and HL7 Table 0482 in Chapter 4, Section 4.5.1.29
   * of HL7 2.5.1 specifications.
   * OID: 2.16.840.1.113883.12.482 (Order Type), Values:  I - inpatient order, O - outpatient order.
   */
  @XmlElement(name = "orderSettingType", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair orderSettingType;

  /**
   * The priority of the order [HITSP C154/24.09] [TQ1.9].
   * A code (e.g., for routine, emergency), specifying the urgency under which the Act
   * happened, can happen, is happening, is intended to happen, or is requested/demanded to happen.
   * Value set derived from 2.2.3.6.10 of HITSP C80.
   * OID: 2.16.840.1.113883.3.88.12.80.71 (Order Priority)
   * Values: S - STAT (highest priority, A - ASAP, R - Routine, P - Preop, C - Callback, T - Timing critical.
   */
  @XmlElement(name = "orderPriority", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair orderPriority;
  
  /** [OBR.2] A unique order identifier (across all orders) from the perspective of
      the system placing the order  */
  @XmlElement(name = "placerOrderNumber", required = true)
  protected String placerOrderNumber;
  
  /** [OBR.3] A unique order identifier (across all orders) from the perspective of
      the system fulfilling the order  */
  @XmlElement(name = "fillerOrderNumber")
  protected String fillerOrderNumber;
    
  /**
   * [OBR.16] c.
   */
  @XmlElement(name = "orderingProvider", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected PersonFactType orderingProvider;

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
   * Gets the value of the patientId property.
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
   * Gets the value of the patient property.
   *
   * @return
   *     possible object is
   *     {@link PersonFactType }
   *
   */
  public NameFactType getPatient() {
    return patient;
  }

  /**
   * Sets the value of the patient property.
   *
   * @param value
   *     allowed object is
   *     {@link NameFactType }
   *
   */
  public void setPatient(NameFactType value) {
    this.patient = value;
  }

  /**
   * Gets the value of the placerOrderNumber property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPlacerOrderNumber() {
    return placerOrderNumber;
  }

  /**
   * Sets the value of the placerOrderNumber property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPlacerOrderNumber(String value) {
    this.placerOrderNumber = value;
  }

  /**
   * Gets the value of the fillerOrderNumber property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFillerOrderNumber() {
    return fillerOrderNumber;
  }

  /**
   * Sets the value of the fillerOrderNumber property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFillerOrderNumber(String value) {
    this.fillerOrderNumber = value;
  }

  /**
   * Gets the value of the parentOrderNumber property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getParentOrderNumber() {
    return parentOrderNumber;
  }

  /**
   * Sets the value of the parentOrderNumber property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setParentOrderNumber(String value) {
    this.parentOrderNumber = value;
  }

  /**
   * Gets the value of the orderGroupNumber property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getOrderGroupNumber() {
    return orderGroupNumber;
  }

  /**
   * Sets the value of the orderGroupNumber property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setOrderGroupNumber(String value) {
    this.orderGroupNumber = value;
  }

  /**
   * Gets the value of the patientClass property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getPatientClass() {
    return patientClass;
  }

  /**
   * Sets the value of the patientClass property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setPatientClass(CodeLabelPair value) {
    this.patientClass = value;
  }

  /**
   * Gets the value of the orderPriority property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getOrderPriority() {
    return orderPriority;
  }

  /**
   * Sets the value of the orderPriority property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setOrderPriority(CodeLabelPair value) {
    this.orderPriority = value;
  }

  /**
   * Gets the value of the orderSettingType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getOrderSettingType() {
    return orderSettingType;
  }

  /**
   * Sets the value of the orderSettingType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setOrderSettingType(CodeLabelPair value) {
    this.orderSettingType = value;
  }

  /**
   * Gets the value of the orderDateTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getOrderDateTime() {
    return orderDateTime;
  }

  /**
   * Sets the value of the orderDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setOrderDateTime(Date value) {
    this.orderDateTime = value;
  }

  /**
   * Gets the value of the orderingProvider property.
   *
   * @return
   *     possible object is
   *     {@link PersonFactType }
   *
   */
  public PersonFactType getOrderingProvider() {
    return orderingProvider;
  }

  /**
   * Sets the value of the orderingProvider property.
   *
   * @param value
   *     allowed object is
   *     {@link PersonFactType }
   *
   */
  public void setOrderingProvider(PersonFactType value) {
    this.orderingProvider = value;
  }

  /**
   * Gets the value of the orderEnteredBy property.
   *
   * @return
   *     possible object is
   *     {@link PersonFactType }
   *
   */
  public PersonFactType getOrderEnteredBy() {
    return orderingProvider;
  }

  /**
   * Sets the value of the orderEnteredBy property.
   *
   * @param value
   *     allowed object is
   *     {@link PersonFactType }
   *
   */
  public void setOrderEnteredBy(PersonFactType value) {
    this.orderEnteredBy = value;
  }

  /**
   * Gets the value of the orderVerifiedBy property.
   *
   * @return
   *     possible object is
   *     {@link PersonFactType }
   *
   */
  public PersonFactType getOrderVerifiedBy() {
    return orderVerifiedBy;
  }

  /**
   * Sets the value of the orderVerifiedBy property.
   *
   * @param value
   *     allowed object is
   *     {@link PersonFactType }
   *
   */
  public void setOrderVerifiedBy(PersonFactType value) {
    this.orderVerifiedBy = value;
  }

  /**
   * Gets the value of the orderStatus property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getOrderStatus() {
    return orderStatus;
  }

  /**
   * Sets the value of the orderStatus property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setOrderStatus(String value) {
    this.orderStatus = value;
  }

  /**
   * Gets the value of the orderStartDateTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getOrderStartDateTime() {
    return orderStartDateTime;
  }

  /**
   * Sets the value of the orderStartDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setOrderStartDateTime(Date value) {
    this.orderStartDateTime = value;
  }

  /**
   * Gets the value of the orderExpirationDateTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getOrderExpirationDateTime() {
    return orderExpirationDateTime;
  }

  /**
   * Sets the value of the orderExpirationDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setOrderExpirationDateTime(Date value) {
    this.orderExpirationDateTime = value;
  }
  
  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("GenericOrderFactType(");
    str.append(super.toString());
    str.append(System.getProperty("line.separator"));
    str.append("  patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  patient=" + patient + System.getProperty("line.separator"));
    str.append("  patientClass=" + patientClass + System.getProperty("line.separator"));
    str.append("  orderGroupNumber=" + orderGroupNumber + System.getProperty("line.separator"));
    str.append("  parentOrderNumber=" + parentOrderNumber + System.getProperty("line.separator"));
    str.append("  placerOrderNumber=" + placerOrderNumber + System.getProperty("line.separator"));
    str.append("  fillerOrderNumber=" + fillerOrderNumber + System.getProperty("line.separator"));
    str.append("  orderStatus=" + orderStatus + System.getProperty("line.separator"));
    str.append("  orderPriority=" + orderPriority + System.getProperty("line.separator"));
    str.append("  orderDateTime=" + orderDateTime + System.getProperty("line.separator"));
    str.append("  orderStartDateTime=" + orderStartDateTime + System.getProperty("line.separator"));
    str.append("  orderExpirationDateTime=" + orderExpirationDateTime + System.getProperty("line.separator"));
    str.append("  orderEnteredBy=" + orderEnteredBy + System.getProperty("line.separator"));
    str.append("  orderVerifiedBy=" + orderVerifiedBy + System.getProperty("line.separator"));
    str.append("  orderingProvider=" + orderingProvider + System.getProperty("line.separator"));
    str.append("  orderSettingType=" + orderSettingType + System.getProperty("line.separator"));
    str.append(")");

    return str.toString();
  }
}
