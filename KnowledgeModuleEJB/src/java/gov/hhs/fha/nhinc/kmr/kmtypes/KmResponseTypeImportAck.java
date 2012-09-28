
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KmResponseType_importAck complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KmResponseType_importAck">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="importFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requestReference" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}RequestRefType"/>
 *         &lt;element name="ackList" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ImportResponseListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KmResponseType_importAck", propOrder = {
    "importFilename",
    "requestReference",
    "ackList"
})
public class KmResponseTypeImportAck {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String importFilename;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected RequestRefType requestReference;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected ImportResponseListType ackList;

    /**
     * Gets the value of the importFilename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportFilename() {
        return importFilename;
    }

    /**
     * Sets the value of the importFilename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportFilename(String value) {
        this.importFilename = value;
    }

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
     * Gets the value of the ackList property.
     * 
     * @return
     *     possible object is
     *     {@link ImportResponseListType }
     *     
     */
    public ImportResponseListType getAckList() {
        return ackList;
    }

    /**
     * Sets the value of the ackList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImportResponseListType }
     *     
     */
    public void setAckList(ImportResponseListType value) {
        this.ackList = value;
    }

}
