package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for VitalFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="VitalFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="codedResultType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextResultType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="resultStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultValue" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *         &lt;element name="freeTextResultValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedResultInterpretation" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="lowReferenceRange" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *         &lt;element name="hiReferenceRange" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VitalFactType", propOrder = {
  "patientId",
  "codedResultType",
  "freeTextResultType",
  "resultDate",
  "resultStatus",
  "resultValue",
  "freeTextResultValue",
  "codedResultInterpretation",
  "lowReferenceRange",
  "hiReferenceRange"
})
public class VitalFactType
        extends FactType
        implements Serializable {

  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  @XmlElement(name = "codedResultType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedResultType;
  @XmlElement(name = "freeTextResultType", required = true)
  protected String freeTextResultType;
  @XmlElement(name = "resultDate", required = true, type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date resultDate;
  @XmlElement(name = "resultStatus", required = true)
  protected String resultStatus;
  @XmlElement(name = "resultValue", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair resultValue;
  @XmlElement(name = "freeTextResultValue")
  protected String freeTextResultValue;
  @XmlElement(name = "codedResultInterpretation", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedResultInterpretation;
  @XmlElement(name = "lowReferenceRange", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair lowReferenceRange;
  @XmlElement(name = "hiReferenceRange", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair hiReferenceRange;

  //---------------------------------------------------------------------------
  // JAXB generated codes
  //---------------------------------------------------------------------------
  /**
   * Sets the value of the patientId property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setPatientId(ValueType value) {
    this.patientId = value;
  }

  /**
   * Gets the value of the freeTextProduct property.
   *
   * @return
   *     possible object is
   *     {@link ValueType }
   *
   */
  public ValueType getPatientId() {
    return patientId;
  }

  /**
   * Gets the value of the codedResultType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedResultType() {
    return codedResultType;
  }

  /**
   * Sets the value of the codedResultType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedResultType(CodeLabelPair value) {
    this.codedResultType = value;
  }

  /**
   * Gets the value of the freeTextResultType property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextResultType() {
    return freeTextResultType;
  }

  /**
   * Sets the value of the freeTextResultType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextResultType(String value) {
    this.freeTextResultType = value;
  }

  /**
   * Gets the value of the resultDate property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getResultDate() {
    return resultDate;
  }

  /**
   * Sets the value of the resultDate property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setResultDate(Date value) {
    this.resultDate = value;
  }

  /**
   * Gets the value of the resultStatus property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getResultStatus() {
    return resultStatus;
  }

  /**
   * Sets the value of the resultStatus property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setResultStatus(String value) {
    this.resultStatus = value;
  }

  /**
   * Gets the value of the resultValue property.
   *
   * @return
   *     possible object is
   *     {@link ValueUnitPair }
   *
   */
  public ValueUnitPair getResultValue() {
    return resultValue;
  }

  /**
   * Sets the value of the resultValue property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueUnitPair }
   *
   */
  public void setResultValue(ValueUnitPair value) {
    this.resultValue = value;
  }

  /**
   * Gets the value of the freeTextResultValue property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextResultValue() {
    return freeTextResultValue;
  }

  /**
   * Sets the value of the freeTextResultValue property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextResultValue(String value) {
    this.freeTextResultValue = value;
  }

  /**
   * Gets the value of the codedResultInterpretation property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedResultInterpretation() {
    return codedResultInterpretation;
  }

  /**
   * Sets the value of the codedResultInterpretation property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedResultInterpretation(CodeLabelPair value) {
    this.codedResultInterpretation = value;
  }

  /**
   * Gets the value of the lowReferenceRange property.
   *
   * @return
   *     possible object is
   *     {@link ValueUnitPair }
   *
   */
  public ValueUnitPair getLowReferenceRange() {
    return lowReferenceRange;
  }

  /**
   * Sets the value of the lowReferenceRange property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueUnitPair }
   *
   */
  public void setLowReferenceRange(ValueUnitPair value) {
    this.lowReferenceRange = value;
  }

  /**
   * Gets the value of the hiReferenceRange property.
   *
   * @return
   *     possible object is
   *     {@link ValueUnitPair }
   *
   */
  public ValueUnitPair getHiReferenceRange() {
    return hiReferenceRange;
  }

  /**
   * Sets the value of the hiReferenceRange property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueUnitPair }
   *
   */
  public void setHiReferenceRange(ValueUnitPair value) {
    this.hiReferenceRange = value;
  }
  
  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("VitalFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  codedResultType=" + (codedResultType != null ? codedResultType : "") + System.getProperty("line.separator"));
    str.append("  freeTextResultType=" + freeTextResultType + System.getProperty("line.separator"));
    str.append("  resultDate=" + resultDate + System.getProperty("line.separator"));
    str.append("  resultStatus=" + resultStatus + System.getProperty("line.separator"));
    str.append("  resultValue=" + (resultValue != null ? resultValue : "") + System.getProperty("line.separator"));
    str.append("  freeTextResultValue=" + freeTextResultValue + System.getProperty("line.separator"));
    str.append("  codedResultInterpretation=" + (codedResultInterpretation != null ? codedResultInterpretation : "") + System.getProperty("line.separator"));
    str.append("  lowReferenceRange=" + (lowReferenceRange != null ? lowReferenceRange : "") + System.getProperty("line.separator"));
    str.append("  hiReferenceRange=" + (hiReferenceRange != null ? hiReferenceRange : "") + System.getProperty("line.separator"));

    str.append("]");

    return str.toString();
  }
}
