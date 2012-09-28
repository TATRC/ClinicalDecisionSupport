/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * <p>Java class for ImmunizationFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ImmunizationFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="codedProduct" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextProduct" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedBrandName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="freeTextBrandName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lotNumberText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="medSeriesNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="administeredDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="refusalIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="refusalReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reaction" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ReactionFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="performer" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" minOccurs="0"/>
 *         &lt;element name="codedImmunSource" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImmunizationFactType", propOrder = {
  "patientId",
  "codedProduct",
  "freeTextProduct",
  "codedBrandName",
  "freeTextBrandName",
  "lotNumberText",
  "medSeriesNumber",
  "administeredDate",
  "refusalIndicator",
  "refusalReason",
  "reaction",
  "performer",
  "codedImmunSource"
})
public class ImmunizationFactType
        extends FactType
        implements Serializable {

  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  @XmlElement(name = "freeTextProductName", required = true)
  protected String freeTextProduct;
  @XmlElement(name = "freeTextBrandName")
  protected String freeTextBrandName;
  @XmlElement(name = "codedProduct", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedProduct;
  @XmlElement(name = "codedBrandName", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedBrandName;
  @XmlElement(name = "lotNumberText")
  protected String lotNumberText;
  @XmlElement(name = "administeredDate", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date administeredDate;
  @XmlElement(name = "refusalIndicator")
  protected Boolean refusalIndicator;
  @XmlElement(name = "refusalReason")
  protected String refusalReason;
  @XmlElement(name = "reaction", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<ReactionFactType> reaction;
  @XmlElement(name = "performer", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  private NameFactType performer;
  @XmlElement(name = "codedImmunSource", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  private CodeLabelPair codedImmunSource;
  @XmlElement(name = "medSeriesNumber", required = true)
  private String medSeriesNumber;

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
   * Gets the value of the codedProduct property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedProduct() {
    return codedProduct;
  }

  /**
   * Sets the value of the codedProduct property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedProduct(CodeLabelPair value) {
    this.codedProduct = value;
  }

  /**
   * Gets the value of the codedBrandName property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedBrandName() {
    return codedBrandName;
  }

  /**
   * Sets the value of the codedBrandName property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedBrandName(CodeLabelPair value) {
    this.codedBrandName = value;
  }

  /**
   * Gets the value of the freeTextProduct property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextProduct() {
    return freeTextProduct;
  }

  /**
   * Sets the value of the freeTextProduct property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextProduct(String value) {
    this.freeTextProduct = value;
  }

  /**
   * Gets the value of the freeTextBrandName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFreeTextBrandName() {
    return freeTextBrandName;
  }

  /**
   * Sets the value of the freeTextBrandName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFreeTextBrandName(String value) {
    this.freeTextBrandName = value;
  }

  /**
   * Gets the value of the administeredDate property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getAdministeredDate() {
    return administeredDate;
  }

  /**
   * Sets the value of the administeredDate property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setAdministeredDate(Date value) {
    this.administeredDate = value;
  }

  /**
   * Gets the value of the lotNumberText property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getLotNumberText() {
    return lotNumberText;
  }

  /**
   * Sets the value of the lotNumberText property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setLotNumberText(String value) {
    this.lotNumberText = value;
  }

  /**
   * Gets the value of the refusalIndicator property.
   *
   * @return
   *     possible object is
   *     {@link Boolean }
   *
   */
  public Boolean isRefusalIndicator() {
    return refusalIndicator;
  }

  /**
   * Sets the value of the refusalIndicator property.
   *
   * @param value
   *     allowed object is
   *     {@link Boolean }
   *
   */
  public void setRefusalIndicator(Boolean value) {
    this.refusalIndicator = value;
  }

  /**
   * Gets the value of the refusalReason property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getRefusalReason() {
    return refusalReason;
  }

  /**
   * Sets the value of the refusalReason property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setRefusalReason(String value) {
    this.refusalReason = value;
  }

  /**
   * Gets the value of the reaction property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the reaction property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getReaction().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link ReactionFactType }
   *
   *
   */
  public List<ReactionFactType> getReaction() {
    if (reaction == null) {
      reaction = new ArrayList<ReactionFactType>();
    }
    return this.reaction;
  }

  /**
   * Gets the value of the medSeriesNumber property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getMedSeriesNumber() {
    return medSeriesNumber;
  }

  /**
   * Sets the value of the medSeriesNumber property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setMedSeriesNumber(String value) {
    this.medSeriesNumber = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  public void setReaction(ArrayList<ReactionFactType> reaction) {
    this.reaction = reaction;
  }

  /**
   * @return the performer
   */
  public NameFactType getPerformer() {
    return performer;
  }

  /**
   * @param performer the performer to set
   */
  public void setPerformer(NameFactType performer) {
    this.performer = performer;
  }

  /**
   * @return the codedImmunSource
   */
  public CodeLabelPair getCodedImmunSource() {
    return codedImmunSource;
  }

  /**
   * @param codedImmunSource the codedImmunSource to set
   */
  public void setCodedImmunSource(CodeLabelPair codedImmunSource) {
    this.codedImmunSource = codedImmunSource;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ImmunizationFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  codedProduct=" + (codedProduct != null ? codedProduct : "") + System.getProperty("line.separator"));
    str.append("  freeTextProduct=" + freeTextProduct + System.getProperty("line.separator"));
    str.append("  codedBrandName=" + (codedBrandName != null ? codedBrandName : "") + System.getProperty("line.separator"));
    str.append("  freeTextBrandName=" + freeTextBrandName + System.getProperty("line.separator"));
    str.append("  administeredDate=" + administeredDate + System.getProperty("line.separator"));
    str.append("  lotNumberText=" + lotNumberText + ",medSeriesNumber=" + medSeriesNumber + System.getProperty("line.separator"));
    str.append("  codedImmunSource=" + getCodedImmunSource() + System.getProperty("line.separator"));
    str.append("  performer=" + (getPerformer() != null ? getPerformer() : "") + System.getProperty("line.separator"));
    str.append("  refusalIndicator=" + refusalIndicator + System.getProperty("line.separator"));
    str.append("  refusalReason=" + refusalReason + System.getProperty("line.separator"));
    str.append("  #reactions=" + getReaction().size() + System.getProperty("line.separator"));
    for (int i = 0; i < getReaction().size(); i++) {
      str.append("    |--> #" + i + reaction.get(i) + System.getProperty("line.separator"));
    }

    str.append("]");

    return str.toString();
  }
}
