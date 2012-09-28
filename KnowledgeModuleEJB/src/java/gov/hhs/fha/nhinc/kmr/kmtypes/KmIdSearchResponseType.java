
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KmIdSearchResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KmIdSearchResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="exportFilename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requestReference" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}RequestRefType"/>
 *         &lt;element name="responseList" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ExportResponseListType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KmIdSearchResponseType", propOrder = {
    "exportFilename",
    "requestReference",
    "responseList"
})
public class KmIdSearchResponseType {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String exportFilename;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected RequestRefType requestReference;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected ExportResponseListType responseList;

    /**
     * Gets the value of the exportFilename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExportFilename() {
        return exportFilename;
    }

    /**
     * Sets the value of the exportFilename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExportFilename(String value) {
        this.exportFilename = value;
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
     * Gets the value of the responseList property.
     * 
     * @return
     *     possible object is
     *     {@link ExportResponseListType }
     *     
     */
    public ExportResponseListType getResponseList() {
        return responseList;
    }

    /**
     * Sets the value of the responseList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExportResponseListType }
     *     
     */
    public void setResponseList(ExportResponseListType value) {
        this.responseList = value;
    }

}
