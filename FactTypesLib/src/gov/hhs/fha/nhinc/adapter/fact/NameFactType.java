package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for NameFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="NameFactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="middleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="familyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}string minOccurs="0"/>
 *         &lt;element name="suffix" type="{http://www.w3.org/2001/XMLSchema}string minOccurs="0"/>
 *         &lt;element name="nameType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NameFactType", propOrder = {
  "firstName",
  "middleName",
  "familyName",
  "prefix",
  "suffix",
  "nameType"
})
public class NameFactType implements Serializable {

  @XmlElement(name = "firstName", required = true)
  protected String firstName;
  @XmlElement(name = "middleName")
  protected String middleName;
  @XmlElement(name = "familyName", required = true)
  protected String familyName;
  @XmlElement(name = "prefix")
  protected String prefix;
  @XmlElement(name = "suffix")
  protected String suffix;
  @XmlElement(name = "nameType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair nameType;

  /**
   * Gets the value of the firstName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the value of the firstName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFirstName(String value) {
    this.firstName = value;
  }

  /**
   * Gets the value of the middleName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * Sets the value of the middleName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setMiddleName(String value) {
    this.middleName = value;
  }

  /**
   * Gets the value of the familyName property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getFamilyName() {
    return familyName;
  }

  /**
   * Sets the value of the familyName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setFamilyName(String value) {
    this.familyName = value;
  }

  /**
   * Gets the value of the prefix property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getPrefix() {
    return prefix;
  }

  /**
   * Sets the value of the prefix property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setPrefix(String value) {
    this.prefix = value;
  }

  /**
   * Gets the value of the suffix property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getSuffix() {
    return suffix;
  }

  /**
   * Sets the value of the suffix property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setSuffix(String value) {
    this.suffix = value;
  }

  /**
   * Gets the value of the nameType property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getNameType() {
    return nameType;
  }

  /**
   * Sets the value of the nameType property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setNameType(CodeLabelPair value) {
    this.nameType = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("NameFactType(nameType=" + nameType);
    str.append(",lastName=" + familyName + ",firstName=" + firstName + ",middle=" + middleName + ",prefix=" + prefix + ",suffix=" + suffix);
    str.append(")");

    return str.toString();
  }
}
