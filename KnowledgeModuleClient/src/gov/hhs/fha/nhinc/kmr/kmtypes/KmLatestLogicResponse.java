
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KmLatestLogicResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KmLatestLogicResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Logic_NativeForm" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KmLatestLogicResponse", propOrder = {
    "id",
    "logicNativeForm"
})
public class KmLatestLogicResponse {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected int id;
    @XmlElement(name = "Logic_NativeForm", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected byte[] logicNativeForm;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the logicNativeForm property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getLogicNativeForm() {
        return logicNativeForm;
    }

    /**
     * Sets the value of the logicNativeForm property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setLogicNativeForm(byte[] value) {
        this.logicNativeForm = ((byte[]) value);
    }

}
