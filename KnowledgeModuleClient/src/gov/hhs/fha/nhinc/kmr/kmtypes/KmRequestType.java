
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KmRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KmRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}MultiParamsRequestType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KmRequestType", propOrder = {
    "request"
})
public class KmRequestType {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected MultiParamsRequestType request;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link MultiParamsRequestType }
     *     
     */
    public MultiParamsRequestType getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultiParamsRequestType }
     *     
     */
    public void setRequest(MultiParamsRequestType value) {
        this.request = value;
    }

}
