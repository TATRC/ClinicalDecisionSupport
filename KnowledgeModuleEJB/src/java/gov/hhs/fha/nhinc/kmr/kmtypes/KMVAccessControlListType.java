
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for KMV_AccessControlListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="KMV_AccessControlListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ACL_Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ACL_Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ACL_PermissionList" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ACL_PermissionListType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KMV_AccessControlListType", propOrder = {
    "aclName",
    "aclDescription",
    "aclPermissionList"
})
public class KMVAccessControlListType {

    @XmlElement(name = "ACL_Name", required = true)
    protected String aclName;
    @XmlElement(name = "ACL_Description", required = true)
    protected String aclDescription;
    @XmlElement(name = "ACL_PermissionList")
    protected List<ACLPermissionListType> aclPermissionList;

    /**
     * Gets the value of the aclName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACLName() {
        return aclName;
    }

    /**
     * Sets the value of the aclName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACLName(String value) {
        this.aclName = value;
    }

    /**
     * Gets the value of the aclDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACLDescription() {
        return aclDescription;
    }

    /**
     * Sets the value of the aclDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACLDescription(String value) {
        this.aclDescription = value;
    }

    /**
     * Gets the value of the aclPermissionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aclPermissionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getACLPermissionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ACLPermissionListType }
     * 
     * 
     */
    public List<ACLPermissionListType> getACLPermissionList() {
        if (aclPermissionList == null) {
            aclPermissionList = new ArrayList<ACLPermissionListType>();
        }
        return this.aclPermissionList;
    }

}
