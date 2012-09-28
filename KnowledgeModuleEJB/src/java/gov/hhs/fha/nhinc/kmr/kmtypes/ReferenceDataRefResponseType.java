
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReferenceData.RefResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferenceData.RefResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="response" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ReferenceData.ResponseType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferenceData.RefResponseType", propOrder = {
    "response"
})
public class ReferenceDataRefResponseType {

    @XmlElement(required = true)
    protected ReferenceDataResponseType response;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceDataResponseType }
     *     
     */
    public ReferenceDataResponseType getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceDataResponseType }
     *     
     */
    public void setResponse(ReferenceDataResponseType value) {
        this.response = value;
    }

}
