/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.ldapaccess;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cmatser
 */
public class RoleDTO {
    private String commonName;
    private String departmentNumber;
    private String description;
    private String displayName;
    private String fax;
    private String locationCode;
    private String mail;
    private String organizationalUnit;
    private String pager;
    private List<String> roleOccupants;
    private String telephoneNumber;

    /**
     * @return the commonName
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * @param commonName the commonName to set
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    /**
     * @return the departmentNumber
     */
    public String getDepartmentNumber() {
        return departmentNumber;
    }

    /**
     * @param departmentNumber the departmentNumber to set
     */
    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the locationCode
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * @param locationCode the locationCode to set
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the organizationalUnit
     */
    public String getOrganizationalUnit() {
        return organizationalUnit;
    }

    /**
     * @param organizationalUnit the organizationalUnit to set
     */
    public void setOrganizationalUnit(String organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    /**
     * @return the pager
     */
    public String getPager() {
        return pager;
    }

    /**
     * @param pager the pager to set
     */
    public void setPager(String pager) {
        this.pager = pager;
    }

    /**
     * @return the roleOccupants
     */
    public List<String> getRoleOccupants() {
        if (roleOccupants == null) {
            roleOccupants = new LinkedList<String>();
        }
        return roleOccupants;
    }

    /**
     * @param roleOccupants the roleOccupants to set
     */
    public void setRoleOccupants(List<String> roleOccupants) {
        this.roleOccupants = roleOccupants;
    }

    /**
     * @return the telephoneNumber
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * @param telephoneNumber the telephoneNumber to set
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
