package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for CodeLabelPair complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CodeLabelPair">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeSystemPair">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="label" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CodeLabelPair", propOrder = {
  "code",
  "label"
})
public class CodeLabelPair
  extends CodeSystemPair
  implements Serializable
{

  // The code from the code system.
  @XmlElement(name = "code", required = true)
  protected String code;
  // The name of the concept from the code system.
  @XmlElement(name = "label", required = true)
  protected String label;

  public CodeLabelPair() {
  }

  /**
   * Gets the value of the code property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets the value of the code property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCode(String value) {
    this.code = value;
  }

  /**
   * Gets the value of the label property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getLabel() {
    return label;
  }

  /**
   * Sets the value of the label property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setLabel(String value) {
    this.label = value;
  }

//  /**
//   * Gets the value of the codeSystem property.
//   *
//   * @return
//   *     possible object is
//   *     {@link String }
//   *
//   */
//  public String getCodeSystem() {
//    return codeSystem;
//  }
//
//  /**
//   * Sets the value of the codeSystem property.
//   *
//   * @param value
//   *     allowed object is
//   *     {@link String }
//   *
//   */
//  public void setCodeSystem(String value) {
//    this.codeSystem = value;
//  }
//
//  /**
//   * Gets the value of the codeSystemName property.
//   *
//   * @return
//   *     possible object is
//   *     {@link String }
//   *
//   */
//  public String getCodeSystemName() {
//    return codeSystemName;
//  }
//
//  /**
//   * Sets the value of the codeSystemName property.
//   *
//   * @param value
//   *     allowed object is
//   *     {@link String }
//   *
//   */
//  public void setCodeSystemName(String value) {
//    this.codeSystemName = value;
//  }

  //---------------------------------------------------------------------------
  // Add custom codes here
  //---------------------------------------------------------------------------

  public CodeLabelPair(String code, String label, String codeSystem, String codeSystemName) {
    super(codeSystem, codeSystemName);
    this.code = code;
    this.label = label;
  }

  @Override
  public String toString() {
    return "CodeLabelPair(" + code + "|" + label + "|" + super.toString() + ")";
  }
}
