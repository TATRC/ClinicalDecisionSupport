/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.account.model;

/**
 *
 * @author Sushma
 */
public class PatientProviderQueryParams {

    private Long patientId;
    private String providerId;

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
}
