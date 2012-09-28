package gov.hhs.fha.nhinc.adapter.fact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for ProviderSearchPayloadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProviderSearchPayloadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" minOccurs="0"/>
 *         &lt;element name="roleClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="serviceDeliveryLocld" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceDeliveryLocType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProviderSearchPayloadType", propOrder = {
   "id",
   "name",
   "roleClass",
   "roleType",
   "serviceDeliveryLocld",
   "serviceDeliveryLocType"
})
public class ProviderSearchPayloadType {

   @XmlElement(name = "id")
   protected String id;
   @XmlElement(name = "name", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected NameFactType name;
   @XmlElement(name = "roleClass")
   protected String roleClass;
   @XmlElement(name = "roleType", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected CodeLabelPair roleType;
   @XmlElement(name = "serviceDeliveryLocld")
   protected String serviceDeliveryLocld;
   @XmlElement(name = "serviceDeliveryLocType", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected CodeLabelPair serviceDeliveryLocType;

   /**
    * Gets the value of the id property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getId() {
      return id;
   }

   /**
    * Sets the value of the id property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setId(String value) {
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
    * Gets the value of the roleClass property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getRoleClass() {
      return roleClass;
   }

   /**
    * Sets the value of the roleClass property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setRoleClass(String value) {
      this.roleClass = value;
   }

   /**
    * Gets the value of the roleClass property.
    *
    * @return
    *     possible object is
    *     {@link CodeLabelPair }
    *
    */
   public CodeLabelPair getRoleType() {
      return roleType;
   }

   /**
    * Sets the value of the roleClass property.
    *
    * @param value
    *     allowed object is
    *     {@link CodeLabelPair }
    *
    */
   public void setRoleType(CodeLabelPair value) {
      this.roleType = value;
   }

   /**
    * Gets the value of the serviceDeliveryLocld property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getServiceDeliveryLocld() {
      return serviceDeliveryLocld;
   }

   /**
    * Sets the value of the serviceDeliveryLocld property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setServiceDeliveryLocld(String value) {
      this.serviceDeliveryLocld = value;
   }

   /**
    * Gets the value of the serviceDeliveryLocld property.
    *
    * @return
    *     possible object is
    *     {@link CodeLabelPair }
    *
    */
   public CodeLabelPair getServiceDeliveryLocType() {
      return serviceDeliveryLocType;
   }

   /**
    * Sets the value of the serviceDeliveryLocld property.
    *
    * @param value
    *     allowed object is
    *     {@link CodeLabelPair }
    *
    */
   public void setServiceDeliveryLocType(CodeLabelPair value) {
      this.serviceDeliveryLocType = value;
   }

   //---------------------------------------------------------------------------
   // Add any custom codes here
   //---------------------------------------------------------------------------
   @Override
   public String toString() {
      StringBuffer str = new StringBuffer();

      str.append("ProviderSearchPayloadType(id=" + id + System.getProperty("line.separator"));
      str.append(",name=" + (name != null ? name : "") + System.getProperty("line.separator"));
      str.append(",roleClass=" + roleClass + System.getProperty("line.separator"));
      str.append(",roleType=" + (roleType != null ? roleType : "") + System.getProperty("line.separator"));
      str.append(",serviceDeliveryLocld=" + serviceDeliveryLocld + System.getProperty("line.separator"));
      str.append(",serviceDeliveryLocType=" + serviceDeliveryLocType + System.getProperty("line.separator"));
      str.append(")");

      return str.toString();
   }
}
