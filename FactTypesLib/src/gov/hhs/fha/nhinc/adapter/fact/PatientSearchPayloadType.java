package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for PatientSearchPayloadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientSearchPayloadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType" minOccurs="0"/>
 *         &lt;element name="name" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" minOccurs="0"/>
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="motherMaidenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientSearchPayloadType", propOrder = {
   "id",
   "name",
   "dateOfBirth",
   "gender",
   "motherMaidenName"
})
public class PatientSearchPayloadType implements Serializable {

   @XmlElement(name = "id", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected ValueType id;
   @XmlElement(name = "name", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected NameFactType name;
   @XmlElement(name = "dateOfBirth")
   protected String dateOfBirth;
   @XmlElement(name = "gender")
   protected String gender;
   @XmlElement(name = "motherMaidenName")
   protected String motherMaidenName;

   /**
    * Gets the value of the id property.
    *
    * @return
    *     possible object is
    *     {@link ValueType }
    *
    */
   public ValueType getId() {
      return id;
   }

   /**
    * Sets the value of the id property.
    *
    * @param value
    *     allowed object is
    *     {@link ValueType }
    *
    */
   public void setId(ValueType value) {
      this.id = value;
   }

   /**
    * Gets the value of the name property.
    *
    * @return
    *     possible object is
    *     {@link NameFactType }
    *
    */
   public NameFactType getName() {
      return name;
   }

   /**
    * Sets the value of the name property.
    *
    * @param value
    *     allowed object is
    *     {@link NameFactType }
    *
    */
   public void setName(NameFactType value) {
      this.name = value;
   }

   /**
    * Gets the value of the dateOfBirth property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getDateOfBirth() {
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
   public void setDateOfBirth(String value) {
      this.dateOfBirth = value;
   }

   /**
    * Gets the value of the gender property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getGender() {
      return gender;
   }

   /**
    * Sets the value of the gender property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setGender(String value) {
      this.gender = value;
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

   //---------------------------------------------------------------------------
   // Add any custom codes here
   //---------------------------------------------------------------------------
   @Override
   public String toString() {
      StringBuffer str = new StringBuffer();

      str.append("PatientSearchPayloadType(id=" + (id != null ? id : "") + System.getProperty("line.separator"));
      str.append(",name=" + (name != null ? name : "") + System.getProperty("line.separator"));
      str.append("dateOfBirth=" + dateOfBirth + ",gender=" + gender + ",motherMaidenName=" + motherMaidenName);
      str.append(")");

      return str.toString();
   }
}
