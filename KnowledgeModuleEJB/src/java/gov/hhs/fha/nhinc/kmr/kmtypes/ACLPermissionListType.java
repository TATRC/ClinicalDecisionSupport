
package gov.hhs.fha.nhinc.kmr.kmtypes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ACL_PermissionListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ACL_PermissionListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ACL_Permission" type="{urn:gov:hhs:fha:nhinc:kmr:kmtypes}ACL_PermissionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ACL_PermissionListType", propOrder = {
    "aclPermission"
})
public class ACLPermissionListType {

    @XmlElement(name = "ACL_Permission")
    protected List<ACLPermissionType> aclPermission;

    /**
     * Gets the value of the aclPermission property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the aclPermission property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getACLPermission().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ACLPermissionType }
     * 
     * 
     */
    public List<ACLPermissionType> getACLPermission() {
        if (aclPermission == null) {
            aclPermission = new ArrayList<ACLPermissionType>();
        }
        return this.aclPermission;
    }

}
