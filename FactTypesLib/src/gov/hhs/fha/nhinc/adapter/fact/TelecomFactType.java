package gov.hhs.fha.nhinc.adapter.fact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for TelecomFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="TelecomFactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="telecomType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TelecomFactType", propOrder = {
  "value",
  "telecomType"
})
public class TelecomFactType {

  @XmlElement(name = "value", required = true)
  protected String value;
  @XmlElement(name = "telecomType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair telecomType;

  /**
   * Gets the value of the value property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getValue() {
    return value;
  }

  /**
   * Sets the value of the value property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * Gets the value of the telecomType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getTelecomType() {
    return telecomType;
  }

  /**
   * Sets the value of the telecomType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setTelecomType(CodeLabelPair value) {
    this.telecomType = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  @Override
  public String toString() {
    return "TelecomFactType(telecomType=" + telecomType + ",value=" + value + ")";
  }
}
