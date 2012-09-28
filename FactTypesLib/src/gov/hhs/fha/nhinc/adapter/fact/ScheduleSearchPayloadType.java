package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for ScheduleSearchPayloadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ScheduleSearchPayloadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appointmentType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="slotType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scheduleStartDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scheduleEndDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="scheduleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sdlcId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sdlcName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="performerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="performerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScheduleSearchPayloadType", propOrder = {
  "appointmentType",
  "slotType",
  "scheduleStartDate",
  "scheduleEndDate",
  "scheduleId",
  "sdlcId",
  "sdlcName",
  "performerId",
  "performerName"
})
public class ScheduleSearchPayloadType implements Serializable {

  // Primary search criteria if known when querying for appointment slots.
  @XmlElement(name = "scheduleId")
  protected String scheduleId;
  // Identify the type of appointment, ROUTINE, CHECKUP, FOLLOWUP, WALKIN, CONSULT.
  // Uses to filter the appointment slots to those with matching criteria.
  @XmlElement(name = "appointmentType", required = true)
  protected String appointmentType;
  // Identify the type of slots desired from the schedule.
  @XmlElement(name = "slotType", required = true)
  protected String slotType;
  // One of the primary search criteria when querying for appointment slots.
  // The start date and time of the schedule.
  @XmlElement(name = "scheduleStartDate", required = true)
  protected String scheduleStartDate;
  // One of the primary search criteria when querying for appointment slots.
  // The end date and time of the schedule.
  @XmlElement(name = "scheduleEndDate")
  protected String scheduleEndDate;
  // One of the primary search criteria when querying for appointment slots.
  // The identifier of the service delivery location where the service will occcur.
  @XmlElement(name = "sdlcId", required = true)
  protected String sdlcId;
  // Informative data - name of the service delivery location.
  @XmlElement(name = "sdlcName", required = true)
  protected String sdlcName;
  // One of the primary search criteria when querying for appointment slots.
  // The identifier of the person who will be providing the service.
  @XmlElement(name = "performerId", required = true)
  protected String performerId;
  // Informative data - name of the person who will be providing the service.
  @XmlElement(name = "performerName", required = true)
  protected String performerName;

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
   * Sets the value of the performerId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPerformerId(String value) {
    this.performerId = value;
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
  public void setAppointmentType(String value) {
    this.appointmentType = value;
  }

  /**
   * Gets the value of the slotType property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSlotType() {
    return slotType;
  }

  /**
   * Sets the value of the slotType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setSlotType(String value) {
    this.slotType = value;
  }

  /**
   * Gets the value of the scheduleStartDate property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getScheduleStartDate() {
    return scheduleStartDate;
  }

  /**
   * Sets the value of the scheduleStartDate property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setScheduleStartDate(String value) {
    this.scheduleStartDate = value;
  }

  /**
   * Gets the value of the scheduleEndDate property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getScheduleEndDate() {
    return scheduleEndDate;
  }

  /**
   * Sets the value of the scheduleEndDate property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setScheduleEndDate(String value) {
    this.scheduleEndDate = value;
  }

  /**
   * Gets the value of the performerName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPerformerName() {
    return performerName;
  }

  /**
   * Sets the value of the performerName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPerformerName(String value) {
    this.performerName = value;
  }

  /**
   * Gets the value of the sdlcId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSdlcId() {
    return sdlcId;
  }

  /**
   * Sets the value of the sdlcId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setSdlcId(String sdlcId) {
    this.sdlcId = sdlcId;
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
  public void setSdlcName(String sdlcName) {
    this.sdlcName = sdlcName;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ScheduleSearchPayloadType[appointmentType=" + appointmentType + ",slotType=" + slotType + System.getProperty("line.separator"));
    str.append("  scheduleStartDate=" + scheduleStartDate + ",scheduleEndDate=" + scheduleEndDate + System.getProperty("line.separator"));
    str.append("  scheduleId=" + scheduleId + ",sdlcId=" + sdlcId + ",sdlcName=" + sdlcName + System.getProperty("line.separator"));
    str.append("  performerId=" + performerId + ",performerName=" + performerName + "]");

    return str.toString();
  }
}
