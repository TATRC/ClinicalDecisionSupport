
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
 *         &lt;element name="Logic_IntermediateForm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Logic_NativeForm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Logic_BinaryForm" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
    "logicIntermediateForm",
    "logicNativeForm",
    "logicBinaryForm"
})
public class KmLatestLogicResponse {

    @XmlElement(namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes")
    protected int id;
    @XmlElement(name = "Logic_IntermediateForm", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String logicIntermediateForm;
    @XmlElement(name = "Logic_NativeForm", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected String logicNativeForm;
    @XmlElement(name = "Logic_BinaryForm", namespace = "urn:gov:hhs:fha:nhinc:kmr:kmtypes", required = true)
    protected byte[] logicBinaryForm;

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
     * Gets the value of the logicIntermediateForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogicIntermediateForm() {
        return logicIntermediateForm;
    }

    /**
     * Sets the value of the logicIntermediateForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogicIntermediateForm(String value) {
        this.logicIntermediateForm = value;
    }

    /**
     * Gets the value of the logicNativeForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogicNativeForm() {
        return logicNativeForm;
    }

    /**
     * Sets the value of the logicNativeForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogicNativeForm(String value) {
        this.logicNativeForm = value;
    }

    /**
     * Gets the value of the logicBinaryForm property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getLogicBinaryForm() {
        return logicBinaryForm;
    }

    /**
     * Sets the value of the logicBinaryForm property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setLogicBinaryForm(byte[] value) {
        this.logicBinaryForm = ((byte[]) value);
    }

}
