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
 * <p>Java class for ResultFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ResultFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="specimenDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="specimen" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="codedTestType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextTestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resultStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultValue" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *         &lt;element name="resultFreeText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedResultInterpretation" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="lowReferenceRange" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *         &lt;element name="hiReferenceRange" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *         &lt;element name="codedRefRangeInterpretationCode" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="refRangePrecondition" type="{urn:gov:hhs:fha:nhinc:adapter:fact}PreConditionFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="codedPanelType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextPanelType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderingProvider" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultFactType", propOrder = {
  "patientId",
  "specimenDate",
  "specimen",
  "resultDate",
  "codedTestType",
  "freeTextTestType",
  "resultStatus",
  "resultValue",
  "resultFreeText",
  "codedResultInterpretation",
  "lowReferenceRange",
  "hiReferenceRange",
  "codedRefRangeInterpretationCode",
  "refRangePrecondition",
  "codedPanelType",
  "freeTextPanelType",
  "orderingProvider"
})
public class ResultFactType
        extends FactType
        implements Serializable
{

  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  @XmlElement(name = "specimenDate", required = true, type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date specimenDate;
  @XmlElement(name = "specimen", required = true)
  protected String specimen;
  @XmlElement(name = "resultDate", required = true, type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date resultDate;
  @XmlElement(name = "codedTestType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedTestType;
  @XmlElement(name = "freeTextTestType")
  protected String freeTextTestType;
  @XmlElement(name = "resultStatus", required = true)
  protected String resultStatus;
  @XmlElement(name = "resultValue", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair resultValue;
  @XmlElement(name = "resultFreeText")
  protected String resultFreeText;
  @XmlElement(name = "codedResultInterpretation", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedResultInterpretation;
  @XmlElement(name = "lowReferenceRange", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair lowReferenceRange;
  @XmlElement(name = "hiReferenceRange", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair hiReferenceRange;
  @XmlElement(name = "codedRefRangeInterpretationCode", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedRefRangeInterpretationCode;
  @XmlElement(name = "refRangePrecondition", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<PreConditionFactType> refRangePrecondition;
  @XmlElement(name = "codedPanelType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedPanelType;
  @XmlElement(name = "freeTextPanelType")
  protected String freeTextPanelType;
  /** the person who ordered this lab test */
  @XmlElement(name = "orderingProvider", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType orderingProvider;


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
   * Gets the value of the patientId property.
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
   * Gets the value of the specimenDate property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getSpecimentDate() {
    return specimenDate;
  }

  /**
   * Sets the value of the specimenDate property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setSpecimentDate(Date value) {
    this.specimenDate = value;
  }

  /**
   * Gets the value of the specimen property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSpeciment() {
    return specimen;
  }

  /**
   * Sets the value of the specimen property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *specimen
   */
  public void setSpecimen(String value) {
    this.specimen = value;
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
   * Gets the value of the codedTestType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedTestType() {
    return codedTestType;
  }

  /**
   * Sets the value of the codedTestType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedTestType(CodeLabelPair value) {
    this.codedTestType = value;
  }

  /**
   * Gets the value of the freeTextTestType property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextTestType() {
    return freeTextTestType;
  }

  /**
   * Sets the value of the freeTextTestType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextTestType(String value) {
    this.freeTextTestType = value;
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
   * Gets the value of the resultFreeText property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getResultFreeText() {
    return resultFreeText;
  }

  /**
   * Sets the value of the resultFreeText property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setResultFreeText(String value) {
    this.resultFreeText = value;
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

  /**
   * Gets the value of the codedRefRangeInterpretationCode property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedRefRangeInterpretationCode() {
    return codedRefRangeInterpretationCode;
  }

  /**
   * Sets the value of the codedRefRangeInterpretationCode property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedRefRangeInterpretationCode(CodeLabelPair value) {
    this.codedRefRangeInterpretationCode = value;
  }

  /**
   * Gets the value of the refRangePrecondition property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the refRangePrecondition property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getRefRangePrecondition().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link PreConditionFactType }
   *
   *
   */
  public List<PreConditionFactType> getRefRangePrecondition() {
    if (refRangePrecondition == null) {
      refRangePrecondition = new ArrayList<PreConditionFactType>();
    }
    return this.refRangePrecondition;
  }

  /**
   * Gets the value of the codedPanelType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedPanelType() {
    return codedPanelType;
  }

  /**
   * Sets the value of the codedPanelType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedPanelType(CodeLabelPair value) {
    this.codedPanelType = value;
  }

  /**
   * Gets the value of the freeTextPanelType property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextPanelType() {
    return freeTextPanelType;
  }

  /**
   * Sets the value of the freeTextPanelType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextPanelType(String value) {
    this.freeTextPanelType = value;
  }

  /**
   * Gets the value of the orderingProvider property.
   *
   * @return
   *     possible object is
   *     {@link NameFactType }
   *
   */
  public NameFactType getOrderingProvider() {
    return orderingProvider;
  }

  /**
   * Sets the value of the orderingProvider property.
   *
   * @param value
   *     allowed object is
   *     {@link NameFactType }
   *
   */
  public void setOrderingProvider(NameFactType orderingProvider) {
    this.orderingProvider = orderingProvider;
  }
  
  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  public void setRefRangePrecondition(ArrayList<PreConditionFactType> precondition) {
    this.refRangePrecondition = precondition;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ResultFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  orderingProvider=" + (orderingProvider != null? orderingProvider: "") + System.getProperty("line.separator"));
    str.append("  specimenDate=" + specimenDate + System.getProperty("line.separator"));
    str.append("  specimen=" + specimen + System.getProperty("line.separator"));
    str.append("  resultDate=" + resultDate + System.getProperty("line.separator"));
    str.append("  codedTestType=" + (codedTestType != null ? codedTestType : "") + System.getProperty("line.separator"));
    str.append("  freeTextTestType=" + freeTextTestType + System.getProperty("line.separator"));
    str.append("  codedPanelType=" + (codedPanelType != null ? codedPanelType : "") + System.getProperty("line.separator"));
    str.append("  freeTextPanelType=" + freeTextPanelType + System.getProperty("line.separator"));
    str.append("  resultStatus=" + resultStatus + System.getProperty("line.separator"));
    str.append("  resultValue=" + (resultValue != null ? resultValue : "") + System.getProperty("line.separator"));
    str.append("  resultText=" + resultFreeText + System.getProperty("line.separator"));
    str.append("  codedResultInterpretation=" + (codedResultInterpretation != null ? codedResultInterpretation : "") + System.getProperty("line.separator"));
    str.append("  lowReferenceRange=" + (lowReferenceRange != null ? lowReferenceRange : "") + System.getProperty("line.separator"));
    str.append("  hiReferenceRange=" + (hiReferenceRange != null ? hiReferenceRange : "") + System.getProperty("line.separator"));
    str.append("  codedRefRangeInterpretationCode=" + (codedRefRangeInterpretationCode != null ? codedRefRangeInterpretationCode : "") + System.getProperty("line.separator"));
    str.append("  #refRangePrecondition=" + getRefRangePrecondition().size() + System.getProperty("line.separator"));
    for (int i = 0; i < getRefRangePrecondition().size(); i++) {
      str.append("    |--> #" + i + "=" + refRangePrecondition.get(i) + System.getProperty("line.separator"));
    }
    str.append("]");

    return str.toString();
  }
}
