package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for CareRecordPayloadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CareRecordPayloadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="careProvisionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="careRecordStartTimePeriod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="careRecordEndTimePeriod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CareRecordPayloadType", propOrder = {
   "patientId",
   "careProvisionCode",
   "careRecordStartTimePeriod",
   "careRecordEndTimePeriod"
})
public class CareRecordPayloadType implements Serializable {

   @XmlElement(name = "patientId", required = true)
   protected String patientId;
   @XmlElement(name = "careProvisionCode", required = true)
   protected String careProvisionCode;
   @XmlElement(name = "careRecordStartTimePeriod", required = true, nillable = true)
   protected String careRecordStartTimePeriod;
   @XmlElement(name = "careRecordEndTimePeriod", required = true, nillable = true)
   protected String careRecordEndTimePeriod;

   /**
    * Gets the value of the patientId property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getPatientId() {
      return patientId;
   }

   /**
    * Sets the value of the patientId property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setPatientId(String value) {
      this.patientId = value;
   }

   /**
    * Gets the value of the careProvisionCode property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getCareProvisionCode() {
      return careProvisionCode;
   }

   /**
    * Sets the value of the careProvisionCode property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setCareProvisionCode(String value) {
      this.careProvisionCode = value;
   }

   /**
    * Gets the value of the careRecordStartTimePeriod property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getCareRecordStartTimePeriod() {
      return careRecordStartTimePeriod;
   }

   /**
    * Sets the value of the careRecordStartTimePeriod property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setCareRecordStartTimePeriod(String value) {
      this.careRecordStartTimePeriod = value;
   }

   /**
    * Gets the value of the careRecordEndTimePeriod property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getCareRecordEndTimePeriod() {
      return careRecordEndTimePeriod;
   }

   /**
    * Sets the value of the careRecordEndTimePeriod property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setCareRecordEndTimePeriod(String value) {
      this.careRecordEndTimePeriod = value;
   }
}
