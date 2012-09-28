
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ACL_SimpleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ACL_SimpleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="User_OrganizationLevel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UR_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AuthorId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ACL_SimpleType", propOrder = {
    "userOrganizationLevel",
    "urName",
    "authorId"
})
public class ACLSimpleType {

    @XmlElement(name = "User_OrganizationLevel", required = true)
    protected String userOrganizationLevel;
    @XmlElement(name = "UR_Name", required = true)
    protected String urName;
    @XmlElement(name = "AuthorId", required = true)
    protected String authorId;

    /**
     * Gets the value of the userOrganizationLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserOrganizationLevel() {
        return userOrganizationLevel;
    }

    /**
     * Sets the value of the userOrganizationLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserOrganizationLevel(String value) {
        this.userOrganizationLevel = value;
    }

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
     * Gets the value of the authorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorId() {
        return authorId;
    }

    /**
     * Sets the value of the authorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorId(String value) {
        this.authorId = value;
    }

}
