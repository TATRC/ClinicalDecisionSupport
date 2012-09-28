
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferenceData.RefRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceData.RefRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Lookup" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ReferenceData.LookupType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceData.RefRequestType", propOrder = {
    "lookup"
})
public class ReferenceDataRefRequestType {

    @XmlElement(name = "Lookup", required = true)
    protected ReferenceDataLookupType lookup;

    /**
     * Gets the value of the lookup property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceDataLookupType }
     *     
     */
    public ReferenceDataLookupType getLookup() {
        return lookup;
    }

    /**
     * Sets the value of the lookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceDataLookupType }
     *     
     */
    public void setLookup(ReferenceDataLookupType value) {
        this.lookup = value;
    }

}
