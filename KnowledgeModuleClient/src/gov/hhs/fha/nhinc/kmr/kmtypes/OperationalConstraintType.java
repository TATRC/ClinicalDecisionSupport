
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperationalConstraintType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperationalConstraintType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OC_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="OC_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OC_Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OC_Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OC_Keywords" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationalConstraintType", propOrder = {
    "ocid",
    "ocName",
    "ocType",
    "ocDescription",
    "ocKeywords"
})
public class OperationalConstraintType {

    @XmlElement(name = "OC_ID")
    protected int ocid;
    @XmlElement(name = "OC_Name", required = true)
    protected String ocName;
    @XmlElement(name = "OC_Type", required = true)
    protected String ocType;
    @XmlElement(name = "OC_Description", required = true)
    protected String ocDescription;
    @XmlElement(name = "OC_Keywords", required = true)
    protected String ocKeywords;

    /**
     * Gets the value of the ocid property.
     * 
     */
    public int getOCID() {
        return ocid;
    }

    /**
     * Sets the value of the ocid property.
     * 
     */
    public void setOCID(int value) {
        this.ocid = value;
    }

    /**
     * Gets the value of the ocName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCName() {
        return ocName;
    }

    /**
     * Sets the value of the ocName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCName(String value) {
        this.ocName = value;
    }

    /**
     * Gets the value of the ocType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCType() {
        return ocType;
    }

    /**
     * Sets the value of the ocType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCType(String value) {
        this.ocType = value;
    }

    /**
     * Gets the value of the ocDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCDescription() {
        return ocDescription;
    }

    /**
     * Sets the value of the ocDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCDescription(String value) {
        this.ocDescription = value;
    }

    /**
     * Gets the value of the ocKeywords property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOCKeywords() {
        return ocKeywords;
    }

    /**
     * Sets the value of the ocKeywords property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOCKeywords(String value) {
        this.ocKeywords = value;
    }

}
