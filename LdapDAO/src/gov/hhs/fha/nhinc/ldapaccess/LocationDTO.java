/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.ldapaccess;

/**
 *
 * @author cmatser
 */
public class LocationDTO {
    private String commonName;
    private String departmentNumber;
    private String description;
    private String destinationIndicator;
    private String displayName;
    private String organizationalUnit;
    private String telephoneNumber;

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestinationIndicator() {
        return destinationIndicator;
    }

    public void setDestinationIndicator(String destinationIndicator) {
        this.destinationIndicator = destinationIndicator;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOrganizationalUnit() {
        return organizationalUnit;
    }

    public void setOrganizationalUnit(String organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

}