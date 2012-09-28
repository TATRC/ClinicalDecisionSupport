
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KmIdSearchRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KmIdSearchRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KmByIdRequestListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KmIdSearchRequestType", propOrder = {
    "request"
})
public class KmIdSearchRequestType {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected KmByIdRequestListType request;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link KmByIdRequestListType }
     *     
     */
    public KmByIdRequestListType getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link KmByIdRequestListType }
     *     
     */
    public void setRequest(KmByIdRequestListType value) {
        this.request = value;
    }

}
