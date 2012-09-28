package gov.hhs.fha.nhinc.adapter.fact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for FactType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="primaryKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="factHash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idHash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="historical" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="id" type="{urn:gov:hhs:fha:nhinc:adapter:fact}ValueType" maxOccurs="unbounded"/>
 *         &lt;element name="sourceSystem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FactType", propOrder = {
  "primaryKey",
  "factHash",
  "idHash",
  "historical",
  "id",
  "sourceSystem"
})
@XmlSeeAlso({
  ResultFactType.class,
  PersonFactType.class,
  ProblemFactType.class,
  MedicationFactType.class,
  AllergyFactType.class
})
public abstract class FactType implements Serializable {

  @XmlElement(name = "primaryKey")
  protected String primaryKey;
  @XmlElement(name = "id", required = true, namespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  protected List<ValueType> id;  
  @XmlElement(name = "historical", type = Boolean.class, required = true)
  protected Boolean historical;
  @XmlElement(name = "sourceSystem", required = true)
  protected String sourceSystem;
  @XmlElement(name = "factHash")
  protected String factHash;
  @XmlElement(name = "idHash")
  protected String idHash;

  /**
   * Gets the value of the historical property.
   *
   */
  public boolean isHistorical() {
    return historical;
  }

  /**
   * Sets the value of the historical property.
   *
   */
  public void setHistorical(boolean value) {
    this.historical = value;
  }

  /**
   * Gets the value of the id property.
   *
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the id property.
   *
   * <p>
   * For example, to add a new item, do as follows:
   * <pre>
   *    getId().add(newItem);
   * </pre>
   *
   *
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link String }
   *
   *
   */
  public List<ValueType> getId() {
    if (id == null) {
      id = new ArrayList<ValueType>();
    }
    return this.id;
  }

    /**
     * Gets the value of the primaryKey property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Sets the value of the primaryKey property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPrimaryKey(String value) {
        this.primaryKey = value;
    }

    /**
     * Gets the value of the sourceSystem property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Sets the value of the sourceSystem property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSourceSystem(String value) {
        this.sourceSystem = value;
    }

    /**
     * Gets the value of the factHash property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFactHash() {
        return factHash;
    }

    /**
     * Sets the value of the factHash property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFactHash(String value) {
        this.factHash = value;
    }

  /**
     * Gets the value of the idHash property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdHash() {
        return idHash;
    }

    /**
     * Sets the value of the idHash property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdHash(String value) {
        this.idHash = value;
    }
    
  //---------------------------------------------------------------------------
  // Add any custom codes here
  //---------------------------------------------------------------------------
  /**
   * 
   * @param id
   */
  public void setId(ArrayList<ValueType> id) {
    this.id = id;
  }

  protected int calculateAgeInYears(Date birthDate) {
    int calculatedAge = 0;

    try {
      // convert the DOB to GregorianCalendar class.
      Calendar dob = new GregorianCalendar();
      dob.setTime(birthDate);

      // Create a calendar object with today's date
      Calendar today = Calendar.getInstance();

      // Get age based on year
      calculatedAge = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

      // Add the tentative age to the date of birth to get this year's birthday
      dob.add(Calendar.YEAR, calculatedAge);

      // If this year's birthday has not happened yet, subtract one from age
      if (today.before(dob)) {
        calculatedAge--;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return calculatedAge;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("FactType(historical=" + historical + ",sourceSystem=" + sourceSystem +
                ",#ids=" + getId().size() + System.getProperty("line.separator") + "    |--> ");
    for (int i = 0; i < getId().size(); i++) {
      str.append(id.get(i) + "|");
    }
    str.append(")");

    return str.toString();
  }
}
