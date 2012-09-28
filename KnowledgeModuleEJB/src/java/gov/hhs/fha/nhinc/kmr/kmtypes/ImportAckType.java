
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ImportAckType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ImportAckType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kmId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="kmName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="importStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ImportAckType", propOrder = {
    "kmId",
    "kmName",
    "importStatus",
    "description"
})
public class ImportAckType {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected int kmId;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String kmName;
    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String importStatus;
    @XmlElement(name = "Description", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String description;

    /**
     * Gets the value of the kmId property.
     * 
     */
    public int getKmId() {
        return kmId;
    }

    /**
     * Sets the value of the kmId property.
     * 
     */
    public void setKmId(int value) {
        this.kmId = value;
    }

    /**
     * Gets the value of the kmName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKmName() {
        return kmName;
    }

    /**
     * Sets the value of the kmName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKmName(String value) {
        this.kmName = value;
    }

    /**
     * Gets the value of the importStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImportStatus() {
        return importStatus;
    }

    /**
     * Sets the value of the importStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImportStatus(String value) {
        this.importStatus = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
