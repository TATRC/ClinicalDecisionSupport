package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for EncounterFactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncounterFactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="codedEncounterType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextEncounterType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="encounterStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="encounterStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="encounterEndTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="arrivalDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="departureDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="encounterProvider" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="codedAdmissionSource" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="freeTextAdmissionSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedAdmissionType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextAdmissionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedDischargeDisposition" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="codedPatientClass" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="patientLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceDeliveryLocationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceDeliveryLocationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codedServiceDeliveryLocation" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="reasonForVisit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncounterFactType", propOrder = {
  "patientId",
  "codedEncounterType",
  "freeTextEncounterType",
  "encounterStatus",
  "encounterStartTime",
  "encounterEndTime",
  "encounterProvider",
  "arrivalDateTime",
  "departureDateTime",
  "codedAdmissionSource",
  "freeTextAdmissionSource",
  "codedAdmissionType",
  "freeTextAdmissionType",
  "codedDischargeDisposition",
  "codedPatientClass",
  "patientLocation",
  "serviceDeliveryLocationId",
  "serviceDeliveryLocationName",
  "codedServiceDeliveryLocation",
  "reasonForVisit"
})
public class EncounterFactType
        extends FactType
        implements Serializable {

  /** uses FactType->id to carry unique encounter id */

  /**
   * Unique identifier for patient within the patient care management system.
   */
  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;

  /**
   * A code value describing the type of this encounter.
   * Note: THIS IS TO BE USE TO CAPTURE E&M CODING!!!
   * TO CAPTURE THE inpatient, outpatient ... USE codedPatientClass ELEMENT.
   */
  @XmlElement(name = "codedEncounterType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedEncounterType;

  /**
   * Free text describing this Encounter Type
   */
  @XmlElement(name = "freeTextEncounterType")
  protected String freeTextEncounterType;

  /**
   * Status of this encounter.
   * "active" - uses in notifications about an active encounter (admission/appointment)
   * "completed" - uses to notify that an encounter has ended normally
   * "aborted" - uses to notify that an encounter was aborted prior to normal completion.
   * "new" - uses to notify that a new admission or appointment has or will occur.
   */
  @XmlElement(name = "encounterStatus", required = true)
  protected String encounterStatus;

  /**
   * Start date and time of the Encounter, including duration if pertinent.
   */
  @XmlElement(name = "encounterStartTime", required = true, type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date encounterStartTime;

  /**
   * End date and time of the Encounter, including duration if pertinent.
   */
  @XmlElement(name = "encounterEndTime", required = true, type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date encounterEndTime;

  /**
   * Name of the person or organization who performed or hosted this encounter.
   */
  @XmlElement(name = "encounterProvider", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType encounterProvider;

  /**
   * A code indicating the source of the admission (e.g., Referral, Transfer, et cetera).
   */
  @XmlElement(name = "codedAdmissionSource", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedAdmissionSource;

  /**
   * Free text description of the source of the admission (e.g., Referral, Transfer, et cetera).
   */
  @XmlElement(name = "freeTextAdmissionSource")
  protected String freeTextAdmissionSource;

  /**
   * A code (from National Uniform Billing Committee) indicating the priority of the admission
   * (e.g., Emergency, Urgent, Elective, et cetera).
   */
  @XmlElement(name = "codedAdmissionType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedAdmissionType;

  /**
   * Free text description of the priority of the admission.
   */
  @XmlElement(name = "freeTextAdmissionType")
  protected String freeTextAdmissionType;

  /**
   * A code value describes the discharge Disposition (sometimes called “Discharge Status”) is the person‟s
   * anticipated location or status following the encounter (e.g. death, transfer to home/hospice/snf/AMA).
   */
  @XmlElement(name = "codedDischargeDisposition", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedDischargeDisposition;

  /**
   * A code value categorizes patient by the site where the encounter occurred , e.g., Emergency, Inpatient, or Outpatient.
   */
  @XmlElement(name = "codedPatientClass", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedPatientClass;

  /**
   * Patient's assigned location(s) during the encounter, e.g. patient's residence, clinic, hospital, work site.
   */
  @XmlElement(name = "patientLocation", required = true)
  protected String patientLocation;

  /**
   * Indicates the rationale for this encounter.
   */
  @XmlElement(name = "reasonForVisit", required = true)
  protected String reasonForVisit;

  /**
   * The date and time the patient arrived or admitted at the location.
   */
  @XmlElement(name = "arrivalDateTime", type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date arrivalDateTime;

  /**
   * The date and time of the patient discharge or depart from the location.
   */
  @XmlElement(name = "departureDateTime", type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date departureDateTime;

  /**
   * Identifier of the service delivery location where this encounter took place.
   */
  @XmlElement(name = "serviceDeliveryLocationId", required = true)
  protected String serviceDeliveryLocationId;

  /**
   * The name of the service delivery location.
   */
  @XmlElement(name = "serviceDeliveryLocationName", required = true)
  protected String serviceDeliveryLocationName;

  /**
   * A code value describes this Service delivery location type.
   */
  @XmlElement(name = "codedServiceDeliveryLocation", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedServiceDeliveryLocation;

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
   * Sets the value of the patientId property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setPatientId(ValueType patientId) {
    this.patientId = patientId;
  }

  /**
   * Gets the value of the codedEncounterType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedEncounterType() {
    return codedEncounterType;
  }

  /**
   * Sets the value of the codedEncounterType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCodedEncounterType(CodeLabelPair value) {
    this.codedEncounterType = value;
  }

  /**
   * Gets the value of the freeTextEncounterType property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextEncounterType() {
    return freeTextEncounterType;
  }

  /**
   * Sets the value of the freeTextEncounterType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextEncounterType(String value) {
    this.freeTextEncounterType = value;
  }

  /**
   * Gets the value of the encounterStatus property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getEncounterStatus() {
    return encounterStatus;
  }

  /**
   * Sets the value of the v property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setEncounterStatus(String value) {
    this.encounterStatus = value;
  }

  /**
   * Gets the value of the encounterStartTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getEncounterStartTime() {
    return encounterStartTime;
  }

  /**
   * Sets the value of the encounterStartTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Integer }
   *
   */
  public void setEncounterStartTime(Date value) {
    this.encounterStartTime = value;
  }

  /**
   * Gets the value of the encounterEndTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getEncounterEndTime() {
    return encounterEndTime;
  }

  /**
   * Sets the value of the encounterEndTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setEncounterEndTime(Date value) {
    this.encounterEndTime = value;
  }

  /**
   * Gets the value of the arrivalDateTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getArrivalDateTime() {
    return arrivalDateTime;
  }

  /**
   * Sets the value of the arrivalDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setArrivalDateTime(Date value) {
    this.arrivalDateTime = value;
  }

  /**
   * Gets the value of the departureDateTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getDepartureDateTime() {
    return departureDateTime;
  }

  /**
   * Sets the value of the departureDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setDepartureDateTime(Date value) {
    this.departureDateTime = value;
  }

  /**
   * Gets the value of the encounterProvider property.
   *
   * @return
   *     possible object is
   *     {@link NameFactType }
   *
   */
  public NameFactType getEncounterProvider() {
    return encounterProvider;
  }

  /**
   * Sets the value of the encounterProvider property.
   *
   * @param value
   *     allowed object is
   *     {@link NameFactType }
   *
   */
  public void setEncounterProvider(NameFactType value) {
    this.encounterProvider = value;
  }

  /**
   * Gets the value of the codedAdmissionSource property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedAdmissionSource() {
    return codedAdmissionSource;
  }

  /**
   * Sets the value of the codedAdmissionSource property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedAdmissionSource(CodeLabelPair value) {
    this.codedAdmissionSource = value;
  }

  /**
   * Gets the value of the freeTextAdmissionSource property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextAdmissionSource() {
    return freeTextAdmissionSource;
  }

  /**
   * Sets the value of the freeTextAdmissionSource property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextAdmissionSource(String value) {
    this.freeTextAdmissionSource = value;
  }

  /**
   * Gets the value of the codedAdmissionType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedAdmissionType() {
    return codedAdmissionType;
  }

  /**
   * Sets the value of the codedAdmissionType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedAdmissionType(CodeLabelPair value) {
    this.codedAdmissionType = value;
  }

  /**
   * Gets the value of the freeTextAdmissionType property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextAdmissionType() {
    return freeTextAdmissionType;
  }

  /**
   * Sets the value of the freeTextAdmissionType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextAdmissionType(String value) {
    this.freeTextAdmissionType = value;
  }

  /**
   * Gets the value of the codedDischargeDisposition property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedDischargeDisposition() {
    return codedDischargeDisposition;
  }

  /**
   * Sets the value of the codedDischargeDisposition property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedDischargeDisposition(CodeLabelPair value) {
    this.codedDischargeDisposition = value;
  }

  /**
   * Gets the value of the codedPatientClass property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedPatientClass() {
    return codedPatientClass;
  }

  /**
   * Sets the value of the codedPatientClass property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedPatientClass(CodeLabelPair value) {
    this.codedPatientClass = value;
  }

  /**
   * Gets the value of the patientLocation property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPatientLocation() {
    return patientLocation;
  }

  /**
   * Sets the value of the patientLocation property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPatientLocation(String value) {
    this.patientLocation = value;
  }

  /**
   * Gets the value of the reasonForVisit property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getReasonForVisit() {
    return reasonForVisit;
  }

  /**
   * Sets the value of the reasonForVisit property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setReasonForVisit(String value) {
    this.reasonForVisit = value;
  }

  /**
   * Gets the value of the serviceDeliveryLocationId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getServiceDeliveryLocationId() {
    return serviceDeliveryLocationId;
  }

  /**
   * Sets the value of the serviceDeliveryLocationId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setServiceDeliveryLocationId(String value) {
    this.serviceDeliveryLocationId = value;
  }

  /**
   * Gets the value of the serviceDeliveryLocationName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getServiceDeliveryLocationName() {
    return serviceDeliveryLocationName;
  }

  /**
   * Sets the value of the serviceDeliveryLocationName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setServiceDeliveryLocationName(String value) {
    this.serviceDeliveryLocationName = value;
  }

  /**
   * Gets the value of the codedServiceDeliveryLocation property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedServiceDeliveryLocation() {
    return codedServiceDeliveryLocation;
  }

  /**
   * Sets the value of the codedServiceDeliveryLocation property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedServiceDeliveryLocation(CodeLabelPair value) {
    this.codedServiceDeliveryLocation = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("EncounterFactType(encounter id=" + id + "|patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  codedEncounterType=" + (codedEncounterType != null ? codedEncounterType : "") + System.getProperty("line.separator"));
    str.append("  freeTextEncounterType=" + freeTextEncounterType + System.getProperty("line.separator"));
    str.append("  encounterStatus=" + encounterStatus + System.getProperty("line.separator"));
    str.append("  encounterStartTime=" + encounterStartTime + "|encounterEndTime=" + encounterEndTime + System.getProperty("line.separator"));
    str.append("  encounterProvider=" + (encounterProvider != null ? encounterProvider : "") + System.getProperty("line.separator"));
    str.append("  arrivalDateTime=" + arrivalDateTime + "|departureDateTime=" + departureDateTime + System.getProperty("line.separator"));
    str.append("  codedAdmissionSource=" + (codedAdmissionSource != null ? codedAdmissionSource : "") + System.getProperty("line.separator"));
    str.append("  freeTextAdmissionSource=" + freeTextAdmissionSource + System.getProperty("line.separator"));
    str.append("  codedAdmissionType=" + (codedAdmissionType != null ? codedAdmissionType : "") + System.getProperty("line.separator"));
    str.append("  freeTextAdmissionType=" + freeTextAdmissionType + System.getProperty("line.separator"));
    str.append("  codedDischargeDisposition=" + (codedDischargeDisposition != null ? codedDischargeDisposition : "") + System.getProperty("line.separator"));
    str.append("  codedPatientClass=" + (codedPatientClass != null ? codedPatientClass : "") + System.getProperty("line.separator"));
    str.append("  serviceDeliveryLocationId=" + serviceDeliveryLocationId + "|serviceDeliveryLocationName=" + serviceDeliveryLocationName + System.getProperty("line.separator"));
    str.append("  codedServiceDeliveryLocation=" + (codedServiceDeliveryLocation != null ? codedServiceDeliveryLocation : "") + System.getProperty("line.separator"));
    str.append("  reasonForVisit=" + reasonForVisit);
    str.append("]");

    return str.toString();
  }
}
