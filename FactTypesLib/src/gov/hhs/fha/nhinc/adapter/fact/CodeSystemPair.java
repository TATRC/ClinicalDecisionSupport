package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for CodeSystemPair complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CodeSystemPair">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codeSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeSystemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodeSystemPair", propOrder = {
  "codeSystem",
  "codeSystemName"
})
@XmlSeeAlso({
  CodeLabelPair.class,
  ValueType.class
})
public class CodeSystemPair
  implements Serializable
{

  // The OID identifying the code system.
  @XmlElement(name = "codeSystem")
  protected String codeSystem;
  // The name of the code system.
  @XmlElement(name = "codeSystemName")
  protected String codeSystemName;

  public CodeSystemPair() {
  }

  /**
   * Gets the value of the codeSystem property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getCodeSystem() {
    return codeSystem;
  }

  /**
   * Sets the value of the codeSystem property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCodeSystem(String value) {
    this.codeSystem = value;
  }

  /**
   * Gets the value of the codeSystemName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getCodeSystemName() {
    return codeSystemName;
  }

  /**
   * Sets the value of the codeSystemName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCodeSystemName(String value) {
    this.codeSystemName = value;
  }

  //---------------------------------------------------------------------------
  // Add custom codes here
  //---------------------------------------------------------------------------

  public CodeSystemPair(String codeSystem, String codeSystemName) {
    this.codeSystem = codeSystem;
    this.codeSystemName = codeSystemName;
  }

  @Override
  public String toString() {
    return codeSystem + "|" + codeSystemName;
  }
}
