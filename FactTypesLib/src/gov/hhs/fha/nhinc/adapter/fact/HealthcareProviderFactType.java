package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for HealthcareProviderFactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="HealthcareProviderFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="codedProviderType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextProviderType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedRole" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="freeTextRole" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="legalName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="otherName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="telecom" type="{urn:gov:hhs:fha:nhinc:adapter:fact}TelecomFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="address" type="{urn:gov:hhs:fha:nhinc:adapter:fact}AddressFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="organization" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="1"/>
 *         &lt;element name="serviceDeliveryLocation" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="1"/>
 *         &lt;element name="nationalProviderId"  type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HealthcareProviderFactType", propOrder = {
   "codedProviderType",
   "freeTextProviderType",
   "codedRole",
   "freeTextRole",
   "legalName",
   "otherName",
   "telecom",
   "address",
   "organization",
   "serviceDeliveryLocation",
   "nationalProviderId"
})
public class HealthcareProviderFactType
        extends FactType
        implements Serializable {

   /** uses FactType->id to carry unique provider identifier */

   // Provider type classifies providers according to the type of license or accreditation
   // they hold (e.g. physician, dentist, pharmacist, et cetera) or the service they provide.
   @XmlElement(name = "codedProviderType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected CodeLabelPair codedProviderType;

   // Free Text description of provider type.
   @XmlElement(name = "freeTextProviderType")
   protected String freeTextProviderType;

   // Provider role uses a coded value to classify providers according to the role they
   // play in the healthcare of the patient and comes from a very limited set of values.
   @XmlElement(name = "codedRole", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected CodeLabelPair codedRole;

   @XmlElement(name = "freeTextRole", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected String freeTextRole;

   // The legal name of this provider
   @XmlElement(name = "legalName", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected NameFactType legalName;

   // Any other name used by this provider
   @XmlElement(name = "otherName", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected List<NameFactType> otherName;

   // A telephone number (voice or fax), e-mail address or other locator for a resource
   // mediated by telecommunication equipment
   @XmlElement(name = "telecom", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected List<TelecomFactType> telecom;

   // The mailing address
   @XmlElement(name = "address", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected List<AddressFactType> address;

   // The name of the organization with which the provider is affiliated
   @XmlElement(name = "organization")
   protected String organization;

   // The name of the service delivery location9s) with which the provider is affiliated
   @XmlElement(name = "serviceDeliveryLocation")
   protected List<String> serviceDeliveryLocation;

   // National Provider Identifier or NPI is a unique identification number issued to
   // healthcare providers in the United States
   @XmlElement(name = "nationalProviderId")
   protected ValueType nationalProviderId;

   //---------------------------------------------------------------------------
   // JAXB generated codes
   //---------------------------------------------------------------------------
   /**
    * Sets the value of the freeTextProviderType property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setFreeTextProviderType(String value) {
      this.freeTextProviderType = value;
   }

   /**
    * Gets the value of the freeTextProviderType property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getFreeTextProviderType() {
      return freeTextProviderType;
   }

   /**
    * Sets the value of the freeTextRole property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setFreeTextRole(String value) {
      this.freeTextRole = value;
   }

   /**
    * Gets the value of the freeTextRole property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getFreeTextRole() {
      return freeTextRole;
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
    * Gets the value of the codedRole property.
    *
    * @return
    *     possible object is
    *     {@link CodeLabelPair }
    *
    */
   public CodeLabelPair getCodedRole() {
      return codedRole;
   }

   /**
    * Sets the value of the codedRole property.
    *
    * @param value
    *     allowed object is
    *     {@link CodeLabelPair }
    *
    */
   public void setCodedRole(CodeLabelPair value) {
      this.codedRole = value;
   }

   /**
    * Gets the value of the organization property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getOrganization() {
      return organization;
   }

   /**
    * Sets the value of the organization property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setOrganization(String value) {
      this.organization = value;
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
    * Gets the value of the codedProviderType property.
    *
    * @return
    *     possible object is
    *     {@link CodeLabelPair }
    *
    */
   public CodeLabelPair getCodedProviderType() {
      return codedProviderType;
   }

   /**
    * Sets the value of the codedProviderType property.
    *
    * @param value
    *     allowed object is
    *     {@link CodeLabelPair }
    *
    */
   public void setCodedProviderType(CodeLabelPair value) {
      this.codedProviderType = value;
   }

   /**
    * Gets the value of the npi (National Provider Identifier) property.
    *
    * @return
    *     possible object is
    *     {@link ValueType }
    *
    */
   public ValueType getNationalProviderId() {
      return nationalProviderId;
   }

   /**
    * Sets the value of the npi (National Provider Identifier) property.
    *
    * @param value
    *     allowed object is
    *     {@link ValueType }
    *
    */
   public void setNationalProviderId(ValueType value) {
      this.nationalProviderId = value;
   }

   /**
    * Gets the value of the serviceDeliveryLocation property.
    *
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the serviceDeliveryLocation property.
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
    * {@link String }
    *
    *
    */
   public List<String> getServiceDeliveryLocation() {
      if (serviceDeliveryLocation == null) {
         serviceDeliveryLocation = new ArrayList<String>();
      }
      return this.serviceDeliveryLocation;
   }

   //---------------------------------------------------------------------------
   // Add any custom codes here
   //---------------------------------------------------------------------------
   public void setTelecom(ArrayList<TelecomFactType> value) {
      this.telecom = value;
   }

   public void setAddress(ArrayList<AddressFactType> value) {
      this.address = value;
   }

   public void setOtherName(ArrayList<NameFactType> value) {
      this.otherName = value;
   }

   public void setServiceDeliveryLocation(ArrayList<String> value) {
      this.serviceDeliveryLocation = value;
   }

   @Override
   public String toString() {
      StringBuffer str = new StringBuffer();

      str.append("HealthcareProviderFactType[" + super.toString() + System.getProperty("line.separator"));
      str.append("  codedProviderType=" + (codedProviderType != null ? codedProviderType : "") + System.getProperty("line.separator"));
      str.append("  freeTextProviderType=" + freeTextProviderType + System.getProperty("line.separator"));
      str.append("  codedRole=" + (codedRole != null ? codedRole : "") + System.getProperty("line.separator"));
      str.append("  freeTextRole=" + freeTextRole + System.getProperty("line.separator"));
      str.append("  organization=" + organization + System.getProperty("line.separator"));
      str.append("  #serviceDeliveryLocation=" + getServiceDeliveryLocation().size() + System.getProperty("line.separator"));
      for (int i = 0; i < getServiceDeliveryLocation().size(); i++) {
         str.append("    |--> #" + i + "=" + serviceDeliveryLocation.get(i) + System.getProperty("line.separator"));
      }
      str.append("  nationalProviderId=" + (nationalProviderId != null ? nationalProviderId : "") + System.getProperty("line.separator"));
      str.append("  legalName=" + legalName + System.getProperty("line.separator"));
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
