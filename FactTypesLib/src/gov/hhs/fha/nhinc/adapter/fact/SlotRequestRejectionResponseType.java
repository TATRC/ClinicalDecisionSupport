package gov.hhs.fha.nhinc.adapter.fact;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for SlotRequestRejectionResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SlotRequestRejectionResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="patient" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" minOccurs="0"/>
 *         &lt;element name="slotId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scheduleId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="appointmentType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="codedRejection" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="rejectionText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SlotRequestRejectionResponseType", propOrder = {
  "patientId",
  "patient",
  "slotId",
  "scheduleId",
  "appointmentType",
  "statusCode",
  "startDateTime",
  "endDateTime",
  "codedRejection",
  "rejectionText"
})
public class SlotRequestRejectionResponseType
        extends FactType {

  /** uses FactType->id to record slot request id */

  /** patient identifier */
  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;

  /** patient information */
  @XmlElement(name = "patient", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType patient;

  /** identifier of the appointment slot */
  @XmlElement(name = "slotId", required = true)
  protected String slotId;

  /** schedule identifier */
  @XmlElement(name = "scheduleId", required = true)
  protected String scheduleId;

  /** appointment type */
  @XmlElement(name = "appointmentType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair appointmentType;

  /** current status of the appointment */
  @XmlElement(name = "statusCode", required = true)
  protected String statusCode;

  /** appointment start date/time */
  @XmlElement(name = "startDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar startDateTime;

  /** appointment end date/time */
  @XmlElement(name = "endDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected XMLGregorianCalendar endDateTime;
  
  /** coded rejection */
  @XmlElement(name = "codedRejection", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedRejection;

  /** the reason for inability to fulfill the request */
  @XmlElement(name = "freeTextLocation")
  protected String rejectionText;

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
  public void setPatientId(ValueType value) {
    this.patientId = value;
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
  public void setPatient(NameFactType value) {
    this.patient = value;
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
   *     {@link String }
   *
   */
  public void setSlotId(String value) {
    this.slotId = value;
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
  public void setScheduleId(String value) {
    this.scheduleId = value;
  }

  /**
   * Gets the value of the appointmentType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getAppointmentType() {
    return appointmentType;
  }

  /**
   * Sets the value of the appointmentType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setAppointmentType(CodeLabelPair value) {
    this.appointmentType = value;
  }

  /**
   * Gets the value of the statusCode property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getStatusCode() {
    return statusCode;
  }

  /**
   * Sets the value of the statusCode property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setStatusCode(String value) {
    this.statusCode = value;
  }

  /**
   * Gets the value of the startDateTime property.
   *
   * @return
   *     possible object is
   *     {@link XMLGregorianCalendar }
   *
   */
  public XMLGregorianCalendar getStartDateTime() {
    return startDateTime;
  }

  /**
   * Sets the value of the startDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link XMLGregorianCalendar }
   *
   */
  public void setStartDateTime(XMLGregorianCalendar value) {
    this.startDateTime = value;
  }

  /**
   * Gets the value of the endDateTime property.
   *
   * @return
   *     possible object is
   *     {@link XMLGregorianCalendar }
   *
   */
  public XMLGregorianCalendar getEndDateTime() {
    return endDateTime;
  }

  /**
   * Sets the value of the endDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link XMLGregorianCalendar }
   *
   */
  public void setEndDateTime(XMLGregorianCalendar value) {
    this.endDateTime = value;
  }

  /**
   * Gets the value of the codedRejection property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedRejection() {
    return codedRejection;
  }

  /**
   * Sets the value of the codedRejection property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedRejection(CodeLabelPair value) {
    this.codedRejection = value;
  }

  /**
   * Gets the value of the rejectionText property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getRejectionText() {
    return rejectionText;
  }

  /**
   * Sets the value of the rejectionText property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setRejectionText(String value) {
    this.rejectionText = value;
  }


  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("SlotRequestRejectionResponseType(patientId=" + patientId + ",patient=" + patient);
    str.append("slotId=" + slotId + ",scheduleId=" + scheduleId + ",statusCode=" + statusCode);
    str.append("appointmentType=" + appointmentType + ",startDateTime=" + startDateTime + ",endDateTime=" + endDateTime);
    str.append(",codedRejection=" + codedRejection + ",rejectionText=" + rejectionText + ")");

    return str.toString();
  }
}
