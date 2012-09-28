package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for EncounterSearchPayloadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncounterSearchPayloadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="typeOfEncounter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="careEventId" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="encounterStartTimeFrame" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="encounterEndTimeFrame" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="encounterStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0" maxOccurs="unbounded"/>
 *         &lt;element name="patientLocationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncounterSearchPayloadType", propOrder = {
   "patientId",
   "typeOfEncounter",
   "careEventId",
   "encounterStartTimeFrame",
   "encounterEndTimeFrame",
   "encounterStatus",
   "patientLocationId"
})
public class EncounterSearchPayloadType implements Serializable {

   // Identify the patient that is the subject of the search
   @XmlElement(name = "patientId", required = true)
   protected String patientId;
   // Identify the type of encounter, IMP (inpatient) and AMB (outpatient).
   // Default to all encounter types if no value is specified
   @XmlElement(name = "typeOfEncounter", required = true)
   protected String typeOfEncounter;
   // Primary search criteria when querying for encounters by identifiers.
   @XmlElement(name = "careEventId")
   protected List<String> careEventId;
   // Primary search criteria when querying for encounters within a specified time interval.
   // The starting date and time for the search.
   @XmlElement(name = "encounterStartTimeFrame")
   protected String encounterStartTimeFrame;
   // Primary search criteria when querying for encounters within a specified time interval
   // The ending date and time for the search.
   @XmlElement(name = "encounterEndTimeFrame")
   protected String encounterEndTimeFrame;
   // Uses to filter the encounters to those with matching statuses.
   @XmlElement(name = "encounterStatus")
   protected List<String> encounterStatus;
   @XmlElement(name = "patientLocationId")
   protected String patientLocationId;


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
    * Gets the value of the careEventId property.
    *
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the careEventId property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getCareEventId().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link String }
    *
    *
    */
   public List<String> getCareEventId() {
      if (careEventId == null) {
         careEventId = new ArrayList<String>();
      }
      return this.careEventId;
   }

   /**
    * Gets the value of the encounterStatus property.
    *
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the encounterStatus property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getEncounterStatus().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link String }
    *
    *
    */
   public List<String> getEncounterStatus() {
      if (encounterStatus == null) {
         encounterStatus = new ArrayList<String>();
      }
      return this.encounterStatus;
   }

   /**
    * Gets the value of the encounterStartTimeFrame property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getEncounterStartTimeFrame() {
      return encounterStartTimeFrame;
   }

   /**
    * Sets the value of the encounterStartTimeFrame property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setEncounterStartTimeFrame(String value) {
      this.encounterStartTimeFrame = value;
   }

   /**
    * Gets the value of the encounterEndTimeFrame property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getEncounterEndTimeFrame() {
      return encounterEndTimeFrame;
   }

   /**
    * Sets the value of the encounterEndTimeFrame property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setEncounterEndTimeFrame(String value) {
      this.encounterEndTimeFrame = value;
   }

   /**
    * Gets the value of the patientLocationId property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getPatientLocationId() {
      return patientLocationId;
   }

   /**
    * Sets the value of the patientLocationId property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setPatientLocationId(String value) {
      this.patientLocationId = value;
   }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  public void setEncounterStatus(ArrayList<String> values) {
    this.encounterStatus = values;
  }

  public void setCareEventId(ArrayList<String> values) {
    this.careEventId = values;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("EncounterSearchPayloadType[patientId=" + patientId);
    str.append("  typeOfEncounter=" + typeOfEncounter);
    str.append("  #encounters=" + getCareEventId().size() + System.getProperty("line.separator"));
    for (int i = 0; i < getCareEventId().size(); i++) {
      str.append("    |--> #" + i + careEventId.get(i) + System.getProperty("line.separator"));
    }
    str.append("  time interval=" + encounterStartTimeFrame + "|" + encounterEndTimeFrame + System.getProperty("line.separator"));
    str.append("  encounterStartTimeFrame=" + encounterStartTimeFrame + System.getProperty("line.separator"));
    str.append("  encounterStatus=" + encounterStatus + ",patientLocationId=" + patientLocationId + "]");

    return str.toString();
  }
}
