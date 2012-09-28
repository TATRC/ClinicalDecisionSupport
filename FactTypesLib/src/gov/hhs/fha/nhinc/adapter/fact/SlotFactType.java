package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for SlotFactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SlotFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}RequestType">
 *       &lt;sequence>
 *         &lt;element name="scheduleId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codedAppointmentType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="startDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="duration" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair" minOccurs="0"/>
 *         &lt;element name="performerId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/
 *         &lt;element name="performer" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="performerTelecom" type="{urn:gov:hhs:fha:nhinc:adapter:fact}TelecomFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sdlcId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="sdlcName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sdlcRole" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SlotFactType", propOrder = {
    "scheduleId",
    "statusCode",
    "codedAppointmentType",
    "startDateTime",
    "endDateTime",
    "duration",
    "performerId",
    "performer",
    "performerTelecom",
    "sdlcId",
    "sdlcName",
    "sdlcRole"
})
public class SlotFactType
        extends FactType
        implements Serializable {

  /** store identifier of the appointment slot in "id" field  */

  /**
   * identifier of the schedule
   */
  @XmlElement(name = "scheduleId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType scheduleId;
  /**
   * status of this slot
   */
  @XmlElement(name = "statusCode", required = true)
  protected String statusCode;
  /**
   * type of appointment assigned to this slot
   */
  @XmlElement(name = "codedAppointmentType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedAppointmentType;
  /**
   * appointment duration, must be a positive number (non-zero) and shall be default to
   * "min" ((minutes).
   */
  @XmlElement(name = "duration", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair duration;
  /**
   * start date and time of this appointment slot.
   */
  @XmlElement(name = "startDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date startDateTime;
  /**
   * end date and time of this appointment slot.
   */
  @XmlElement(name = "endDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date endDateTime;
  /** 
   * identifier of service delivery location associated with this schedule
   */
  @XmlElement(name = "sdlcId", required = true)
  protected ValueType sdlcId;
  /** 
   * free text name of service delivery location associated with this schedule
   */
  @XmlElement(name = "sdlcName")
  protected String sdlcName;
  /**
   * identifier of service delivery location associated with this schedule
   */
  @XmlElement(name = "sdlcRole", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair sdlcRole;
  /** 
   * identifier of person who will be providing service
   */
  @XmlElement(name = "performerId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType performerId;
  /** 
   * name of provider who will be providing service
   */
  @XmlElement(name = "performer", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType performer;
  /**
   * name of provider who will be providing service
   */
  @XmlElement(name = "performerTelecom", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<TelecomFactType> performerTelecom;
  /** 
   * Coded role of person who will be providing service in this appointment/schedule
   */
  //@XmlElement(name = "codedPerformerRole", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  //protected CodeLabelPair codedPerformerRole;
 

  /**
   * Gets the value of the scheduleId property.
   *
   * @return
   *     possible object is
   *     {@link ValueType }
   *
   */
  public ValueType getScheduleId() {
    return scheduleId;
  }

  /**
   * Sets the value of the scheduleId property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setScheduleId(ValueType scheduleId) {
    this.scheduleId = scheduleId;
  }

  /**
   * Gets the value of the codedAppointmentType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedAppointmentType() {
    return codedAppointmentType;
  }

  /**
   * Sets the value of the codedAppointmentType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedAppointmentType(CodeLabelPair appointmentType) {
    this.codedAppointmentType = appointmentType;
  }

  /**
   * Gets the value of the sdlcId property.
   *
   * @return
   *     possible object is
   *     {@link ValueType }
   *
   */
  public ValueType getSdlcId() {
    return sdlcId;
  }

  /**
   * Sets the value of the sdlcId property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setSdlcId(ValueType value) {
    this.sdlcId = value;
  }
  
  /**
   * Gets the value of the sdlcName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSdlcName() {
    return sdlcName;
  }

  /**
   * Sets the value of the sdlcName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setSdlcName(String value) {
    this.sdlcName = value;
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
   *     {@link ValueType }
   *
   */
  public ValueType getPerformerId() {
    return performerId;
  }

  /**
   * Sets the value of the performer property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setPerformerId(ValueType performerId) {
    this.performerId = performerId;
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
  public void setStatusCode(String status) {
    this.statusCode = status;
  }

  /**
   * Gets the value of the sdlcRole property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getSdlcRole() {
    return sdlcRole;
  }

  /**
   * Sets the value of the sdlcRole property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setSdlcRole(CodeLabelPair role) {
    this.sdlcRole = role;
  }

  /**
   * Gets the value of the performerTelecom property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the performerTelecom property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getPerformerTelecom().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link TelecomFactType }
   *
   *
   */
  public List<TelecomFactType> getPerformerTelecom() {
    if (performerTelecom == null) {
      performerTelecom = new ArrayList<TelecomFactType>();
    }
    return this.performerTelecom;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  public void setPerformerTelecom(ArrayList<TelecomFactType> telecom) {
    this.performerTelecom = telecom;
  }
  
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("SlotFactType(scheduleId=" + scheduleId + ",slotid=" + id + System.getProperty("line.separator"));
    str.append("  ,startDateTime=" + startDateTime + ",endDateTime=" + endDateTime + ",duration=" + duration + System.getProperty("line.separator"));
    str.append("  ,codedAppointmentType=" + (codedAppointmentType != null? codedAppointmentType: ""));
    str.append("  ,statusCode=" + statusCode + System.getProperty("line.separator"));
    str.append("  ,performerId=" + performerId + ",performer=" + (performer != null? performer: "") + System.getProperty("line.separator"));
    str.append("  ,sdlcId=" + sdlcId + ",sdlcName=" + sdlcName + System.getProperty("line.separator"));
    str.append("  ,sdlcRole=" + (sdlcRole != null? sdlcRole: "") + System.getProperty("line.separator"));
    str.append("  #telecoms=" + getPerformerTelecom().size() + System.getProperty("line.separator"));
    for (int i = 0; i < getPerformerTelecom().size(); i++) {
      str.append("    |--> #" + i + "=" + performerTelecom.get(i) + System.getProperty("line.separator"));
    }
    
    return str.toString();
  }
}
