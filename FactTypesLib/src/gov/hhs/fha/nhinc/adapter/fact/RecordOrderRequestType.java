package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RecordOrderRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RecordOrderRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="senderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="interactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="triggerEventCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="labOrder" type="{urn:gov:hhs:fha:nhinc:adapter:fact}LabOrderFactType"/>
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
@XmlType(name = "RecordOrderRequestType", propOrder = {
  "queryId",
  "senderId",
  "interactionId",
  "triggerEventCode",
  "labOrder"
})
public class RecordOrderRequestType implements Serializable {

  @XmlElement(required = true)
  protected String queryId;
  @XmlElement(required = true)
  protected String senderId;
  @XmlElement(required = true)
  protected String interactionId;
  @XmlElement(required = true)
  protected String triggerEventCode;
  @XmlElement(required = true)
  protected LabOrderFactType labOrder;

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
   * Gets the value of the labOrder property.
   *
   * @return
   *     possible object is
   *     {@link LabOrderFactType }
   *
   */
  public LabOrderFactType getLabOrder() {
    return labOrder;
  }

  /**
   * Sets the value of the labOrder property.
   *
   * @param value
   *     allowed object is
   *     {@link LabOrderFactType }
   *
   */
  public void setLabOrder(LabOrderFactType value) {
    this.labOrder = value;
  }
}
