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
 * <p>Java class for ProblemFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ProblemFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="problemType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="codedProblem" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextProblem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="problemDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="resolutionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="codedProblemStatus" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="diagnosisPriority" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="onsetAge" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="treatingProvider" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="causeOfDeath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ageAtDeath" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProblemFactType", propOrder = {
  "patientId",
  "problemType",
  "codedProblem",
  "freeTextProblem",
  "problemDate",
  "resolutionDate",
  "codedProblemStatus",
  "diagnosisPriority",
  "onsetAge",
  "treatingProvider",
  "causeOfDeath",
  "ageAtDeath"
})
public class ProblemFactType
        extends FactType
        implements Serializable
{

  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  @XmlElement(name = "problemType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair problemType;
  @XmlElement(name = "codedProblem", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedProblem;
  @XmlElement(name = "freeTextProblem")
  protected String freeTextProblem;
  @XmlElement(name = "problemDate", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date problemDate = null;
  @XmlElement(name = "resolutionDate", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date resolutionDate = null;
  @XmlElement(name = "codedProblemStatus", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedProblemStatus;
  @XmlElement(name = "diagnosisPriority")
  protected Integer diagnosisPriority;
  @XmlElement(name = "onsetAge")
  protected Integer onsetAge;
  @XmlElement(name = "treatingProvider", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<NameFactType> treatingProvider;
  @XmlElement(name = "causeOfDeath")
  protected String causeOfDeath;  // should change to CodeLabelPair
  @XmlElement(name = "ageAtDeath")
  protected Integer ageAtDeath;
  // add report date of death


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
   * Gets the value of the problemType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getProblemType() {
    return problemType;
  }

  /**
   * Sets the value of the problemType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setProblemType(CodeLabelPair value) {
    this.problemType = value;
  }

  /**
   * Gets the value of the codedProblem property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedProblem() {
    return codedProblem;
  }

  /**
   * Sets the value of the codedProblem property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedProblem(CodeLabelPair value) {
    this.codedProblem = value;
  }

  /**
   * Gets the value of the freeTextProblem property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextProblem() {
    if (freeTextProblem == null || freeTextProblem.isEmpty())
      if (codedProblem != null)
        freeTextProblem = codedProblem.getLabel();
    return freeTextProblem;
  }

  /**
   * Sets the value of the freeTextProblem property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextProblem(String value) {
    this.freeTextProblem = value;
  }

  /**
   * Gets the value of the problemDate property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getProblemDate() {
    return problemDate;
  }

  /**
   * Sets the value of the problemDate property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setProblemDate(Date value) {
    this.problemDate = value;
  }

  /**
   * Gets the value of the resolutionDate property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getResolutionDate() {
    return resolutionDate;
  }

  /**
   * Sets the value of the resolutionDate property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setResolutionDate(Date value) {
    this.resolutionDate = value;
  }

  /**
   * Gets the value of the codedProblemStatus property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedProblemStatus() {
    return codedProblemStatus;
  }

  /**
   * Sets the value of the codedProblemStatus property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedProblemStatus(CodeLabelPair value) {
    this.codedProblemStatus = value;
  }

  /**
   * Gets the value of the diagnosisPriority property.
   *
   * @return
   *     possible object is
   *     {@link Integer }
   */
  public Integer getDiagnosisPriority() {
    return diagnosisPriority;
  }

  /**
   * Sets the value of the diagnosisPriority property.
   *
   */
  public void setDiagnosisPriority(Integer value) {
    this.diagnosisPriority = value;
  }

  /**
   * Gets the value of the onsetAge property.
   *
   * @return
   *     possible object is
   *     {@link Integer }
   */
  public Integer getOnsetAge() {
    return onsetAge;
  }

  /**
   * Sets the value of the onsetAge property.
   *
   */
  public void setOnsetAge(Integer value) {
    this.onsetAge = value;
  }

  /**
   * Gets the value of the treatingProvider property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the treatingProvider property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getTreatingProvider().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link NameFactType }
   *
   *
   */
  public List<NameFactType> getTreatingProvider() {
    if (treatingProvider == null) {
      treatingProvider = new ArrayList<NameFactType>();
    }
    return this.treatingProvider;
  }

  /**
   * Gets the value of the causeOfDeath property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getCauseOfDeath() {
    return causeOfDeath;
  }

  /**
   * Sets the value of the causeOfDeath property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCauseOfDeath(String value) {
    this.causeOfDeath = value;
  }

  /**
   * Gets the value of the ageAtDeath property.
   *
   * @return
   *     possible object is
   *     {@link Integer }
   *
   */
  public Integer getAgeAtDeath() {
    return ageAtDeath;
  }

  /**
   * Sets the value of the ageAtDeath property.
   *
   * @param value
   *     allowed object is
   *     {@link Integer }
   *
   */
  public void setAgeAtDeath(Integer value) {
    this.ageAtDeath = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //--------------------------------------------------------------------------- 
//  public void setTreatingProviderId(ArrayList<String> treatingProviderId) {
//    this.treatingProviderId = treatingProviderId;
//  }
  public void setTreatingProvider(ArrayList<NameFactType> treatingProvider) {
    this.treatingProvider = treatingProvider;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ProblemFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  problemType=" + (problemType != null ? problemType : "") + System.getProperty("line.separator"));
    str.append("  problemDate=" + problemDate + System.getProperty("line.separator"));
    str.append("  resolutionDate=" + resolutionDate + System.getProperty("line.separator"));
    str.append("  codedProblem=" + (codedProblem != null ? codedProblem : "") + System.getProperty("line.separator"));
    str.append("  freeTextProblem=" + freeTextProblem + System.getProperty("line.separator"));
    str.append("  codedProblemStatus=" + codedProblemStatus + System.getProperty("line.separator"));
    str.append("  #treatingProvider=" + getTreatingProvider().size() + " ==>" + System.getProperty("line.separator"));
    for (int i = 0; i < getTreatingProvider().size(); i++) {
      str.append("    |--> #" + i + "=" + getTreatingProvider().get(i) + System.getProperty("line.separator"));
    }
//    str.append("  #treatingProvider ids=" + getTreatingProviderId().size() + "    |--> ");
//    for (int i = 0; i < getTreatingProviderId().size(); i++) {
//      str.append(treatingProviderId.get(i) + "|");
//    }
    str.append("]");

    return str.toString();
  }
}
