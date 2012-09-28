package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for AppointmentFactType complex type.
 *
 * <p>Appointment: A mutual agreement for a patient to be seen by or be in contact with one or more clinical
 * service providers. Associated details usually include patient ID, provider ID, type of service, reason for
 * visit, date, time, and location for visit. An appointment may be for follow-up visits, new patient visits,
 * physical examinations, procedures, therapies, and/or tests.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppointmentFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="patient" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" minOccurs="0"/>
 *         &lt;element name="chiefComplaint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="appointmentType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="performer" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="author" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" minOccurs="0"/>
 *         &lt;element name="codedLocation" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextLocation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentFactType", propOrder = {
    "patientId",
    "patient",
    "chiefComplaint",
    "statusCode",
    "startDateTime",
    "endDateTime",
    "appointmentType",
    "performer",
    "author",
    "codedLocation",
    "freeTextLocation"
})
public class AppointmentFactType
        extends FactType
        implements Serializable
{
  /** uses FactType->id to carry appointment id */

  /** patient identifier */
  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  
  /** patient information */
  @XmlElement(name = "patient", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType patient;

  /** reason for appointment */
  @XmlElement(name = "chiefComplaint")
  protected String chiefComplaint;

  /** current status of the appointment */
  @XmlElement(name = "statusCode", required = true)
  protected String statusCode;

  /** appointment start date/time */
  @XmlElement(name = "startDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date startDateTime;

  /** appointment end date/time */
  @XmlElement(name = "endDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date endDateTime;

  /** appointment type */
  @XmlElement(name = "appointmentType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair appointmentType;

  /** the provider who will be providing service */
  @XmlElement(name = "performer", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType performer;

  /** the person requested this appointment */
  @XmlElement(name = "author", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType author;

  /** service delivery location where a medical appointment will occur */
  @XmlElement(name = "codedLocation", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedLocation;

  /** free text name of service delivery location, i.e. clinic, where  */
  @XmlElement(name = "freeTextLocation")
  protected String freeTextLocation;

  /** order number associated with this appointment */
  //@XmlElement(name = "orderNumber")
  //protected String orderNumber;

  
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
  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  /**
   * Gets the value of the statusCode property.
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
  public void setAppointmentType(CodeLabelPair appointmentType) {
    this.appointmentType = appointmentType;
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
  public void setStartDateTime(Date startDateTime) {
    this.startDateTime = startDateTime;
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
  public void setEndDateTime(Date endDateTime) {
    this.endDateTime = endDateTime;
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
   * Gets the value of the author property.
   *
   * @return
   *     possible object is
   *     {@link NameFactType }
   *
   */
  public NameFactType getAuthor() {
    return author;
  }

  /**
   * Sets the value of the author property.
   *
   * @param value
   *     allowed object is
   *     {@link NameFactType }
   *
   */
  public void setAuthor(NameFactType author) {
    this.author = author;
  }

  /**
   * Gets the value of the codedLocation property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedLocation() {
    return codedLocation;
  }

  /**
   * Sets the value of the codedLocation property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedLocation(CodeLabelPair location) {
    this.codedLocation = location;
  }

  /**
   * Gets the value of the freeTextLocation property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextLocation() {
    return freeTextLocation;
  }

  /**
   * Sets the value of the freeTextLocation property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextLocation(String value) {
    this.freeTextLocation = value;
  }

  /**
   * Gets the value of the orderNumber property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  //public String getOrderNumber() {
  //  return orderNumber;
  //}

  /**
   * Sets the value of the orderNumber property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  //public void setOrderNumber(String value) {
  //  this.orderNumber = value;
  //}

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("AppointmentFactType(");
    str.append(super.toString());
    str.append(System.getProperty("line.separator"));
    str.append("  patientId=" + patientId);
    str.append(System.getProperty("line.separator"));
    str.append("  patient=" + patient);
    str.append(System.getProperty("line.separator"));
    str.append("  statusCode=" + statusCode);
    str.append(System.getProperty("line.separator"));
    str.append("  chiefComplaint=" + chiefComplaint);
    str.append(System.getProperty("line.separator"));
    str.append("  startDateTime=" + startDateTime);
    str.append(System.getProperty("line.separator"));
    str.append("  endDateTime=" + endDateTime);
    str.append(System.getProperty("line.separator"));
    str.append("  appointmentType=" + appointmentType);
    str.append(System.getProperty("line.separator"));
    //str.append("  orderNumber=" + orderNumber);
    //str.append(System.getProperty("line.separator"));
    str.append("  performer=" + performer);
    str.append(System.getProperty("line.separator"));
    str.append("  codedLocation=" + codedLocation);
    str.append(System.getProperty("line.separator"));
    str.append("  author=" + author + ")");

    return str.toString();
  }
}
