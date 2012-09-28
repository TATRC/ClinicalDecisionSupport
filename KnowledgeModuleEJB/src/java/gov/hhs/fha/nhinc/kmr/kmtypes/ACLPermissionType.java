
package gov.hhs.fha.nhinc.kmr.kmtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ACL_PermissionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ACL_PermissionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UR_Permission" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="UserRole" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}UserRoleType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ACL_PermissionType", propOrder = {
    "urPermission",
    "userRole"
})
public class ACLPermissionType {

    @XmlElement(name = "UR_Permission", required = true)
    protected String urPermission;
    @XmlElement(name = "UserRole", required = true)
    protected UserRoleType userRole;

    /**
     * Gets the value of the urPermission property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURPermission() {
        return urPermission;
    }

    /**
     * Sets the value of the urPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURPermission(String value) {
        this.urPermission = value;
    }

    /**
     * Gets the value of the userRole property.
     * 
     * @return
     *     possible object is
     *     {@link UserRoleType }
     *     
     */
    public UserRoleType getUserRole() {
        return userRole;
    }

    /**
     * Sets the value of the userRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserRoleType }
     *     
     */
    public void setUserRole(UserRoleType value) {
        this.userRole = value;
    }

}
