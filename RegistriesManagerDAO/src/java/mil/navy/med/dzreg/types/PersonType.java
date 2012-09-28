package mil.navy.med.dzreg.types;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for PersonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deersIdentifier" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="fmpssn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="address" type="{urn:mil:navy:med:dzreg:types}AddressType" minOccurs="0"/>
 *         &lt;element name="homePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="officePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonType", propOrder = {
  "id",
  "name",
  "deersIdentifier",
  "fmpssn",
  "dateOfBirth",
  "address",
  "homePhone",
  "officePhone",
  "dataSource"
})
public class PersonType {

  @XmlElement(name = "id", required = true)
  protected long id;
  @XmlElement(name = "name", required = true)
  protected String name;
  @XmlElement(name = "deersIdentifier")
  protected Long deersIdentifier;
  @XmlElement(name = "fmpssn")
  protected String fmpssn;
  @XmlElement(name = "dateOfBirth", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date dateOfBirth;
  @XmlElement(name = "address")
  protected AddressType address;
  @XmlElement(name = "homePhone")
  protected String homePhone;
  @XmlElement(name = "officePhone")
  protected String officePhone;
  @XmlElement(name = "dataSource")
  protected String dataSource;

  /**
   * Gets the value of the id property.
   *
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the value of the id property.
   *
   */
  public void setId(long value) {
    this.id = value;
  }

  /**
   * Gets the value of the name property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the name property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setName(String value) {
    this.name = value;
  }

  /**
   * Gets the value of the deersIdentifier property.
   *
   * @return
   *     possible object is
   *     {@link Long }
   *
   */
  public Long getDeersIdentifier() {
    return deersIdentifier;
  }

  /**
   * Sets the value of the deersIdentifier property.
   *
   * @param value
   *     allowed object is
   *     {@link Long }
   *
   */
  public void setDeersIdentifier(Long value) {
    this.deersIdentifier = value;
  }

  /**
   * Gets the value of the fmpssn property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFmpssn() {
    return fmpssn;
  }

  /**
   * Sets the value of the fmpssn property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFmpssn(String value) {
    this.fmpssn = value;
  }

  /**
   * Gets the value of the dateOfBirth property.
   *
   * @return
   *     possible object is
   *     {@link XMLGregorianCalendar }
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
   *     {@link XMLGregorianCalendar }
   *
   */
  public void setDateOfBirth(Date value) {
    this.dateOfBirth = value;
  }

  /**
   * Gets the value of the address property.
   *
   * @return
   *     possible object is
   *     {@link AddressType }
   *
   */
  public AddressType getAddress() {
    return address;
  }

  /**
   * Sets the value of the address property.
   *
   * @param value
   *     allowed object is
   *     {@link AddressType }
   *
   */
  public void setAddress(AddressType value) {
    this.address = value;
  }

  /**
   * Gets the value of the homePhone property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getHomePhone() {
    return homePhone;
  }

  /**
   * Sets the value of the homePhone property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setHomePhone(String value) {
    this.homePhone = value;
  }

  /**
   * Gets the value of the officePhone property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getOfficePhone() {
    return officePhone;
  }

  /**
   * Sets the value of the officePhone property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setOfficePhone(String value) {
    this.officePhone = value;
  }

  /**
   * Gets the value of the dataSource property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getDataSource() {
    return dataSource;
  }

  /**
   * Sets the value of the dataSource property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setDataSource(String value) {
    this.dataSource = value;
  }

  //----------------------------------------------------------------------------
  // Add any custom codes here
  //----------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("PersonType[id=" + id);
    str.append(",fmpssn=" + fmpssn + ",deersIdentifier=" + deersIdentifier + System.getProperty("line.separator"));
    str.append(",legalName=" + name + ",dateOfBirth=" + dateOfBirth + System.getProperty("line.separator"));
    str.append(",homePhone=" + homePhone + ",officePhone=" + officePhone + System.getProperty("line.separator"));
    str.append(",address=" + address + System.getProperty("line.separator"));
    str.append(",dataSource=" + dataSource + "]");

    return str.toString();
  }
}
