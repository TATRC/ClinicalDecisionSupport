package mil.navy.med.dzreg.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for AddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="streetAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="streetAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", propOrder = {
  "streetAddress",
  "streetAddress2",
  "city",
  "state",
  "postalCode"
})
public class AddressType {

  @XmlElement(name = "streetAddress", required = true)
  protected String streetAddress;
  @XmlElement(name = "streetAddress2")
  protected String streetAddress2;
  @XmlElement(name = "city", required = true)
  protected String city;
  @XmlElement(name = "state", required = true)
  protected String state;
  @XmlElement(name = "postalCode", required = true)
  protected String postalCode;

  /**
   * Gets the value of the streetAddress property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getStreetAddress() {
    return streetAddress;
  }

  /**
   * Sets the value of the streetAddress property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setStreetAddress(String value) {
    this.streetAddress = value;
  }

  /**
   * Gets the value of the streetAddress2 property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getStreetAddress2() {
    return streetAddress2;
  }

  /**
   * Sets the value of the streetAddress2 property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setStreetAddress2(String value) {
    this.streetAddress2 = value;
  }

  /**
   * Gets the value of the city property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the value of the city property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCity(String value) {
    this.city = value;
  }

  /**
   * Gets the value of the state property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the value of the state property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setState(String value) {
    this.state = value;
  }

  /**
   * Gets the value of the postalCode property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * Sets the value of the postalCode property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPostalCode(String value) {
    this.postalCode = value;
  }

  //----------------------------------------------------------------------------
  // Add any custom codes here
  //----------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("AddressFactType[streetAddress=" + streetAddress);
    str.append(",streetAddress2=" + streetAddress + ",state=" + state);
    str.append(",city=" + city + ",state=" + state);
    str.append(",postalCode=" + postalCode + "]");

    return str.toString();
  }
}
