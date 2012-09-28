package gov.hhs.fha.nhinc.adapter.fact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for SeverityFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SeverityFactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codedSeverity" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextSeverity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SeverityFactType", propOrder = {
  "codedSeverity",
  "freeTextSeverity"
})
public class SeverityFactType {

  @XmlElement(name = "codedSeverity", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedSeverity;
  @XmlElement(name = "freeTextSeverity")
  protected String freeTextSeverity;

  /**
   * Gets the value of the codedSeverity property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedSeverity() {
    return codedSeverity;
  }

  /**
   * Sets the value of the codedSeverity property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedSeverity(CodeLabelPair value) {
    this.codedSeverity = value;
  }

  /**
   * Gets the value of the freeTextSeverity property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextSeverity() {
    return freeTextSeverity;
  }

  /**
   * Sets the value of the freeTextSeverity property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextSeverity(String value) {
    this.freeTextSeverity = value;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ReactionFactType[" + System.getProperty("line.separator"));
    if (getCodedSeverity() != null) {
      str.append("  severity=" + getCodedSeverity().getCode() + "|" + getCodedSeverity().getLabel() + System.getProperty("line.separator"));
    }
    str.append("  freeTextSeverity=" + getFreeTextSeverity() + System.getProperty("line.separator"));
    str.append("]");

    return str.toString();
  }
}
