
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KMV_FactDependency_SimpleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMV_FactDependency_SimpleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TerminologyScheme" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TerminologyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMV_FactDependency_SimpleType", propOrder = {
    "type",
    "terminologyScheme",
    "terminologyCode"
})
public class KMVFactDependencySimpleType {

    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "TerminologyScheme")
    protected String terminologyScheme;
    @XmlElement(name = "TerminologyCode")
    protected String terminologyCode;

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

}
