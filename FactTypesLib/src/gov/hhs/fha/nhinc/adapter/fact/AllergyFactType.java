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
 * <p>Java class for AllergyFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="AllergyFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="adverseEventType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="codedProduct" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextProduct" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="adverseEventDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="reaction" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ReactionFactType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AllergyFactType", propOrder = {
  "patientId",
  "adverseEventType",
  "codedProduct",
  "freeTextProduct",
  "adverseEventDate",
  "reaction"
})
public class AllergyFactType
        extends FactType
        implements Serializable {

  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  @XmlElement(name = "adverseEventType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair adverseEventType;
  @XmlElement(name = "codedProduct", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedProduct;
  @XmlElement(name = "freeTextProduct", required = true)
  protected String freeTextProduct;
  @XmlElement(name = "adverseEventDate", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date adverseEventDate;
  @XmlElement(name = "reaction", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<ReactionFactType> reaction;

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
   * Gets the value of the adverseEventType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getAdverseEventType() {
    return adverseEventType;
  }

  /**
   * Sets the value of the adverseEventType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setAdverseEventType(CodeLabelPair value) {
    this.adverseEventType = value;
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
   * Gets the value of the adverseEventDate property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getAdverseEventDate() {
    return adverseEventDate;
  }

  /**
   * Sets the value of the adverseEventDate property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setAdverseEventDate(Date value) {
    this.adverseEventDate = value;
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

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  public void setReaction(ArrayList<ReactionFactType> reaction) {
    this.reaction = reaction;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("AllergyFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  adverseEventType=" + (adverseEventType != null ? adverseEventType : "") + System.getProperty("line.separator"));
    str.append("  adverseEventDate=" + adverseEventDate + System.getProperty("line.separator"));
    str.append("  codedProduct=" + (codedProduct != null ? codedProduct : "") + System.getProperty("line.separator"));
    str.append("  freeTextProduct=" + this.freeTextProduct + System.getProperty("line.separator"));
    str.append("  #reactions=" + getReaction().size() + System.getProperty("line.separator"));
    for (int i = 0; i < getReaction().size(); i++) {
      str.append("    |--> #" + i + reaction.get(i) + System.getProperty("line.separator"));
    }

    str.append("]");

    return str.toString();
  }
}
