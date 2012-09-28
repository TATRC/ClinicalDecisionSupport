package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for AddressFactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressFactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="streetAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="addressType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressFactType", propOrder = {
  "streetAddress",
  "city",
  "state",
  "postalCode",
  "country",
  "addressType"
})
public class AddressFactType implements Serializable {

  @XmlElement(name = "streetAddress", required = true)
  protected String streetAddress;
  @XmlElement(name = "city", required = true)
  protected String city;
  @XmlElement(name = "state", required = true)
  protected String state;
  @XmlElement(name = "postalCode", required = true)
  protected String postalCode;
  @XmlElement(name = "country", required = true)
  protected String country;
  @XmlElement(name = "addressType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair addressType;

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

  /**
   * Gets the value of the country property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getCountry() {
    return country;
  }

  /**
   * Sets the value of the country property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setCountry(String value) {
    this.country = value;
  }

  /**
   * Gets the value of the addressType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getAddressType() {
    return addressType;
  }

  /**
   * Sets the value of the addressType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setAddressType(CodeLabelPair value) {
    this.addressType = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("AddressFactType[addressType=" + addressType + System.getProperty("line.separator"));
    str.append("  streetAddress=" + streetAddress + System.getProperty("line.separator"));
    str.append("  city=" + city + ",state=" + state + ",postalCode=" + postalCode + ",country=" + country + "]");

    return str.toString();
  }
}
