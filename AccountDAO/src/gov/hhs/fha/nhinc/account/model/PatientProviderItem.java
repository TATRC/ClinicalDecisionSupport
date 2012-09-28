/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.account.model;

import java.util.Date;

/**
 *
 * @author Sushma
 */
public class PatientProviderItem {

    private Long patientProviderId;
    private Long patientId;
    private String providerId;
    private Date lastUpdated;
    private String status;
    private Date createDateTime;
    private String token;

    /**
     * @return the patientProviderId
     */
    public Long getPatientProviderId() {
        return patientProviderId;
    }

    /**
     * @param patientProviderId the patientProviderId to set
     */
    public void setPatientProviderId(Long patientProviderId) {
        this.patientProviderId = patientProviderId;
    }

    /**
     * @return the patientId
     */
    public Long getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
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
     * @return the lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

     /**
     * @return the createDateTime
     */
    public Date getCreateDateTime() {
        return createDateTime;
    }

    /**
     * @param createDateTime the createDateTime to set
     */
    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }
}
