package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RegistryInfoFactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegistryInfoFactType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:gov:hhs:fha:nhinc:adapter:fact}FactType">
 *       &lt;sequence>
 *         &lt;element name="patientId" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType"/>
 *         &lt;element name="patientName" type="{urn:gov:hhs:fha:nhinc:adapter:fact}NameFactType"/>
 *         &lt;element name="registry" type="{urn:gov:hhs:fha:nhinc:adapter:fact}CodeLabelPair"/>
 *         &lt;element name="registeredDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dataSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistryInfoFactType", propOrder = {
  "patientId",
  "patientName",
  "registry",
  "registeredDate",
  "active",
  "dataSource"
})
public class RegistryProfileFactType
        extends FactType
        implements Serializable {

  /**
   * Registry type identifier shall be a combination of "patientid + registryid"
   */

  /**
   * Unique identifier of this patient.
   */
  @XmlElement(name = "patientId", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected ValueType patientId;
  /**
   * Unique identifier of this patient.
   */
  @XmlElement(name = "patientName", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected NameFactType patientName;
  /**
   * Registered date and time.
   */
  @XmlElement(name = "registeredDate", type = Date.class, required = true)
  @XmlSchemaType(name = "dateTime")
  protected Date registeredDate;
  /**
   * Indicator whether patient is active in this registry or not.
   */
  @XmlElement(name = "active", required = true)
  protected boolean active;
  /**
   * Registry description.
   */
  @XmlElement(name = "registry", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected CodeLabelPair registry;
  /**
   * Data source.
   */
  @XmlElement(name = "dataSource")
  protected String dataSource;

  /**
   * Gets the value of the active property.
   *
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Sets the value of the active property.
   *
   */
  public void setActive(boolean value) {
    this.active = value;
  }

  /**
   * Gets the value of the registeredDate property.
   *
   * @return
   *     possible object is
   *     {@link Date }
   *
   */
  public Date getRegisteredDate() {
    return registeredDate;
  }

  /**
   * Sets the value of the registeredDate property.
   *
   * @param value
   *     allowed object is
   *     {@link Date }
   *
   */
  public void setRegisteredDate(Date value) {
    this.registeredDate = value;
  }

  /**
   * Sets the value of the registry property.
   *
   * @param value
   *     allowed object is
   *     {@link CodeLabelPair }
   *
   */
  public void setRegistry(CodeLabelPair value) {
    this.registry = value;
  }

  /**
   * Gets the value of the state property.
   *
   * @return
   *     possible object is
   *     {@link CodeLabelPair }
   *
   */
  public CodeLabelPair getRegistry() {
    return registry;
  }

  /**
   * Gets the value of the dataSource property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getDataSource() {
    return dataSource;
  }

  /**
   * Sets the value of the dataSource property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setDataSource(String value) {
    this.dataSource = value;
  }

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
  public void setPatientId(ValueType value) {
    this.patientId = value;
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

  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("RegistryInfoFactType[patientId=" + patientId + System.getProperty("line.separator"));
    str.append("  ,patientName=" + patientName + System.getProperty("line.separator"));
    str.append("  ,registeredDate=" + registeredDate + ",active=" + active + ",dataSource=" + dataSource + System.getProperty("line.separator"));
    str.append("  ,registry=" + registry + "]");

    return str.toString();
  }
}
