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
 * <p>Java class for AdmissionFactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdmissionFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="patient" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="reasonForVisit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="startDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="endDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="admitter" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="attender" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType" minOccurs="0" minOccurs="unbounded"/>
 *         &lt;element name="codedServiceDeliveryLocation" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="serviceDeliveryLocationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdmissionFactType", propOrder = {
  "patientId",
  "patient",
  "reasonForVisit",
  "statusCode",
  "startDateTime",
  "endDateTime",
  "admitter",
  "attender",
  "codedServiceDeliveryLocation",
  "serviceDeliveryLocationName"
})
public class AdmissionFactType
        extends FactType
        implements Serializable {

  /** uses FactType->id to carry appointment id */

  /**
   * Unique identifier of patient
   */
  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;

  /**
   * Patient name
   */
  @XmlElement(name = "patient", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType patient;
  /** 
   * reason for this admission
   */
  @XmlElement(name = "reasonForVisit")
  protected String reasonForVisit;

  /**
   * Status of the admission
   */
  @XmlElement(name = "statusCode", required = true)
  protected String statusCode;
  
  /** 
   * Start date and time of admission
   */
  @XmlElement(name = "startDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date startDateTime;
  
  /** 
   * End date and time (discharge) of admission
   */
  @XmlElement(name = "endDateTime", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date endDateTime;
  
  /**
   * The healthcare practitioner who required/authorized this encounter.
   */
  @XmlElement(name = "admitter", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType admitter;
  /**
   * the provider who will be providing service
   */
  @XmlElement(name = "attender", namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<NameFactType> attender;

  /**
   * A code value describes this service delivery location.
   */
  @XmlElement(name = "codedServiceDeliveryLocation", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair codedServiceDeliveryLocation;
  
  /**
   * The name of the service delivery location.
   */
  @XmlElement(name = "serviceDeliveryLocationName")
  protected String serviceDeliveryLocationName;

  /**
   * Gets the value of the patientId property.
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
   * Sets the value of the patientId property.
   *
   * @param value
   *     allowed object is
   *     {@link ValueType }
   *
   */
  public void setPatientId(ValueType patientId) {
    this.patientId = patientId;
  }

  /**
   * Gets the value of the patient property.
   *
   * @return
   *     possible object is
   *     {@link NameFactType }
   *
   */
  public NameFactType getPatient() {
    return patient;
  }

  /**
   * Sets the value of the patient property.
   *
   * @param value
   *     allowed object is
   *     {@link NameFactType }
   *
   */
  public void setPatient(NameFactType patient) {
    this.patient = patient;
  }

  /**
   * Gets the value of the statusCode property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getStatusCode() {
    return statusCode;
  }

  /**
   * Sets the value of the statusCode property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  /**
   * Gets the value of the startDateTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getStartDateTime() {
    return startDateTime;
  }

  /**
   * Sets the value of the startDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setStartDateTime(Date startDateTime) {
    this.startDateTime = startDateTime;
  }

  /**
   * Gets the value of the endDateTime property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getEndDateTime() {
    return endDateTime;
  }

  /**
   * Sets the value of the endDateTime property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setEndDateTime(Date endDateTime) {
    this.endDateTime = endDateTime;
  }

  /**
   * Gets the value of the attender property.
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
   *    getAttender().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link NameFactType }
   *
   *
   */
  public List<NameFactType> getAttender() {
    if (attender == null) {
      attender = new ArrayList<NameFactType>();
    }
    return this.attender;
  }

  /**
   * Gets the value of the codedServiceDeliveryLocation property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getCodedServiceDeliveryLocation() {
    return codedServiceDeliveryLocation;
  }

  /**
   * Sets the value of the codedServiceDeliveryLocation property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setCodedServiceDeliveryLocation(CodeLabelPair location) {
    this.codedServiceDeliveryLocation = location;
  }

  /**
   * Gets the value of the codedServiceDeliveryLocation property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getServiceDeliveryLocationName() {
    return serviceDeliveryLocationName;
  }

  /**
   * Sets the value of the serviceDeliveryLocationName property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setServiceDeliveryLocationName(String value) {
    this.serviceDeliveryLocationName = value;
  }

  /**
   * Gets the value of the reasonForVisit property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getReasonForVisit() {
    return reasonForVisit;
  }

  /**
   * Sets the value of the reasonForVisit property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setReasonForVisit(String value) {
    this.reasonForVisit = value;
  }

  /**
   * Gets the value of the admitter property.
   *
   * @return
   *     possible object is
   *     {@link NameFactType }
   *
   */
  public NameFactType getAdmitter() {
    return admitter;
  }

  /**
   * Sets the value of the admitter property.
   *
   * @param value
   *     allowed object is
   *     {@link NameFactType }
   *
   */
  public void setAdmitter(NameFactType value) {
    this.admitter = value;
  }

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  public void setAttender(ArrayList<NameFactType> attender) {
    this.attender = attender;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("AdmissionFactType(");
    str.append(super.toString());
    str.append(System.getProperty("line.separator"));
    str.append("  statusCode=" + statusCode + ",patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  patient=" + (patient != null? patient: "") + System.getProperty("line.separator"));
    str.append("  reasonForVisit=" + reasonForVisit + System.getProperty("line.separator"));
    str.append("  startDateTime=" + startDateTime + ",endDateTime=" + endDateTime + System.getProperty("line.separator"));
    str.append("  admitter=" + (admitter != null? admitter:"") + System.getProperty("line.separator"));
    str.append("  attender=" + (attender != null? attender:"") + System.getProperty("line.separator"));
    str.append("  serviceDeliveryLocationName=" + serviceDeliveryLocationName + System.getProperty("line.separator"));
    str.append("  codedServiceDeliveryLocation=" + (codedServiceDeliveryLocation != null?codedServiceDeliveryLocation:"") + System.getProperty("line.separator"));

    return str.toString();
  }
}
