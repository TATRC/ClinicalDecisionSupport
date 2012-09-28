
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KmImportRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KmImportRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ImportRequestType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KmImportRequestType", propOrder = {
    "request"
})
public class KmImportRequestType {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected ImportRequestType request;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link ImportRequestType }
     *     
     */
    public ImportRequestType getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImportRequestType }
     *     
     */
    public void setRequest(ImportRequestType value) {
        this.request = value;
    }

}
