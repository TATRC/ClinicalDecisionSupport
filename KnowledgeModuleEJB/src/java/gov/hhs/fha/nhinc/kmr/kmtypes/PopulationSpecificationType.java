
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PopulationSpecificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PopulationSpecificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TerminologyScheme" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TerminologyCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TerminologyValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PopulationSpecificationType", propOrder = {
    "terminologyScheme",
    "terminologyCode",
    "terminologyValue",
    "type"
})
public class PopulationSpecificationType {

    @XmlElement(name = "TerminologyScheme", required = true)
    protected String terminologyScheme;
    @XmlElement(name = "TerminologyCode", required = true)
    protected String terminologyCode;
    @XmlElement(name = "TerminologyValue", required = true)
    protected String terminologyValue;
    @XmlElement(name = "Type", required = true)
    protected String type;

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
