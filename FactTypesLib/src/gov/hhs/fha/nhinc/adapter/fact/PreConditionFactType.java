package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for PreConditionFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PreConditionFactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codedCriterionCode" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="codedCriterionValue" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="criterionLowValue" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *         &lt;element name="criterionHiValue" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PreConditionFactType", propOrder = {
  "codedCriterionCode",
  "codedCriterionValue",
  "criterionLowValue",
  "criterionHiValue"
})
public class PreConditionFactType implements Serializable {

  @XmlElement(name = "codedCriterionCode", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedCriterionCode;
  @XmlElement(name = "codedCriterionValue", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedCriterionValue;
  @XmlElement(name = "criterionLowValue", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair criterionLowValue;
  @XmlElement(name = "criterionHiValue", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair criterionHiValue;

  /**
   * Gets the value of the codedCriterionCode property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedCriterionCode() {
    return codedCriterionCode;
  }

  /**
   * Sets the value of the codedCriterionCode property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedCriterionCode(CodeLabelPair value) {
    this.codedCriterionCode = value;
  }

  /**
   * Gets the value of the codedCriterionValue property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedCriterionValue() {
    return codedCriterionValue;
  }

  /**
   * Sets the value of the codedCriterionValue property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedCriterionValue(CodeLabelPair value) {
    this.codedCriterionValue = value;
  }

  /**
   * Gets the value of the criterionLowValue property.
   *
   * @return
   *     possible object is
   *     {@link ValueUnitPair }
   *
   */
  public ValueUnitPair getCriterionLowValue() {
    return criterionLowValue;
  }

  /**
   * Sets the value of the criterionLowValue property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueUnitPair }
   *
   */
  public void setCriterionLowValue(ValueUnitPair value) {
    this.criterionLowValue = value;
  }

  /**
   * Gets the value of the criterionHiValue property.
   *
   * @return
   *     possible object is
   *     {@link ValueUnitPair }
   *
   */
  public ValueUnitPair getCriterionHiValue() {
    return criterionHiValue;
  }

  /**
   * Sets the value of the criterionHiValue property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueUnitPair }
   *
   */
  public void setCriterionHiValue(ValueUnitPair value) {
    this.criterionHiValue = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  @Override
  public String toString() {
    return "PreConditionFactType(codedCriterionCode=" + codedCriterionCode + ",codedCriterionValue=" + codedCriterionValue +
            ",criterionHiValue=" + criterionHiValue + ",criterionLowValue=" + criterionLowValue + ")";
  }
}
