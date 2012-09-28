
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KmByIdRequestListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KmByIdRequestListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestReference" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}RequestRefType"/>
 *         &lt;element name="ACL" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ACL_SimpleType"/>
 *         &lt;element name="kms" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}KmIdType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KmByIdRequestListType", propOrder = {
    "requestReference",
    "acl",
    "kms"
})
public class KmByIdRequestListType {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected RequestRefType requestReference;
    @XmlElement(name = "ACL", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected ACLSimpleType acl;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected KmIdType kms;

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
     * Gets the value of the acl property.
     * 
     * @return
     *     possible object is
     *     {@link ACLSimpleType }
     *     
     */
    public ACLSimpleType getACL() {
        return acl;
    }

    /**
     * Sets the value of the acl property.
     * 
     * @param value
     *     allowed object is
     *     {@link ACLSimpleType }
     *     
     */
    public void setACL(ACLSimpleType value) {
        this.acl = value;
    }

    /**
     * Gets the value of the kms property.
     * 
     * @return
     *     possible object is
     *     {@link KmIdType }
     *     
     */
    public KmIdType getKms() {
        return kms;
    }

    /**
     * Sets the value of the kms property.
     * 
     * @param value
     *     allowed object is
     *     {@link KmIdType }
     *     
     */
    public void setKms(KmIdType value) {
        this.kms = value;
    }

}
