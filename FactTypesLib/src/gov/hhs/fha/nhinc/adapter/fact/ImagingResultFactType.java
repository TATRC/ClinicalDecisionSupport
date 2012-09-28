package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for ImagingResultFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ImagingResultFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="procedureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="procedureCode" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextProcedureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetSiteCode" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="codedObservationType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextImpressions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codedImpressions" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="referenceImage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reportStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resultDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImagingResultFactType", propOrder = {
  "patientId",
  "procedureDate",
  "procedureCode",
  "freeTextProcedureCode",
  "targetSiteCode",
  "codedObservationType",
  "freeTextImpressions",
  "codedImpressions",
  "referenceImage",
  "reportStatus",
  "resultDate"
})
public class ImagingResultFactType
        extends FactType
        implements Serializable
{

  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  @XmlElement(name = "procedureDate", required = true, type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date procedureDate;
  @XmlElement(name = "procedureCode", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair procedureCode;
  @XmlElement(name = "freeTextProcedureCode")
  protected String freeTextProcedureCode;
  @XmlElement(name = "targetSiteCode", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair targetSiteCode;
  @XmlElement(name = "freeTextImpressions", required = true)
  protected String freeTextImpressions;
  @XmlElement(name = "codedImpressions", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedImpressions;
  @XmlElement(name = "codedObservationType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedObservationType;
  @XmlElement(name = "referenceImage", required = true)
  protected String referenceImage;
  @XmlElement(name = "reportStatus", required = true)
  protected String reportStatus;
  @XmlElement(name = "resultDate", required = true, type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date resultDate;


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
   * Gets the value of the procedureDate property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getProcedureDate() {
    return procedureDate;
  }

  /**
   * Sets the value of the procedureDate property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setProcedureDate(Date value) {
    this.procedureDate = value;
  }

  /**
   * Gets the value of the procedureCode property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getProcedureCode() {
    return procedureCode;
  }

  /**
   * Sets the value of the procedureCode property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setProcedureCode(CodeLabelPair value) {
    this.procedureCode = value;
  }

  /**
   * Gets the value of the freeTextProcedureCode property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextProcedureCode() {
    return freeTextProcedureCode;
  }

  /**
   * Sets the value of the freeTextProcedureCode property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextProcedureCode(String value) {
    this.freeTextProcedureCode = value;
  }

  /**
   * Gets the value of the targetSiteCode property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getTargetSiteCode() {
    return targetSiteCode;
  }

  /**
   * Sets the value of the targetSiteCode property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setTargetSiteCode(CodeLabelPair value) {
    this.targetSiteCode = value;
  }

  /**
   * Gets the value of the codedObservationType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedObservationType() {
    return codedObservationType;
  }

  /**
   * Sets the value of the codedObservationType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedObservationType(CodeLabelPair value) {
    this.codedObservationType = value;
  }

  /**
   * Gets the value of the codedImpressions property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedImpressions() {
    return codedImpressions;
  }

  /**
   * Sets the value of the codedImpressions property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedImpressions(CodeLabelPair value) {
    this.codedImpressions = value;
  }

  /**
   * Gets the value of the reportStatus property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getReportStatus() {
    return reportStatus;
  }

  /**
   * Sets the value of the reportStatus property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setReportStatus(String value) {
    this.reportStatus = value;
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
   * Gets the value of the referenceImage property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getReferenceImage() {
    return referenceImage;
  }

  /**
   * Sets the value of the referenceImage property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setReferenceImage(String value) {
    this.referenceImage = value;
  }

  /**
   * Gets the value of the freeTextImpressions property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextImpressions() {
    return freeTextImpressions;
  }

  /**
   * Sets the value of the freeTextImpressions property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextImpressions(String value) {
    this.freeTextImpressions = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ImagingResultFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  procedureDate=" + procedureDate + System.getProperty("line.separator"));
    str.append("  freeTextProcedureCode=" + freeTextProcedureCode + System.getProperty("line.separator"));
    str.append("  procedureCode=" + (procedureCode != null ? procedureCode : "") + System.getProperty("line.separator"));
    str.append("  freeTextImpressions=" + freeTextImpressions + System.getProperty("line.separator"));
    str.append("  codedImpressions=" + (codedImpressions != null ? codedImpressions : "") + System.getProperty("line.separator"));
    str.append("  referenceImage=" + referenceImage + System.getProperty("line.separator"));
    str.append("  reportStatus=" + reportStatus + System.getProperty("line.separator"));
    str.append("  resultDate=" + (resultDate != null ? resultDate : "") + System.getProperty("line.separator"));
    str.append("  targetSiteCode=" + (targetSiteCode != null ? targetSiteCode : "") + System.getProperty("line.separator"));
    str.append("  codedObservationType=" + (codedObservationType != null ? codedObservationType : "") + System.getProperty("line.separator"));
    str.append("]");

    return str.toString();
  }
}
