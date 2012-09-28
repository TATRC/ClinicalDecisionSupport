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
 * <p>Java class for ProcedureFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ProcedureFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="procedureType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextProcedureType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="procedureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="treatingProvider" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="codedTargetSite" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcedureFactType", propOrder = {
  "patientId",
  "procedureType",
  "freeTextProcedureType",
  "procedureDate",
  "treatingProvider",
  "codedTargetSite"
})
public class ProcedureFactType
        extends FactType
        implements Serializable
{

  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  @XmlElement(name = "procedureType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair procedureType;
  @XmlElement(name = "freeTextProcedureType")
  protected String freeTextProcedureType;
  @XmlElement(name = "procedureDate", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date procedureDate = null;
  @XmlElement(name = "treatingProvider", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<NameFactType> treatingProvider;
  @XmlElement(name = "codedTargetSite", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<CodeLabelPair> codedTargetSite;


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
   * Gets the value of the procedureType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getProcedureType() {
    return procedureType;
  }

  /**
   * Sets the value of the procedureType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setProcedureType(CodeLabelPair value) {
    this.procedureType = value;
  }

  /**
   * Gets the value of the freeTextProcedureType property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextProcedureType() {
    return freeTextProcedureType;
  }

  /**
   * Sets the value of the freeTextProcedureType property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextProcedureType(String value) {
    this.freeTextProcedureType = value;
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
   * Gets the value of the codedTargetSite property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the codedTargetSite
   * property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getCodedTargetSite().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link NameFactType }
   *
   *
   */
  public List<CodeLabelPair> getCodedTargetSite() {
    if (codedTargetSite == null) {
      codedTargetSite = new ArrayList<CodeLabelPair>();
    }
    return this.codedTargetSite;
  }
  
  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  
  public void setTreatingProvider(ArrayList<NameFactType> treatingProvider) {
    this.treatingProvider = treatingProvider;
  }

  public void setCodedTargetSite(ArrayList<CodeLabelPair> codedTargetSite) {
    this.codedTargetSite = codedTargetSite;
  }
  
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ProcedureFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  procedureType=" + (procedureType != null ? procedureType : "") + System.getProperty("line.separator"));
    str.append("  freeTextProcedureType=" + freeTextProcedureType + System.getProperty("line.separator"));
    str.append("  procedureDate=" + procedureDate + System.getProperty("line.separator"));
    str.append("  #treatingProvider=" + getTreatingProvider().size() + " ==>" + System.getProperty("line.separator"));
    for (int i = 0; i < getTreatingProvider().size(); i++) {
      str.append("    |--> #" + i + "=" + getTreatingProvider().get(i) + System.getProperty("line.separator"));
    }
    str.append("  #codedTargetSite=" + getCodedTargetSite().size() + " ==>" + System.getProperty("line.separator"));
    for (int i = 0; i < getCodedTargetSite().size(); i++) {
      str.append("    |--> #" + i + "=" + getCodedTargetSite().get(i) + System.getProperty("line.separator"));
    }

    str.append("]");

    return str.toString();
  }
}