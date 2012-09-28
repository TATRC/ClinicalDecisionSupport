package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for FactQueryRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>
 * &lt;complexType name="FactQueryRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="senderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="interactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="triggerEventCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="careRecordPayload" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CareRecordPayloadType" minOccurs="0"/>
 *           &lt;element name="resultEventPayload" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ResultEventPayloadType" minOccurs="0"/>
 *           &lt;element name="encounterSearchPayload" type="{urn:gov:hhs:fha:nhinc:adapter:fact}EncounterSearchPayloadType" minOccurs="0"/>
 *           &lt;element name="providerSearchPayload" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ProviderSearchPayloadType" minOccurs="0"/>
 *           &lt;element name="patientSearchPayload" type="{urn:gov:hhs:fha:nhinc:adapter:fact}PatientSearchPayloadType" minOccurs="0"/>
 *           &lt;element name="recordQueryPayload" type="{urn:gov:hhs:fha:nhinc:adapter:fact}RecordQueryPayloadType" minOccurs="0"/>
 *           &lt;element name="scheduleSearchPayload" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ScheduleSearchPayloadType" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FactQueryRequestType", propOrder = {
  "queryId",
  "senderId",
  "interactionId",
  "triggerEventCode",
  "careRecordPayload",
  "resultEventPayload",
  "encounterSearchPayload",
  "providerSearchPayload",
  "patientSearchPayload",
  "recordQueryPayload",
  "scheduleSearchPayload"
})
public class FactQueryRequestType implements Serializable {

  @XmlElement(name = "queryId", required = true)
  protected String queryId;
  @XmlElement(name = "senderId", required = true)
  protected String senderId;
  @XmlElement(name = "interactionId", required = true)
  protected String interactionId;
  @XmlElement(name = "triggerEventCode", required = true)
  protected String triggerEventCode;
  @XmlElement(name = "careRecordPayload", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CareRecordPayloadType careRecordPayload;
  @XmlElement(name = "resultEventPayload", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ResultEventPayloadType resultEventPayload;
  @XmlElement(name = "encounterSearchPayload", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected EncounterSearchPayloadType encounterSearchPayload;
  @XmlElement(name = "providerSearchPayload", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ProviderSearchPayloadType providerSearchPayload;
  @XmlElement(name = "patientSearchPayload", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected PatientSearchPayloadType patientSearchPayload;
  @XmlElement(name = "recordQueryPayload", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected RecordQueryPayloadType recordQueryPayload;
  @XmlElement(name = "scheduleSearchPayload", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ScheduleSearchPayloadType scheduleSearchPayload;

  /**
   * Gets the value of the queryId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getQueryId() {
    return queryId;
  }

  /**
   * Sets the value of the queryId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setQueryId(String value) {
    this.queryId = value;
  }

  /**
   * Gets the value of the senderId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSenderId() {
    return senderId;
  }

  /**
   * Sets the value of the senderId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setSenderId(String value) {
    this.senderId = value;
  }

  /**
   * Gets the value of the interactionId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getInteractionId() {
    return interactionId;
  }

  /**
   * Sets the value of the interactionId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setInteractionId(String value) {
    this.interactionId = value;
  }

  /**
   * Gets the value of the triggerEventCode property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getTriggerEventCode() {
    return triggerEventCode;
  }

  /**
   * Sets the value of the triggerEventCode property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setTriggerEventCode(String value) {
    this.triggerEventCode = value;
  }

  /**
   * Gets the value of the careRecordPayload property.
   *
   * @return
   *     possible object is
   *     {@link CareRecordPayloadType }
   *
   */
  public CareRecordPayloadType getCareRecordPayload() {
    return careRecordPayload;
  }

  /**
   * Sets the value of the careRecordPayload property.
   *
   * @param value
   *     allowed object is
   *     {@link CareRecordPayloadType }
   *
   */
  public void setCareRecordPayload(CareRecordPayloadType value) {
    this.careRecordPayload = value;
  }

  /**
   * Gets the value of the resultEventPayload property.
   *
   * @return
   *     possible object is
   *     {@link ResultEventPayloadType }
   *
   */
  public ResultEventPayloadType getResultEventPayload() {
    return resultEventPayload;
  }

  /**
   * Sets the value of the resultEventPayload property.
   *
   * @param value
   *     allowed object is
   *     {@link ResultEventPayloadType }
   *
   */
  public void setResultEventPayload(ResultEventPayloadType value) {
    this.resultEventPayload = value;
  }

  /**
   * Gets the value of the encounterSearchPayload property.
   *
   * @return
   *     possible object is
   *     {@link EncounterSearchPayloadType }
   *
   */
  public EncounterSearchPayloadType getEncounterPayload() {
    return encounterSearchPayload;
  }

  /**
   * Sets the value of the encounterSearchPayload property.
   *
   * @param value
   *     allowed object is
   *     {@link EncounterSearchPayloadType }
   *
   */
  public void setEncounterPayload(EncounterSearchPayloadType value) {
    this.encounterSearchPayload = value;
  }

  /**
   * Gets the value of the providerSearchPayload property.
   *
   * @return
   *     possible object is
   *     {@link ProviderSearchPayloadType }
   *
   */
  public ProviderSearchPayloadType getProviderSearchPayload() {
    return providerSearchPayload;
  }

  /**
   * Sets the value of the providerSearchPayload property.
   *
   * @param value
   *     allowed object is
   *     {@link ProviderSearchPayloadType }
   *
   */
  public void setProviderSearchPayload(ProviderSearchPayloadType value) {
    this.providerSearchPayload = value;
  }

  /**
   * Gets the value of the patientSearchPayload property.
   *
   * @return
   *     possible object is
   *     {@link PatientSearchPayloadType }
   *
   */
  public PatientSearchPayloadType getPatientSearchPayload() {
    return patientSearchPayload;
  }

  /**
   * Sets the value of the patientSearchPayload property.
   *
   * @param value
   *     allowed object is
   *     {@link PatientSearchPayloadType }
   *
   */
  public void setPatientSearchPayload(PatientSearchPayloadType value) {
    this.patientSearchPayload = value;
  }

  /**
   * Gets the value of the recordQueryPayload property.
   *
   * @return
   *     possible object is
   *     {@link RecordQueryPayloadType }
   *
   */
  public RecordQueryPayloadType getRecordQueryPayload() {
    return recordQueryPayload;
  }

  /**
   * Sets the value of the recordQueryPayload property.
   *
   * @param value
   *     allowed object is
   *     {@link RecordQueryPayloadType }
   *
   */
  public void setRecordQueryPayload(RecordQueryPayloadType value) {
    this.recordQueryPayload = value;
  }

  /**
   * Gets the value of the scheduleSearchPayload property.
   *
   * @return
   *     possible object is
   *     {@link ScheduleSearchPayloadType }
   *
   */
  public ScheduleSearchPayloadType getScheduleSearchPayload() {
    return scheduleSearchPayload;
  }

  /**
   * Sets the value of the scheduleSearchPayload property.
   *
   * @param value
   *     allowed object is
   *     {@link ScheduleSearchPayloadType }
   *
   */
  public void setScheduleSearchPayload(ScheduleSearchPayloadType value) {
    this.scheduleSearchPayload = value;
  }
}
