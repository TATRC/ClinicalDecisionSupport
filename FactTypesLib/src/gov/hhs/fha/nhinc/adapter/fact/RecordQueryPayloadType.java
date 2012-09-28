package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RecordQueryPayloadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecordQueryPayloadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="typeOfEncounter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecordQueryPayloadType", propOrder = {
   "patientId",
   "typeOfEncounter",
   "recordId"
})
public class RecordQueryPayloadType implements Serializable {

   // Identify the patient that is the subject of the search
   @XmlElement(name = "patientId", required = true)
   protected String patientId;
   // Identify the type of encounter, IMP (inpatient) and AMB (outpatient).
   // Default to all encounter types if no value is specified
   @XmlElement(name = "typeOfEncounter", required = true)
   protected String typeOfEncounter;
   // Primary search criteria when querying for encounters by identifiers.
   @XmlElement(name = "recordId")
   protected String recordId;


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
    * Gets the value of the typeOfEncounter property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getTypeOfEncounter() {
      return typeOfEncounter;
   }

   /**
    * Sets the value of the typeOfEncounter property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setTypeOfEncounter(String value) {
      this.typeOfEncounter = value;
   }

   /**
    * Gets the value of the recordId property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getRecordId() {
      return recordId;
   }

   /**
    * Sets the value of the recordId property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setRecordId(String value) {
      this.recordId = value;
   }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("v[patientId=" + patientId);
    str.append("  typeOfEncounter=" + typeOfEncounter);
    str.append("  recordId=" + recordId + "]");

    return str.toString();
  }
}
