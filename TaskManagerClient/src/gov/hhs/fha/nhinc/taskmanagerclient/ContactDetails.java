/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanagerclient;

/**
 * Part of task message, contact information.
 *
 * @author cmatser
 */
public class ContactDetails
        implements java.io.Serializable {
    private String method;
    private String provider;
    private String clinic;
    private String organization;
    private String role;
    private String location;

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * @return the clinic
     */
    public String getClinic() {
        return clinic;
    }

    /**
     * @param clinic the clinic to set
     */
    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    /**
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * @param organization the organization to set
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

}