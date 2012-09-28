package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for ResultEventPayloadType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResultEventPayloadType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="actId" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="observationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observationStartTimePeriod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observationEndTimePeriod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="observationType" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *         &lt;element name="patientName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" minOccurs="0"/>
 *         &lt;element name="patientDOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientGender" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultEventPayloadType", propOrder = {
   "patientId",
   "actId",
   "observationStatus",
   "observationStartTimePeriod",
   "observationEndTimePeriod",
   "observationType",
   "patientName",
   "patientDOB",
   "patientGender"
})
public class ResultEventPayloadType implements Serializable {

   // an identifier for the patient, required criteria
   @XmlElement(name = "patientId", required = true)
   protected String patientId;
   // name of the patient, uses for validation purpose
   @XmlElement(name = "patientName", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected NameFactType patientName;
   // date of birth of the patient, uses for validation purpose
   @XmlElement(name = "patientDOB")
   protected String patientDOB;
   // gender the patient to be use for validation purpose
   @XmlElement(name = "patientGender", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
   protected CodeLabelPair patientGender;
   // a list of events
   // primary  criteria when quering for specific event(s)
   @XmlElement(name = "actId")
   protected List<String> actId;
   // status of the event to be use as filter only
   @XmlElement(name = "observationStatus")
   protected String observationStatus;
   // the starting date/time of an interval to query for results within which the observations had occurred
   // secondary criteria uses when querying for results of a test over an interval of time
   @XmlElement(name = "observationStartTimePeriod")
   protected String observationStartTimePeriod;
   // the ending date/time of an interval to query for results within which the observations had occurred
   // secondary criteria uses when querying for results of a test over an interval of time
   @XmlElement(name = "observationEndTimePeriod")
   protected String observationEndTimePeriod;
   // A code from standard vocabularies such as LOINC Codes  to identify the test to query for
   // primary criteria uses when querying for results of a test over an interval of time
   @XmlElement(name = "observationType")
   protected CodeLabelPair observationType;


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
    * Gets the value of the actId property.
    *
    * <p>
    * This accessor method returns a reference to the live list,
    * not a snapshot. Therefore any modification you make to the
    * returned list will be present inside the JAXB object.
    * This is why there is not a <CODE>set</CODE> method for the actId property.
    *
    * <p>
    * For example, to add a new item, do as follows:
    * <pre>
    *    getActId().add(newItem);
    * </pre>
    *
    *
    * <p>
    * Objects of the following type(s) are allowed in the list
    * {@link String }
    *
    *
    */
   public List<String> getActId() {
      if (actId == null) {
         actId = new ArrayList<String>();
      }
      return this.actId;
   }

   /**
    * Gets the value of the observationStatus property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getObservationStatus() {
      return observationStatus;
   }

   /**
    * Sets the value of the observationStatus property.
    *
    * @param value
    *     allowed object is
    *    {@link String }
    *
    */
   public void setObservationStatus(String value) {
      this.observationStatus = value;
   }

   /**
    * Gets the value of the observationStartTimePeriod property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getObservationStartTimePeriod() {
      return observationStartTimePeriod;
   }

   /**
    * Sets the value of the observationStartTimePeriod property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setObservationStartTimePeriod(String value) {
      this.observationStartTimePeriod = value;
   }

   /**
    * Gets the value of the observationEndTimePeriod property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getObservationEndTimePeriod() {
      return observationEndTimePeriod;
   }

   /**
    * Sets the value of the observationEndTimePeriod property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setObservationEndTimePeriod(String value) {
      this.observationEndTimePeriod = value;
   }

   /**
    * Gets the value of the observationType property.
    *
    * @return
    *     possible object is
    *     {@link CodeLabelPair }
    *
    */
   public CodeLabelPair getObservationType() {
      return observationType;
   }

   /**
    * Sets the value of the observationType property.
    *
    * @param value
    *     allowed object is
    *    {@link CodeLabelPair }
    *
    */
   public void setObservationType(CodeLabelPair value) {
      this.observationType = value;
   }

   /**
    * Gets the value of the patientName property.
    *
    * @return
    *     possible object is
    *     {@link NameFactType }
    *
    */
   public NameFactType getPatientName() {
      return patientName;
   }

   /**
    * Sets the value of the patientName property.
    *
    * @param value
    *     allowed object is
    *     {@link NameFactType }
    *
    */
   public void setPatientName(NameFactType value) {
      this.patientName = value;
   }

   /**
    * Gets the value of the patientDOB property.
    *
    * @return
    *     possible object is
    *     {@link String }
    *
    */
   public String getPatientDOB() {
      return patientDOB;
   }

   /**
    * Sets the value of the patientDOB property.
    *
    * @param value
    *     allowed object is
    *     {@link String }
    *
    */
   public void setPatientDOB(String value) {
      this.patientDOB = value;
   }

   /**
    * Gets the value of the patientGender property.
    *
    * @return
    *     possible object is
    *     {@link CodeLabelPair }
    *
    */
   public CodeLabelPair getPatientGender() {
      return patientGender;
   }

   /**
    * Sets the value of the patientGender property.
    *
    * @param value
    *     allowed object is
    *     {@link CodeLabelPair }
    *
    */
   public void setPatientGender(CodeLabelPair value) {
      this.patientGender = value;
   }

   //---------------------------------------------------------------------------
   // Add any custom codes here
   //---------------------------------------------------------------------------
   /**
    *
    * @param actId
    */
   public void setActId(ArrayList<String> actId) {
      this.actId = actId;
   }

   @Override
   public String toString() {
      StringBuffer str = new StringBuffer();

      str.append("ResultEventPayloadType(#actIds=" + getActId().size() + System.getProperty("line.separator") + "    |--> ");
      for (int i = 0; i < getActId().size(); i++) {
         str.append(actId.get(i) + "|");
      }
      str.append(",observationStatus=" + observationStatus);
      str.append(",observationStartTimePeriod=" + observationStartTimePeriod);
      str.append(",observationEndTimePeriod=" + observationEndTimePeriod);
      str.append(",observationType=" + (observationType != null? observationType: ""));
      str.append(",patientName=" + (patientName != null? patientName: ""));
      str.append(")");

      return str.toString();
   }
}
