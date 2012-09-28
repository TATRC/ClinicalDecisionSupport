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
 * <p>Java class for PersonFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PersonFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="legalName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="otherName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="motherMaidenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="gender" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="maritalStatus" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="race" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="ethnic" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="language" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="telecom" type="{urn:gov:hhs:fha:nhinc:adapter:fact}TelecomFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="multipleBirthInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="birthOrder" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="age" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueUnitPair"/>
 *         &lt;element name="birthPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{urn:gov:hhs:fha:nhinc:adapter:fact}AddressFactType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonFactType", propOrder = {
  "legalName",
  "otherName",
  "motherMaidenName",
  "dateOfBirth",
  "gender",
  "maritalStatus",
  "race",
  "ethnic",
  "language",
  "telecom",
  "multipleBirthInd",
  "birthOrder",
  "age",
  "birthPlace",
  "accountNumber",
  "address"
})
public class PersonFactType
        extends FactType
        implements Serializable {

  /**
   * Legal name of the patient.
   */
  @XmlElement(name = "legalName", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType legalName;

  /**
   * Other names used by the patient.
   */
  //@XmlElementWrapper(name="otherNames", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  @XmlElement(name = "otherName", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<NameFactType> otherName;

  /**
   * The family name under which patient's mother was born.
   */
  @XmlElement(name = "motherMaidenName")
  protected String motherMaidenName;

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
   * A value representing the domestic partnership status of this patient.
   */
  @XmlElement(name = "maritalStatus", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair maritalStatus;

  /**
   * A single valued term that may be constant over that patient's lifetime.
   * The coding of race is aligned with public health and other federal reporting standards
   * of the CDC and the Census Bureau.
   */
  @XmlElement(name = "race", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair race;

  /**
   * A code that extends the concept of race. The coding of ethnicity is aligned with
   * public health and other federal reporting standards of the CDC and the Census Bureau.
   */
  @XmlElement(name = "ethnic", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair ethnic;

  //@XmlElementWrapper(name="languages", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  @XmlElement(name = "language", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<CodeLabelPair> language;
  
  //@XmlElementWrapper(name="telecoms", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact", required = true)
  @XmlElement(name = "telecom", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<TelecomFactType> telecom;

  /**
   * Indicates whether a patient was part of a multiple birth.
   */
  @XmlElement(name = "multipleBirthInd", type = Boolean.class)
  protected Boolean multipleBirthInd;
  
  @XmlElement(name = "birthOrder", type = Integer.class)
  protected Integer birthOrder;

  /**
   * The Person‟s age. This is normally a value derived, but in some cases this may be
   * the only information provided (no birth date).
   */
  @XmlElement(name = "age", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueUnitPair age;

  /**
   * The location of the patient‟s birth.
   */
  @XmlElement(name = "birthPlace")
  protected String birthPlace;
  
  @XmlElement(name = "accountNumber")
  protected String accountNumber;
  
  @XmlElement(name = "address", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<AddressFactType> address;

  //---------------------------------------------------------------------------
  // JAXB generated codes
  //---------------------------------------------------------------------------

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
   * Gets the value of the motherMaidenName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getMotherMaidenName() {
    return motherMaidenName;
  }

  /**
   * Sets the value of the motherMaidenName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setMotherMaidenName(String value) {
    this.motherMaidenName = value;
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
   * Gets the value of the maritalStatus property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getMaritalStatus() {
    return maritalStatus;
  }

  /**
   * Sets the value of the maritalStatus property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setMaritalStatus(CodeLabelPair value) {
    this.maritalStatus = value;
  }

  /**
   * Gets the value of the race property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getRace() {
    return race;
  }

  /**
   * Sets the value of the race property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setRace(CodeLabelPair value) {
    this.race = value;
  }

  /**
   * Gets the value of the ethnic property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getEthnic() {
    return ethnic;
  }

  /**
   * Sets the value of the ethnic property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setEthnic(CodeLabelPair value) {
    this.ethnic = value;
  }

  /**
   * Gets the value of the language property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the language property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getLanguage().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link CodeLabelPair }
   *
   *
   */
  public List<CodeLabelPair> getLanguage() {
    if (language == null) {
      language = new ArrayList<CodeLabelPair>();
    }
    return this.language;
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
   * Gets the value of the multipleBirthInd property.
   *
   * @return
   *     possible object is
   *     {@link Boolean }
   *
   */
  public Boolean isMultipleBirthInd() {
    return multipleBirthInd;
  }

  /**
   * Sets the value of the multipleBirthInd property.
   *
   * @param value
   *     allowed object is
   *     {@link Boolean }
   *
   */
  public void setMultipleBirthInd(Boolean value) {
    this.multipleBirthInd = value;
  }

  /**
   * Gets the value of the birthOrder property.
   *
   * @return
   *     possible object is
   *     {@link Integer }
   *
   */
  public Integer getBirthOrder() {
    return birthOrder;
  }

  /**
   * Sets the value of the birthOrder property.
   *
   * @param value
   *     allowed object is
   *     {@link Integer }
   *
   */
  public void setBirthOrder(Integer value) {
    this.birthOrder = value;
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

  /**
   * Gets the value of the birthPlace property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getBirthPlace() {
    return birthPlace;
  }

  /**
   * Sets the value of the birthPlace property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setBirthPlace(String value) {
    this.birthPlace = value;
  }

  /**
   * Gets the value of the accountNumber property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
   * Sets the value of the accountNumber property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setAccountNumber(String value) {
    this.accountNumber = value;
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

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  public void setLanguage(ArrayList<CodeLabelPair> language) {
    this.language = language;
  }
  
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

    str.append("PersonFactType[" + super.toString() + System.getProperty("line.separator"));
    str.append("  legalName=" + legalName + System.getProperty("line.separator"));
    str.append("  #otherName=" + getOtherName().size() + System.getProperty("line.separator"));
    for (int i = 0; i < getOtherName().size(); i++) {
      str.append("    |--> #" + i + "=" + otherName.get(i) + System.getProperty("line.separator"));
    }
    str.append("  motherMaidenName=" + motherMaidenName + System.getProperty("line.separator"));
    str.append("  dateOfBirth=" + dateOfBirth + ", age=" + age + System.getProperty("line.separator"));
    str.append("  gender=" + (gender != null? gender: "") + System.getProperty("line.separator"));
    str.append("  maritalStatus=" + (maritalStatus != null? maritalStatus: "") + System.getProperty("line.separator"));
    str.append("  race=" + (race != null ? race : "") + System.getProperty("line.separator"));
    str.append("  ethnic=" + (ethnic != null ? ethnic : "") + System.getProperty("line.separator"));
    str.append("  #languages=" + getLanguage().size() + System.getProperty("line.separator"));
    for (int i = 0; i < getLanguage().size(); i++) {
      str.append("    |--> #" + i + "=" + language.get(i) + System.getProperty("line.separator"));
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
