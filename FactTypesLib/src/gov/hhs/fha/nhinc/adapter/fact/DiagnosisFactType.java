package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for EncounterFactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiagnosisFactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="encounterId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="codedEncounterType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="encounterStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codedDiagnosisType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="codedDiagnosis" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextDiagnosis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiagnosisFactType", propOrder = {
  "patientId",
  "encounterId",
  "codedEncounterType",
  "encounterStatus",
  "codedDiagnosisType",
  "codedDiagnosis",
  "freeTextDiagnosis"
})
public class DiagnosisFactType
        extends FactType
        implements Serializable {

  /** uses FactType->id to carry unique encounter-diagnosis id */

  /**
   * Unique identifier for patient within the patient care management system.
   */
  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;

  /**
   * Unique identifier of the encounter for which this diagnosis was observed.
   */
  @XmlElement(name = "encounterId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType encounterId;

  /**
   * A code value describing the type of this encounter.
   * Note: THIS IS TO BE USE TO CAPTURE E&M CODING!!!
   * TO CAPTURE THE inpatient, outpatient ... USE codedPatientClass ELEMENT.
   */
  @XmlElement(name = "codedEncounterType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedEncounterType;

  /**
   * Status of this encounter.
   * "active" - uses in notifications about an active encounter (admission/appointment)
   * "completed" - uses to notify that an encounter has ended normally
   * "aborted" - uses to notify that an encounter was aborted prior to normal completion.
   * "new" - uses to notify that a new admission or appointment has or will occur.
   */
  @XmlElement(name = "encounterStatus", required = true)
  protected String encounterStatus;

  /**
   * A code value describing the type of diagnosis.
   */
  @XmlElement(name = "codedDiagnosisType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedDiagnosisType;

  /**
   * The diagnosis value using standard vocabulary such as ICD-9-CM Codes System.
   */
  @XmlElement(name = "diagnosis", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedDiagnosis;

  /**
   * A free text description of this diagnosis.
   */
  @XmlElement(name = "freeTextDiagnosis")
  protected String freeTextDiagnosis;


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
   * Sets the value of the patientId property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setPatientId(ValueType patientId) {
    this.patientId = patientId;
  }

  /**
   * Gets the value of the encounterId property.
   *
   * @return
   *     possible object is
   *     {@link ValueType }
   *
   */
  public ValueType getEncounterId() {
    return encounterId;
  }

  /**
   * Sets the value of the encounterId property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setEncounterId(ValueType encounterId) {
    this.patientId = encounterId;
  }

  /**
   * Gets the value of the codedEncounterType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedEncounterType() {
    return codedEncounterType;
  }

  /**
   * Sets the value of the codedEncounterType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCodedEncounterType(CodeLabelPair value) {
    this.codedEncounterType = value;
  }

  /**
   * Gets the value of the freeTextDiagnosis property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextDiagnosis() {
    return freeTextDiagnosis;
  }

  /**
   * Sets the value of the freeTextDiagnosis property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextDiagnosis(String value) {
    this.freeTextDiagnosis = value;
  }

  /**
   * Gets the value of the encounterStatus property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getEncounterStatus() {
    return encounterStatus;
  }

  /**
   * Sets the value of the encounterStatus property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setEncounterStatus(String value) {
    this.encounterStatus = value;
  }

  /**
   * Gets the value of the codedDiagnosisType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedDiagnosisType() {
    return codedDiagnosisType;
  }

  /**
   * Sets the value of the codedDiagnosisType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedAdmissionType(CodeLabelPair value) {
    this.codedDiagnosisType = value;
  }

  /**
   * Gets the value of the codedDiagnosis property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedDiagnosis() {
    return codedDiagnosis;
  }

  /**
   * Sets the value of the codedDiagnosis property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedDiagnosis(CodeLabelPair value) {
    this.codedDiagnosis = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("DiagnosisFactType(encounter id=" + encounterId + "|patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  codedEncounterType=" + (codedEncounterType != null ? codedEncounterType : "") + System.getProperty("line.separator"));
    str.append("  encounterStatus=" + encounterStatus + System.getProperty("line.separator"));
    str.append("  codedDiagnosisType=" + (codedDiagnosisType != null ? codedDiagnosisType : "") + System.getProperty("line.separator"));
    str.append("  codedDiagnosis=" + (codedDiagnosis != null ? codedDiagnosis : "") + System.getProperty("line.separator"));
    str.append("  freeTextDiagnosis=" + freeTextDiagnosis);
    str.append("]");

    return str.toString();
  }
}
