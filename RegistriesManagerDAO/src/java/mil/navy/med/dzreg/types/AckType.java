package mil.navy.med.dzreg.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for AckType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AckType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="detectedIssueText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AckType", propOrder = {
  "responseCode",
  "detectedIssueText"
})
public class AckType {

  @XmlElement(name = "responseCode", required = true)
  protected String responseCode;
  @XmlElement(name = "detectedIssueText")
  protected String detectedIssueText;

  /**
   * Gets the value of the responseCode property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getResponseCode() {
    return responseCode;
  }

  /**
   * Sets the value of the responseCode property.
   * Allowable values are:  OK, AE (Application Error)
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setResponseCode(String value) {
    this.responseCode = value;
  }

  /**
   * Gets the value of the detectedIssueText property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getDetectedIssueText() {
    return detectedIssueText;
  }

  /**
   * Sets the value of the detectedIssueText property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setDetectedIssueText(String value) {
    this.detectedIssueText = value;
  }
  //----------------------------------------------------------------------------
  // Add any custom codes here
  //----------------------------------------------------------------------------

  @Override
  public String toString() {
    return "AckType[responseCode=" + responseCode + ",detectedIssueText=" + detectedIssueText + "]";
  }
}
