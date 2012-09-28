package mil.navy.med.dzreg.types;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for PersonRegistryProfileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonRegistryProfileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="person" type="{urn:mil:navy:med:dzreg:types}PersonType"/>
 *         &lt;element name="registry" type="{urn:mil:navy:med:dzreg:types}RegistryProfileType" maxOccurs="unbounded"/>
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
@XmlType(name = "PersonRegistryProfileType", propOrder = {
  "person",
  "registry",
  "dataSource"
})
public class PersonRegistryProfileType {

  @XmlElement(name = "dataSource", required = true)
  protected String dataSource;
  @XmlElement(name = "registry", required = true)
  protected List<RegistryProfileType> registry;
  @XmlElement(name = "person", required = true)
  protected PersonType person;

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
   * Gets the value of the registry property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the dzType property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getRegistry().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link RegistryProfileType }
   *
   *
   */
  public List<RegistryProfileType> getRegistry() {
    if (registry == null) {
      registry = new ArrayList<RegistryProfileType>();
    }
    return this.registry;
  }

  /**
   * Gets the value of the person property.
   *
   * @return
   *     possible object is
   *     {@link PatientType }
   *
   */
  public PersonType getPerson() {
    return person;
  }

  /**
   * Sets the value of the person property.
   *
   * @param value
   *     allowed object is
   *     {@link PatientType }
   *
   */
  public void setPerson(PersonType value) {
    this.person = value;
  }

  //----------------------------------------------------------------------------
  // Add any custom codes here
  //----------------------------------------------------------------------------
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("PersonRegistryProfileType[person=" + person + System.getProperty("line.separator"));
    str.append(",registry=" + registry + System.getProperty("line.separator"));
    str.append(",dataSource=" + dataSource + "]");

    return str.toString();
  }
}
