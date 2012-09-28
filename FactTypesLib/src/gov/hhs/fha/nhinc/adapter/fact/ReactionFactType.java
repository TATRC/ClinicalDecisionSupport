package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for ReactionFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ReactionFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="codedReaction" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextReaction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="severity" type="{urn:gov:hhs:fha:nhinc:adapter:fact}SeverityFactType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReactionFactType", propOrder = {
  "codedReaction",
  "freeTextReaction",
  "severity"
})
public class ReactionFactType
        extends FactType
        implements Serializable
{

  @XmlElement(name = "codedReaction", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedReaction;
  @XmlElement(name = "freeTextReaction")
  protected String freeTextReaction;
  @XmlElement(name = "severity", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected SeverityFactType severity;

  /**
   * Gets the value of the codedReaction property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedReaction() {
    return codedReaction;
  }

  /**
   * Sets the value of the codedReaction property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedReaction(CodeLabelPair value) {
    this.codedReaction = value;
  }

  /**
   * Gets the value of the freeTextReaction property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextReaction() {
    return freeTextReaction;
  }

  /**
   * Sets the value of the freeTextReaction property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextReaction(String value) {
    this.freeTextReaction = value;
  }

  /**
   * Gets the value of the severity property.
   *
   * @return
   *     possible object is
   *     {@link SeverityFactType }
   *
   */
  public SeverityFactType getSeverity() {
    return severity;
  }

  /**
   * Sets the value of the severity property.
   *
   * @param value
   *     allowed object is
   *     {@link SeverityFactType }
   *
   */
  public void setSeverity(SeverityFactType value) {
    this.severity = value;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ReactionFactType[" + System.getProperty("line.separator"));
    if (getCodedReaction() != null) {
      str.append("  reaction=" + getCodedReaction().getCode() + "|" + getCodedReaction().getLabel() + System.getProperty("line.separator"));
    }
    str.append("  freeTextReaction=" + getFreeTextReaction() + System.getProperty("line.separator"));
    if (getSeverity() != null) {
      str.append("  " + getSeverity().toString() + System.getProperty("line.separator"));
    }
    str.append("]");

    return str.toString();
  }
}
