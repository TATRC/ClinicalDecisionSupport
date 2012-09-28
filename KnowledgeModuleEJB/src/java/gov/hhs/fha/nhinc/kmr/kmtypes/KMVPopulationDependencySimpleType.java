
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KMV_PopulationDependency_SimpleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMV_PopulationDependency_SimpleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PD_Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PD_Scope" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PD_Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TerminologyScheme" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TerminologyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TerminologyValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMV_PopulationDependency_SimpleType", propOrder = {
    "pdStatus",
    "pdScope",
    "pdName",
    "terminologyScheme",
    "terminologyCode",
    "terminologyValue",
    "type"
})
public class KMVPopulationDependencySimpleType {

    @XmlElement(name = "PD_Status")
    protected String pdStatus;
    @XmlElement(name = "PD_Scope")
    protected String pdScope;
    @XmlElement(name = "PD_Name")
    protected String pdName;
    @XmlElement(name = "TerminologyScheme")
    protected String terminologyScheme;
    @XmlElement(name = "TerminologyCode")
    protected String terminologyCode;
    @XmlElement(name = "TerminologyValue")
    protected String terminologyValue;
    @XmlElement(name = "Type")
    protected String type;

    /**
     * Gets the value of the pdStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDStatus() {
        return pdStatus;
    }

    /**
     * Sets the value of the pdStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDStatus(String value) {
        this.pdStatus = value;
    }

    /**
     * Gets the value of the pdScope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDScope() {
        return pdScope;
    }

    /**
     * Sets the value of the pdScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDScope(String value) {
        this.pdScope = value;
    }

    /**
     * Gets the value of the pdName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPDName() {
        return pdName;
    }

    /**
     * Sets the value of the pdName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPDName(String value) {
        this.pdName = value;
    }

    /**
     * Gets the value of the terminologyScheme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminologyScheme() {
        return terminologyScheme;
    }

    /**
     * Sets the value of the terminologyScheme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminologyScheme(String value) {
        this.terminologyScheme = value;
    }

    /**
     * Gets the value of the terminologyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminologyCode() {
        return terminologyCode;
    }

    /**
     * Sets the value of the terminologyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminologyCode(String value) {
        this.terminologyCode = value;
    }

    /**
     * Gets the value of the terminologyValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminologyValue() {
        return terminologyValue;
    }

    /**
     * Sets the value of the terminologyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminologyValue(String value) {
        this.terminologyValue = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setType(String value) {
        this.type = value;
    }

}
