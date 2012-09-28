
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserRoleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserRoleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UR_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UR_Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserRoleType", propOrder = {
    "urName",
    "urDescription"
})
public class UserRoleType {

    @XmlElement(name = "UR_Name", required = true)
    protected String urName;
    @XmlElement(name = "UR_Description", required = true)
    protected String urDescription;

    /**
     * Gets the value of the urName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURName() {
        return urName;
    }

    /**
     * Sets the value of the urName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURName(String value) {
        this.urName = value;
    }

    /**
     * Gets the value of the urDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURDescription() {
        return urDescription;
    }

    /**
     * Sets the value of the urDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURDescription(String value) {
        this.urDescription = value;
    }

}
