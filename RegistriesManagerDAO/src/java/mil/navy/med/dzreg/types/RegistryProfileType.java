package mil.navy.med.dzreg.types;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for RegistryProfileType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RegistryProfileType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:mil:navy:med:dzreg:types}RegistryType">
 *       &lt;sequence>
 *         &lt;element name="registeredDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistryProfileType", propOrder = {
  "registeredDate",
  "active"
})
public class RegistryProfileType
        extends RegistryType {

  @XmlElement(name = "active", required = true)
  protected boolean active;
  @XmlElement(name = "registeredDate", required = true, type = Date.class)
  @XmlSchemaType(name = "dateTime")
  protected Date registeredDate;

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

  //----------------------------------------------------------------------------
  // Add any custom codes here
  //----------------------------------------------------------------------------
  public RegistryProfileType(int registryId, String registryDesc, boolean active, Date registeredDate) {
    super(registryId, registryDesc);
    this.active = active;
    this.registeredDate = registeredDate;
  }

  public RegistryProfileType(int registryId, String registryDesc) {
    super(registryId, registryDesc);
  }

  public RegistryProfileType(RegistryType registry) {
    super(registry);
  }

  public RegistryProfileType() {
    super();
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("RegistryProfileType[registryId=" + registryId);
    str.append(",registryDesc=" + registryDesc + ",registeredDate=" + registeredDate);
    str.append(",active=" + active + "]");

    return str.toString();
  }
}
