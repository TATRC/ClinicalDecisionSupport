
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferenceData.LookupRefType_Code complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceData.LookupRefType_Code">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodeType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodeSystem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceData.LookupRefType_Code", propOrder = {
    "codeType",
    "codeSystem",
    "contentCode"
})
public class ReferenceDataLookupRefTypeCode {

    @XmlElement(name = "CodeType", required = true, nillable = true)
    protected String codeType;
    @XmlElement(name = "CodeSystem", required = true, nillable = true)
    protected String codeSystem;
    @XmlElement(name = "ContentCode", required = true, nillable = true)
    protected String contentCode;

    /**
     * Gets the value of the codeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeType() {
        return codeType;
    }

    /**
     * Sets the value of the codeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeType(String value) {
        this.codeType = value;
    }

    /**
     * Gets the value of the codeSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeSystem() {
        return codeSystem;
    }

    /**
     * Sets the value of the codeSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeSystem(String value) {
        this.codeSystem = value;
    }

    /**
     * Gets the value of the contentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentCode() {
        return contentCode;
    }

    /**
     * Sets the value of the contentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentCode(String value) {
        this.contentCode = value;
    }

}
