
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MultiParamsRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MultiParamsRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestReference" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}RequestRefType"/>
 *         &lt;element name="km" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KmByParamsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiParamsRequestType", propOrder = {
    "requestReference",
    "km"
})
public class MultiParamsRequestType {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected RequestRefType requestReference;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected KmByParamsType km;

    /**
     * Gets the value of the requestReference property.
     * 
     * @return
     *     possible object is
     *     {@link RequestRefType }
     *     
     */
    public RequestRefType getRequestReference() {
        return requestReference;
    }

    /**
     * Sets the value of the requestReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestRefType }
     *     
     */
    public void setRequestReference(RequestRefType value) {
        this.requestReference = value;
    }

    /**
     * Gets the value of the km property.
     * 
     * @return
     *     possible object is
     *     {@link KmByParamsType }
     *     
     */
    public KmByParamsType getKm() {
        return km;
    }

    /**
     * Sets the value of the km property.
     * 
     * @param value
     *     allowed object is
     *     {@link KmByParamsType }
     *     
     */
    public void setKm(KmByParamsType value) {
        this.km = value;
    }

}
