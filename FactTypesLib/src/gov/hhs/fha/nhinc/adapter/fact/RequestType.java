package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="senderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="interactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="triggerEventCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "RequestType", propOrder = {
    "requestId",
    "senderId",
    "interactionId",
    "triggerEventCode"
})
@XmlSeeAlso({
    SlotRequestType.class
})
public abstract class RequestType implements Serializable {

  @XmlElement(name = "requestId", required = true)
  protected String requestId;
  @XmlElement(required = true)
  protected String senderId;
  @XmlElement(required = true)
  protected String interactionId;
  @XmlElement(required = true)
  protected String triggerEventCode;

  /**
   * Gets the value of the requestId property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getRequestId() {
    return requestId;
  }

  /**
   * Sets the value of the requestId property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setRequestId(String value) {
    this.requestId = value;
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

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  @Override
  public String toString() {
    return "RequestType(id=" + requestId + ",senderId=" + senderId + ",interactionId=" + interactionId + ",triggerEventCode=" + triggerEventCode;
  }
}
