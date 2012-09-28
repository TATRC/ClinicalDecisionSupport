
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KMV_SupportingReferenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMV_SupportingReferenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KM_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="KM_VersionNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SR_ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="SR_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SR_Type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SR_Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SR_Reference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SR_Document" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMV_SupportingReferenceType", propOrder = {
    "kmid",
    "kmVersionNum",
    "srid",
    "srName",
    "srType",
    "srDescription",
    "srReference",
    "srDocument"
})
public class KMVSupportingReferenceType {

    @XmlElement(name = "KM_ID")
    protected int kmid;
    @XmlElement(name = "KM_VersionNum")
    protected int kmVersionNum;
    @XmlElement(name = "SR_ID")
    protected int srid;
    @XmlElement(name = "SR_Name", required = true)
    protected String srName;
    @XmlElement(name = "SR_Type", required = true)
    protected String srType;
    @XmlElement(name = "SR_Description", required = true)
    protected String srDescription;
    @XmlElement(name = "SR_Reference", required = true)
    protected String srReference;
    @XmlElement(name = "SR_Document", required = true)
    protected String srDocument;

    /**
     * Gets the value of the kmid property.
     * 
     */
    public int getKMID() {
        return kmid;
    }

    /**
     * Sets the value of the kmid property.
     * 
     */
    public void setKMID(int value) {
        this.kmid = value;
    }

    /**
     * Gets the value of the kmVersionNum property.
     * 
     */
    public int getKMVersionNum() {
        return kmVersionNum;
    }

    /**
     * Sets the value of the kmVersionNum property.
     * 
     */
    public void setKMVersionNum(int value) {
        this.kmVersionNum = value;
    }

    /**
     * Gets the value of the srid property.
     * 
     */
    public int getSRID() {
        return srid;
    }

    /**
     * Sets the value of the srid property.
     * 
     */
    public void setSRID(int value) {
        this.srid = value;
    }

    /**
     * Gets the value of the srName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRName() {
        return srName;
    }

    /**
     * Sets the value of the srName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRName(String value) {
        this.srName = value;
    }

    /**
     * Gets the value of the srType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRType() {
        return srType;
    }

    /**
     * Sets the value of the srType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRType(String value) {
        this.srType = value;
    }

    /**
     * Gets the value of the srDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRDescription() {
        return srDescription;
    }

    /**
     * Sets the value of the srDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRDescription(String value) {
        this.srDescription = value;
    }

    /**
     * Gets the value of the srReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRReference() {
        return srReference;
    }

    /**
     * Sets the value of the srReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRReference(String value) {
        this.srReference = value;
    }

    /**
     * Gets the value of the srDocument property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRDocument() {
        return srDocument;
    }

    /**
     * Sets the value of the srDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRDocument(String value) {
        this.srDocument = value;
    }

}
