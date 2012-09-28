
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FindKmIdsResponseListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FindKmIdsResponseListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "FindKmIdsResponseListType", propOrder = {
    "kms"
})
public class FindKmIdsResponseListType {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected KmIdType kms;

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
