/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.model;

/**
 * Alert contacts for direct alert.
 *
 * @author cmatser
 */
public class AlertContact {

    protected Long contactId;
    protected String method;
    protected String providerId;
    protected String providerName;
    protected String providerLdap;
    protected String organizationId;
    protected String clinicId;
    protected String role;
    protected String location;
    protected AlertTicket ticket;

    /**
     * @return the contactId
     */
    public Long getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

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
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    /**
     * @return the providerName
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * @param providerName the providerName to set
     */
    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    /**
     * @return the providerLdap
     */
    public String getProviderLdap() {
        return providerLdap;
    }

    /**
     * @param providerLdap the providerLdap to set
     */
    public void setProviderLdap(String providerLdap) {
        this.providerLdap = providerLdap;
    }

    /**
     * @return the organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * @param organizationId the organizationId to set
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * @return the clinicId
     */
    public String getClinicId() {
        return clinicId;
    }

    /**
     * @param clinicId the clinicId to set
     */
    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
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

    /**
     * @return the ticket
     */
    public AlertTicket getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(AlertTicket ticket) {
        this.ticket = ticket;
    }

}