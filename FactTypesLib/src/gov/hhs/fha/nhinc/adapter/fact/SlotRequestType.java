package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for SlotRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SlotRequestType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}RequestType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="patient" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="slotId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scheduleId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chiefComplaint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="appointmentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="duration" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair" minOccurs="0"/>
 *         &lt;element name="priorityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="author" type="{urn:gov:hhs:fha:nhinc:adapter:fact}PersonFactType"/>
 *         &lt;element name="serviceDeliveryLocationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="freeTextServiceDeliveryLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedServiceDeliveryLocation" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="performerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="performer" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" minOccurs="0"/>
 *         &lt;element name="performerRole" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SlotRequestType", propOrder = {
    "patientId",
    "patient",
    "slotId",
    "scheduleId",
    "chiefComplaint",
    "appointmentType",
    "startDateTime",
    "endDateTime",
    "duration",
    "priorityCode",
    "orderNumber",
    "author",
    "serviceDeliveryLocationId",
    "freeTextServiceDeliveryLocation",
    "codedServiceDeliveryLocation",
    "performerId",
    "performer",
    "codedPerformerRole"
})
public class SlotRequestType
        extends RequestType
        implements Serializable {

  /** [PID] patient information */
  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  @XmlElement(name = "patient", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType patient;
  /** [ARQ-1] identifier of the appointment slot */
  @XmlElement(name = "slotId", required = true)
  protected String slotId;
  /** [ARQ-5] schedule identifier */
  @XmlElement(name = "scheduleId", required = true)
  protected String scheduleId;
  /** [ARQ-7] reason for appointment */
  @XmlElement(name = "chiefComplaint", required = true)
  protected String chiefComplaint;
  /** [ARQ-8] appointment type requesting */
  @XmlElement(name = "appointmentType", required = true)
  protected String appointmentType;
  /** [ARQ-9] appointment duration, must be a positive number (non-zero).
  [ARQ-10] appointment duration units, default to "min" ((minutes) */
  @XmlElement(name = "duration")
  protected ValueUnitPair duration;
  /** [ARQ-11] requested appointment start date/time of the appointment/schedule */
  @XmlElement(name = "startDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date startDateTime;
  /** [ARQ-11] requested appointment end date/time of the appointment/schedule */
  @XmlElement(name = "endDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date endDateTime;
  /** [ARQ-12] the urgency of this request */
  @XmlElement(name = "priorityCode")
  protected String priorityCode;
  /** [ARQ-15] the person responsible for requesting the scheduling of the appointment */
  @XmlElement(name = "author", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact", required = true)
  protected PersonFactType author;
  /** [ARQ-24] order number associated with this request */
  @XmlElement(name = "orderNumber")
  protected String orderNumber;
  /** identifier of service delivery location associated with this schedule */
  @XmlElement(name = "serviceDeliveryLocationId", required = true)
  protected String serviceDeliveryLocationId;
  /** free text name of service delivery location associated with this schedule */
  @XmlElement(name = "freeTextServiceDeliveryLocation")
  protected String freeTextServiceDeliveryLocation;
  /** Coded role of service delivery location associated with this schedule */
  @XmlElement(name = "codedServiceDeliveryLocation", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedServiceDeliveryLocation;
  /** identifier of person who will be providing service */
  @XmlElement(name = "performerId", required = true)
  protected String performerId;
  /** name of provider who will be providing service in this appointment/schedule */
  @XmlElement(name = "performer", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType performer;
  /** Coded role of person who will be providing service in this appointment/schedule */
  @XmlElement(name = "codedPerformerRole", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedPerformerRole;


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
   *     {@link NameFactType }
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
  public void setPatient(NameFactType patient) {
    this.patient = patient;
  }

  /**
   * Gets the value of the slotId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSlotId() {
    return slotId;
  }

  /**
   * Sets the value of the slotId property.
   *
   * @param value
   *     allowed object is
   *     {@link String
   *
   */
  public void setSlotId(String slotId) {
    this.slotId = slotId;
  }

  /**
   * Gets the value of the scheduleId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getScheduleId() {
    return scheduleId;
  }

  /**
   * Sets the value of the scheduleId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setScheduleId(String scheduleId) {
    this.scheduleId = scheduleId;
  }

  /**
   * Gets the value of the chiefComplaint property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getChiefComplaint() {
    return chiefComplaint;
  }

  /**
   * Sets the value of the chiefComplaint property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setChiefComplaint(String chiefComplaint) {
    this.chiefComplaint = chiefComplaint;
  }

  /**
   * Gets the value of the appointmentType property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getAppointmentType() {
    return appointmentType;
  }

  /**
   * Sets the value of the appointmentType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setAppointmentType(String appointmentType) {
    this.appointmentType = appointmentType;
  }

  /**
   * Gets the value of the priorityCode property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPriorityCode() {
    return priorityCode;
  }

  /**
   * Sets the value of the priorityCode property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPriorityCode(String priorityCode) {
    this.priorityCode = priorityCode;
  }

  /**
   * Gets the value of the orderNumber property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getOrderNumber() {
    return orderNumber;
  }

  /**
   * Sets the value of the orderNumber property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setOrderNumber(String value) {
    this.orderNumber = value;
  }

  /**
   * Gets the value of the author property.
   *
   * @return
   *     possible object is
   *     {@link PersonFactType }
   *
   */
  public PersonFactType getAuthor() {
    return author;
  }

  /**
   * Sets the value of the author property.
   *
   * @param value
   *     allowed object is
   *     {@link PersonFactType }
   *
   */
  public void setAuthor(PersonFactType value) {
    this.author = value;
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
  public void setServiceDeliveryLocationId(String serviceDeliveryLocationId) {
    this.serviceDeliveryLocationId = serviceDeliveryLocationId;
  }

  /**
   * Gets the value of the freeTextServiceDeliveryLoc property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextServiceDeliveryLocation() {
    return freeTextServiceDeliveryLocation;
  }

  /**
   * Sets the value of the freeTextServiceDeliveryLoc property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextServiceDeliveryLocation(String value) {
    this.freeTextServiceDeliveryLocation = value;
  }

  /**
   * Gets the value of the duration property.
   *
   * @return
   *     possible object is
   *     {@link ValueUnitPair }
   *
   */
  public ValueUnitPair getDuration() {
    return duration;
  }

  /**
   * Sets the value of the duration property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueUnitPair }
   *
   */
  public void setDuration(ValueUnitPair value) {
    this.duration = value;
  }

  /**
   * Gets the value of the startDateTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getStartDateTime() {
    return startDateTime;
  }

  /**
   * Sets the value of the startDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setStartDateTime(Date value) {
    this.startDateTime = value;
  }

  /**
   * Gets the value of the endDateTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getEndDateTime() {
    return endDateTime;
  }

  /**
   * Sets the value of the endDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setEndDateTime(Date value) {
    this.endDateTime = value;
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

  /**
   * Gets the value of the performer property.
   *
   * @return
   *     possible object is
   *     {@link NameFactType }
   *
   */
  public NameFactType getPerformer() {
    return performer;
  }

  /**
   * Sets the value of the performer property.
   *
   * @param value
   *     allowed object is
   *     {@link NameFactType }
   *
   */
  public void setPerformer(NameFactType performer) {
    this.performer = performer;
  }

  /**
   * Gets the value of the performerId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPerformerId() {
    return performerId;
  }

  /**
   * Sets the value of the performer property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPerformerId(String performerId) {
    this.performerId = performerId;
  }

  /**
   * Gets the value of the codedPerformerRole property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedPerformerRole() {
    return codedPerformerRole;
  }

  /**
   * Sets the value of the codedPerformerRole property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedPerformerRole(CodeLabelPair value) {
    this.codedPerformerRole = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("SlotRequestType(scheduleId=" + scheduleId + ",orderNumber=" + orderNumber);
    str.append(",patient=" + patient + ")");

    return str.toString();
  }
}
