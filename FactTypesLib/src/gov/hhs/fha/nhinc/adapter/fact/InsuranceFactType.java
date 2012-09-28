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
 * <p>Java class for InsuranceFactType complex type representing the insurance provider module contains data
 * about the entities or other individuals who may pay for a patient's healthcare. Such entities or individuals
 * may be health insurance plans, other payers, and guarantors, parties with financial responsibility, some
 * combination of payers or the patient directly. This module is used to define which entity or combination of
 * entities has any financial responsibility for a patient's care.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="InsuranceFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="groupNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codedHealthInsuranceType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="healthPlanSourceId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}Valueype"/>
 *         &lt;element name="healthPlanSourceAddr" type="{urn:gov:hhs:fha:nhinc:adapter:fact}AddressFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="healthPlanSourceTelecom" type="{urn:gov:hhs:fha:nhinc:adapter:fact}TelecomFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="healthPlanSourceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="healthPlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="memberId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="codedMemberRelationship" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="coverageDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="codedMemberFinancialRespType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="subscriberId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subscriberAddress" type="{urn:gov:hhs:fha:nhinc:adapter:fact}AddressFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subscriberTelecom" type="{urn:gov:hhs:fha:nhinc:adapter:fact}TelecomFactType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subscriberName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subscriberDateOfBirth" type="{http://www.w3.org/2001/XMLSchema}dateTime"  minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InsuranceFactType", propOrder = {
   "patientId",
   "groupNumber",
   "codedHealthInsuranceType",
   "healthPlanSourceId",
   "healthPlanSourceAddr",
   "healthPlanSourceTelecom",
   "healthPlanSourceName",
   "healthPlanName",
   "memberId",
   "codedMemberRelationship",
   "coverageDate",
   "codedMemberFinancialRespType",
   "subscriberId",
   "subscriberAddress",
   "subscriberTelecom",
   "subscriberName",
   "subscriberDateOfBirth"
})
public class InsuranceFactType
        extends FactType
        implements Serializable {

   // identify the patient
   @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected ValueType patientId;
   // [5.01] The policy or group contract number identifying the contract between a health plan sponsor and the health plan.
   @XmlElement(name = "groupNumber", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected String groupNumber;
   // [5.02] The type of health plan covering the individual, e.g., an HMO, PPO, POS, Medicare Part A/B, etc
   @XmlElement(name = "codedHealthInsuranceType", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected CodeLabelPair codedHealthInsuranceType;
   // [5.03] The coded identifier of the payer corresponding to the Health Plan Information Source Name.
   // Note: Health Plan Information Source Name and ID are not synonymous with Health Plan Name or the health 
   // plan identifier (when/if health plans are enumerated under HIPAA)
   @XmlElement(name = "healthPlanSourceId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected ValueType healthPlanSourceId;
   // [5.04] The official mailing address to which written correspondence is to be directed.
   @XmlElement(name = "healthPlanSourceAddr", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected List<AddressFactType> healthPlanSourceAddr;
   // [5.05] A telephone number (voice or fax), e-mail address or other locator for a resource mediated by 
   // telecommunication equipment
   @XmlElement(name = "healthPlanSourceTelecom", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected List<TelecomFactType> healthPlanSourceTelecom;
   // [5.06] The name of the entity that is the source of information about the health insurance.
   @XmlElement(name = "healthPlanSourceName")
   protected String healthPlanSourceName;
   // [5.24] The name of the specific health insurance product as specified by the insurance company
   // offering the healthcare insurance.
   @XmlElement(name = "healthPlanName")
   protected String healthPlanName;
   // [5.08] The identifier assigned by the health plan to the patient who is covered by the health plan. When
   // the patient is the actual member or health plan contract holder (the true subscriber) and not a
   // dependent of the subscriber, it is the same as the Subscriber ID. A related spouse, child, or
   // dependent may not have a unique identification number of their own.
   @XmlElement(name = "memberId", required = true)
   protected String memberId;
   // [5.09] Specifies patient with subscriber within the context of the specified health plan
   @XmlElement(name = "codedMemberRelationship", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected CodeLabelPair codedMemberRelationship;
   // [5.07] The beginning and end dates of the health plan coverage of the individual.
   @XmlElement(name = "coverageDate", type = Date.class, required = true)
   @XmlSchemaType(name = "dateTime")
   protected Date coverageDate;
   // [5.14] The type of party that has responsibility for all or a portion of the patient's
   // healthcare; includes health insurance, the patient directly, a guardian or other guarantor
   // or other third party that is not a health insurance plan.
   @XmlElement(name = "codedMemberFinancialRespType", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected CodeLabelPair codedMemberFinancialRespType;
   // [5.15] The identifier assigned by the health plan to the actual member or health plan contract holder (the
   // true subscriber) entered into the eligibility system of the health plan.
   @XmlElement(name = "subscriberId", required = true)
   protected String subscriberId;
   // [5.16] The official mailing address of the actual member or health plan contract holder (the true
   // subscriber) as entered into the eligibility system of the health plan to which written correspondence
   // is to be directed.
   @XmlElement(name = "subscriberAddress", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected List<AddressFactType> subscriberAddress;
   // [5.17] A telephone number (voice or fax), e-mail address of the actual member or health plan
   // contract holder (the true subscriber)
   @XmlElement(name = "subscriberTelecom", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected List<TelecomFactType> subscriberTelecom;
   // [5.18] The name of the actual member or health plan contract holder (the true subscriber) as entered
   // into the eligibility system of the health plan.
   @XmlElement(name = "subscriberName", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected NameFactType subscriberName;
   // [5.19] The date of birth of the actual member or health plan contract holder (the true subscriber) 
   // as entered into the eligibility system of the health plan.
   @XmlElement(name = "subscriberDateOfBirth", type = Date.class)
   @XmlSchemaType(name = "dateTime")
   protected Date subscriberDateOfBirth;

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
    * Sets the value of the groupNumber property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setGroupNumber(String value) {
      this.groupNumber = value;
   }

   /**
    * Gets the value of the groupNumber property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getGroupNumber() {
      return groupNumber;
   }

   /**
    * Sets the value of the codedHealthInsuranceType property.
    *
    * @param value
    *     allowed object is
    *     {@link CodeLabelPair }
    *
    */
   public void setCodedHealthInsuranceType(CodeLabelPair value) {
      this.codedHealthInsuranceType = value;
   }

   /**
    * Gets the value of the codedHealthInsuranceType property.
    *
    * @return
    *     possible object is
    *     {@link CodeLabelPair }
    *
    */
   public CodeLabelPair getCodedHealthInsuranceType() {
      return codedHealthInsuranceType;
   }

   /**
    * Gets the value of the healthPlanSourceId property.
    *
    * @return
    *     possible object is
    *     {@link ValueType }
    *
    */
   public ValueType getHealthPlanSourceId() {
      return healthPlanSourceId;
   }

   /**
    * Sets the value of the healthPlanSourceId property.
    *
    * @param value
    *     allowed object is
    *     {@link ValueType }
    *
    */
   public void setHealthPlanSourceId(ValueType value) {
      this.healthPlanSourceId = value;
   }

   /**
    * Gets the value of the healthPlanSourceName property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getHealthPlanSourceName() {
      return healthPlanSourceName;
   }

   /**
    * Sets the value of the healthPlanSourceName property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setHealthPlanSourceName(String value) {
      this.healthPlanSourceName = value;
   }

   /**
    * Gets the value of the subscriberName property.
    *
    * @return
    *     possible object is
    *     {@link NameFactType }
    *
    */
   public NameFactType getSubscriberName() {
      return subscriberName;
   }

   /**
    * Sets the value of the subscriberName property.
    *
    * @param value
    *     allowed object is
    *     {@link NameFactType }
    *
    */
   public void setSubscriberName(NameFactType value) {
      this.subscriberName = value;
   }

   /**
    * Gets the value of the memberId property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getMemberId() {
      return memberId;
   }

   /**
    * Sets the value of the memberId property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setMemberId(String value) {
      this.memberId = value;
   }

   /**
    * Gets the value of the codedMemberRelationship property.
    *
    * @return
    *     possible object is
    *     {@link CodeLabelPair }
    *
    */
   public CodeLabelPair getCodedMemberRelationship() {
      return codedMemberRelationship;
   }

   /**
    * Gets the value of the codedHealthInsuranceType property.
    *
    * @return
    *     possible object is
    *     {@link CodeLabelPair }
    *
    */
   public void setCodedMemberRelationship(CodeLabelPair value) {
      codedHealthInsuranceType = value;
   }

   /**
    * Sets the value of the codedMemberFinancialRespType property.
    *
    * @param value
    *     allowed object is
    *     {@link CodeLabelPair }
    *
    */
   public void setCodedMemberFinancialRespType(CodeLabelPair value) {
      this.codedMemberFinancialRespType = value;
   }

   /**
    * Sets the value of the codedMemberFinancialRespType property.
    *
    * @param value
    *     allowed object is
    *     {@link CodeLabelPair }
    *
    */
   public void getCodedMemberFinancialRespType(CodeLabelPair value) {
      this.codedMemberFinancialRespType = value;
   }

   /**
    * Gets the value of the healthPlanSourceTelecom property.
    *
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the healthPlanSourceTelecom property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getHealthPlanSourceTelecom().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link TelecomFactType }
    *
    *
    */
   public List<TelecomFactType> getHealthPlanSourceTelecom() {
      if (healthPlanSourceTelecom == null) {
         healthPlanSourceTelecom = new ArrayList<TelecomFactType>();
      }
      return this.healthPlanSourceTelecom;
   }

   /**
    * Gets the value of the healthPlanSourceAddr property.
    *
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the healthPlanSourceAddr property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getHealthPlanSourceAddr().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link AddressFactType }
    *
    *
    */
   public List<AddressFactType> getHealthPlanSourceAddr() {
      if (healthPlanSourceAddr == null) {
         healthPlanSourceAddr = new ArrayList<AddressFactType>();
      }
      return this.healthPlanSourceAddr;
   }

   /**
    * Gets the value of the healthPlanSourceTsubscriberTelecomelecom property.
    *
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the subscriberTelecom property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getSubscriberTelecom().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link TelecomFactType }
    *
    *
    */
   public List<TelecomFactType> getSubscriberTelecom() {
      if (subscriberTelecom == null) {
         subscriberTelecom = new ArrayList<TelecomFactType>();
      }
      return this.subscriberTelecom;
   }

   /**
    * Gets the value of the subscriberAddress property.
    *
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the subscriberAddress property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getHealthPlanSourceAddr().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link AddressFactType }
    *
    *
    */
   public List<AddressFactType> getSubscriberAddress() {
      if (subscriberAddress == null) {
         subscriberAddress = new ArrayList<AddressFactType>();
      }
      return this.subscriberAddress;
   }

   /**
    * Gets the value of the coverageDate property.
    *
    * @return
    *     possible object is
    *     {@link Date }
    *
    */
   public Date getCoverageDate() {
      return coverageDate;
   }

   /**
    * Sets the value of the coverageDate property.
    *
    * @param value
    *     allowed object is
    *     {@link Date }
    *
    */
   public void setCoverageDate(Date value) {
      this.coverageDate = value;
   }

   /**
    * Gets the value of the subscriberDateOfBirth property.
    *
    * @return
    *     possible object is
    *     {@link Date }
    *
    */
   public Date getSubscriberDateOfBirth() {
      return subscriberDateOfBirth;
   }

   /**
    * Sets the value of the subscriberDateOfBirth property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setSubscriberDateOfBirth(Date value) {
      this.subscriberDateOfBirth = value;
   }

   /**
    * Gets the value of the subscriberId property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getSubscriberId() {
      return subscriberId;
   }

   /**
    * Sets the value of the subscriberId property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setSubscriberId(String value) {
      this.subscriberId = value;
   }

   //---------------------------------------------------------------------------
   // Add any custom codes here
   //---------------------------------------------------------------------------
   public void setHealthPlanSourceTelecom(ArrayList<TelecomFactType> telecom) {
      this.healthPlanSourceTelecom = telecom;
   }

   public void setHealthPlanSourceAddr(ArrayList<AddressFactType> address) {
      this.healthPlanSourceAddr = address;
   }

   public void setSubscriberTelecom(ArrayList<TelecomFactType> telecom) {
      this.subscriberTelecom = telecom;
   }

   public void setSubscriberAddress(ArrayList<AddressFactType> address) {
      this.subscriberAddress = address;
   }

   @Override
   public String toString() {
      StringBuffer str = new StringBuffer();

      str.append("SupportContactFactType[" + super.toString() + System.getProperty("line.separator"));
      str.append("  patientId=" + (patientId != null ? patientId : "") + System.getProperty("line.separator"));
      str.append("  groupNumber=" + groupNumber + System.getProperty("line.separator"));
      str.append("  codedHealthInsuranceType=" + (codedHealthInsuranceType != null ? codedHealthInsuranceType : "") + System.getProperty("line.separator"));
      str.append("  memberId=" + memberId + System.getProperty("line.separator"));
      str.append("  codedMemberRelationship=" + (codedMemberRelationship != null ? codedMemberRelationship : "") + System.getProperty("line.separator"));
      str.append("  codedMemberFinancialRespType=" + (codedMemberFinancialRespType != null ? codedMemberFinancialRespType : "") + System.getProperty("line.separator"));
      str.append("  coverageDate=" + coverageDate + System.getProperty("line.separator"));
      str.append("  subscriberId=" + subscriberId + System.getProperty("line.separator"));
      str.append("  subscriberName=" + (subscriberName != null ? subscriberName : "") + System.getProperty("line.separator"));
      str.append("  subscriberDateOfBirth=" + subscriberDateOfBirth + System.getProperty("line.separator"));
      str.append("  healthPlanSourceId=" + (healthPlanSourceId != null ? healthPlanSourceId : "") + System.getProperty("line.separator"));
      str.append("  healthPlanSourceName=" + healthPlanSourceName + System.getProperty("line.separator"));
      str.append("  healthPlanName=" + healthPlanName + System.getProperty("line.separator"));
      str.append("  #healthPlanSourceTelecom=" + getHealthPlanSourceTelecom().size() + System.getProperty("line.separator"));
      for (int i = 0; i < getHealthPlanSourceTelecom().size(); i++) {
         str.append("    |--> #" + i + "=" + healthPlanSourceTelecom.get(i) + System.getProperty("line.separator"));
      }
      str.append("  #healthPlanSourceAddresses=" + getHealthPlanSourceAddr().size() + System.getProperty("line.separator"));
      for (int i = 0; i < getHealthPlanSourceAddr().size(); i++) {
         str.append("    |--> #" + i + "=" + healthPlanSourceAddr.get(i) + System.getProperty("line.separator"));
      }
      str.append("]");

      return str.toString();
   }
}
