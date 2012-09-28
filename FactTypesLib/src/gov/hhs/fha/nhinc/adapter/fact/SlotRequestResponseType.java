package gov.hhs.fha.nhinc.adapter.fact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for SlotRequestResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SlotRequestResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="confirmation" type="{urn:gov:hhs:fha:nhinc:adapter:fact}FactQueryResponseType" maxOccurs="unbounded"/>
 *           &lt;element name="rejection" type="{urn:gov:hhs:fha:nhinc:adapter:fact}SlotRequestRejectionResponseType"/>
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
@XmlType(name = "SlotRequestResponseType", propOrder = {
  "confirmation",
  "rejection"
})
public class SlotRequestResponseType {

  /** confirmation(s) */
  @XmlElement(name = "confirmation", nillable = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected String confirmation;

  /** rejection */
  @XmlElement(name = "rejection", nillable = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected SlotRequestRejectionResponseType rejection;

  /**
   * Gets the value of the rejection property.
   *
   * @return
   *     possible object is
   *     {@link SlotRequestRejectionResponseType }
   *
   */
  public SlotRequestRejectionResponseType getRejection() {
    return rejection;
  }

  /**
   * Sets the value of the rejection property.
   *
   * @param value
   *     allowed object is
   *     {@link SlotRequestRejectionResponseType }
   *
   */
  public void setRejection(SlotRequestRejectionResponseType value) {
    this.rejection = value;
  }

  /**
   * Gets the value of the confirmation property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getConfirmation() {
    return confirmation;
  }

  /**
   * Sets the value of the confirmation property.
   *
   * @param value
   *     allowed object is
   *     {@link String
   *
   */
  public void setConfirmation(String value) {
    this.confirmation = value;
  }
}
