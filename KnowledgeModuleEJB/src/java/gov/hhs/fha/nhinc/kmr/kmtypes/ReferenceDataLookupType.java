
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferenceData.LookupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceData.LookupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Lookup_Type" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ReferenceData.LookupRefType_Type" minOccurs="0"/>
 *         &lt;element name="Lookup_System" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ReferenceData.LookupRefType_System" minOccurs="0"/>
 *         &lt;element name="Lookup_Code" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ReferenceData.LookupRefType_Code" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="StartingRecNumber" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *       &lt;attribute name="Reference" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MaxRecordsToShow" type="{http://www.w3.org/2001/XMLSchema}int" default="200" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceData.LookupType", propOrder = {
    "lookupType",
    "lookupSystem",
    "lookupCode"
})
public class ReferenceDataLookupType {

    @XmlElement(name = "Lookup_Type")
    protected ReferenceDataLookupRefTypeType lookupType;
    @XmlElement(name = "Lookup_System")
    protected ReferenceDataLookupRefTypeSystem lookupSystem;
    @XmlElement(name = "Lookup_Code")
    protected ReferenceDataLookupRefTypeCode lookupCode;
    @XmlAttribute(name = "StartingRecNumber")
    protected Integer startingRecNumber;
    @XmlAttribute(name = "Reference", required = true)
    protected String reference;
    @XmlAttribute(name = "MaxRecordsToShow")
    protected Integer maxRecordsToShow;

    /**
     * Gets the value of the lookupType property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceDataLookupRefTypeType }
     *     
     */
    public ReferenceDataLookupRefTypeType getLookupType() {
        return lookupType;
    }

    /**
     * Sets the value of the lookupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceDataLookupRefTypeType }
     *     
     */
    public void setLookupType(ReferenceDataLookupRefTypeType value) {
        this.lookupType = value;
    }

    /**
     * Gets the value of the lookupSystem property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceDataLookupRefTypeSystem }
     *     
     */
    public ReferenceDataLookupRefTypeSystem getLookupSystem() {
        return lookupSystem;
    }

    /**
     * Sets the value of the lookupSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceDataLookupRefTypeSystem }
     *     
     */
    public void setLookupSystem(ReferenceDataLookupRefTypeSystem value) {
        this.lookupSystem = value;
    }

    /**
     * Gets the value of the lookupCode property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceDataLookupRefTypeCode }
     *     
     */
    public ReferenceDataLookupRefTypeCode getLookupCode() {
        return lookupCode;
    }

    /**
     * Sets the value of the lookupCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceDataLookupRefTypeCode }
     *     
     */
    public void setLookupCode(ReferenceDataLookupRefTypeCode value) {
        this.lookupCode = value;
    }

    /**
     * Gets the value of the startingRecNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getStartingRecNumber() {
        if (startingRecNumber == null) {
            return  0;
        } else {
            return startingRecNumber;
        }
    }

    /**
     * Sets the value of the startingRecNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStartingRecNumber(Integer value) {
        this.startingRecNumber = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Gets the value of the maxRecordsToShow property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getMaxRecordsToShow() {
        if (maxRecordsToShow == null) {
            return  200;
        } else {
            return maxRecordsToShow;
        }
    }

    /**
     * Sets the value of the maxRecordsToShow property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxRecordsToShow(Integer value) {
        this.maxRecordsToShow = value;
    }

}
