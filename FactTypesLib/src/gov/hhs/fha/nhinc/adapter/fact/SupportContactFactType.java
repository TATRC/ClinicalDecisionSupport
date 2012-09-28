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
 * <p>Java class for SupportSourceFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SupportSourceFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="contactOfPatient" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="codedContactType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="codedRelationship" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="legalName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="otherName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="telecom" type="{urn:gov:hhs:fha:nhinc:adapter:fact}TelecomFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="address" type="{urn:gov:hhs:fha:nhinc:adapter:fact}AddressFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="age" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair" minOccurs="0"/>
 *         &lt;element name="gender" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupportSourceFactType", propOrder = {
  "contactOfPatient",
  "codedContactType",
  "codedRelationship",
  "legalName",
  "otherName",
  "telecom",
  "address",
  "dateOfBirth",
  "age",
  "gender"
})
public class SupportContactFactType
        extends FactType
        implements Serializable {

  @XmlElement(name = "contactOfPatient", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType contactOfPatient;
  @XmlElement(name = "codedContactType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedContactType;
  @XmlElement(name = "codedRelationship", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedRelationship;
  @XmlElement(name = "legalName", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType legalName;
  @XmlElement(name = "otherName", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<NameFactType> otherName;
  //@XmlElementWrapper(name="telecom", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact", required = true)
  @XmlElement(name = "telecom", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact", nillable=true)
  protected List<TelecomFactType> telecom;
  @XmlElement(name = "address", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<AddressFactType> address;
  /**
   * The date and time of birth of this patient.
   */
  @XmlElement(name = "dateOfBirth", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date dateOfBirth;
  /**
   * Gender of this patient.
   */
  @XmlElement(name = "gender", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair gender;
  /**
   * The Person‟s age. This is normally a value derived, but in some cases this may be
   * the only information provided (no birth date).
   */
  @XmlElement(name = "age", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair age;

  
  //---------------------------------------------------------------------------
  // JAXB generated codes
  //---------------------------------------------------------------------------

  /**
   * Sets the value of the contactOfPatient property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setContactOfPatient(ValueType value) {
    this.contactOfPatient = value;
  }

  /**
   * Gets the value of the contactOfPatient property.
   *
   * @return
   *     possible object is
   *     {@link ValueType }
   *
   */
  public ValueType getContactOfPatient() {
    return contactOfPatient;
  }
  
  /**
   * Gets the value of the legalName property.
   *
   * @return
   *     possible object is
   *     {@link NameFactType }
   *
   */
  public NameFactType getLegalName() {
    return legalName;
  }

  /**
   * Sets the value of the legalName property.
   *
   * @param value
   *     allowed object is
   *     {@link NameFactType }
   *
   */
  public void setLegalName(NameFactType value) {
    this.legalName = value;
  }

  /**
   * Gets the value of the otherName property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the otherName property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getOtherName().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link NameFactType }
   *
   *
   */
  public List<NameFactType> getOtherName() {
    if (otherName == null) {
      otherName = new ArrayList<NameFactType>();
    }
    return this.otherName;
  }

  /**
   * Gets the value of the codedContactType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedContactType() {
    return codedContactType;
  }

  /**
   * Sets the value of the codedContactType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedContactType(CodeLabelPair value) {
    this.codedContactType = value;
  }

  /**
   * Gets the value of the codedRelationship property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedRelationship() {
    return codedRelationship;
  }

  /**
   * Sets the value of the codedRelationship property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedRelationship(CodeLabelPair value) {
    this.codedRelationship = value;
  }

  /**
   * Gets the value of the telecom property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the telecom property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getTelecom().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link TelecomFactType }
   *
   *
   */
  public List<TelecomFactType> getTelecom() {
    if (telecom == null) {
      telecom = new ArrayList<TelecomFactType>();
    }
    return this.telecom;
  }

  /**
   * Gets the value of the address property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the address property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getAddress().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link AddressFactType }
   *
   *
   */
  public List<AddressFactType> getAddress() {
    if (address == null) {
      address = new ArrayList<AddressFactType>();
    }
    return this.address;
  }

  /**
   * Gets the value of the dateOfBirth property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * Sets the value of the dateOfBirth property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setDateOfBirth(Date value) {
    this.dateOfBirth = value;
    if (dateOfBirth != null) {
      this.setAge(new ValueUnitPair(String.valueOf(calculateAgeInYears(value)), "yrs"));
    }
  }

  /**
   * Gets the value of the gender property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getGender() {
    return gender;
  }

  /**
   * Sets the value of the gender property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setGender(CodeLabelPair value) {
    this.gender = value;
  }

  /**
   * Gets the value of the age property.
   *
   * @return
   *     possible object is
   *     {@link ValueUnitPair }
   *
   */
  public ValueUnitPair getAge() {
    return age;
  }

  /**
   * Sets the value of the age property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueUnitPair }
   *
   */
  public void setAge(ValueUnitPair value) {
    this.age = value;
  }
  
  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  public void setTelecom(ArrayList<TelecomFactType> telecom) {
    this.telecom = telecom;
  }

  public void setAddress(ArrayList<AddressFactType> address) {
    this.address = address;
  }

  
  public void setOtherName(ArrayList<NameFactType> name) {
    this.otherName = name;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("SupportContactFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  contactOfPatient=" + (contactOfPatient != null? contactOfPatient: "") + System.getProperty("line.separator"));
    str.append("  codedContactType=" + (codedContactType != null? codedContactType: "") + System.getProperty("line.separator"));
    str.append("  codedRelationship=" + (codedRelationship != null? codedRelationship: "") + System.getProperty("line.separator"));
    str.append("  legalName=" + legalName + System.getProperty("line.separator"));
    str.append("  dateOfBirth=" + dateOfBirth + ", age=" + age + System.getProperty("line.separator"));
    str.append("  gender=" + (gender != null? gender: "") + System.getProperty("line.separator"));
    str.append("  #otherName=" + getOtherName().size() + System.getProperty("line.separator"));
    for (int i = 0; i < getOtherName().size(); i++) {
      str.append("    |--> #" + i + "=" + otherName.get(i) + System.getProperty("line.separator"));
    }
    str.append("  #telecoms=" + getTelecom().size() + System.getProperty("line.separator"));
    for (int i = 0; i < getTelecom().size(); i++) {
      str.append("    |--> #" + i + "=" + telecom.get(i) + System.getProperty("line.separator"));
    }
    str.append("  #addresses=" + getAddress().size() + System.getProperty("line.separator"));
    for (int i = 0; i < getAddress().size(); i++) {
      str.append("    |--> #" + i + "=" + address.get(i) + System.getProperty("line.separator"));
    }
    str.append("]");

    return str.toString();
  }
}
