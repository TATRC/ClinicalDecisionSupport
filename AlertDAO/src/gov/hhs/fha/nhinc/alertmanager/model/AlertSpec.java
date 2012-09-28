/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.model;

/**
 *
 * @author cmatser
 */
public class AlertSpec {
    private Long specificationId;
    private String name;
    private String value;
    private AlertType alertType;

    /**
     * @return the specificationId
     */
    public Long getSpecificationId() {
        return specificationId;
    }

    /**
     * @param specificationId the specificationId to set
     */
    public void setSpecificationId(Long specificationId) {
        this.specificationId = specificationId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the alertType
     */
    public AlertType getAlertType() {
        return alertType;
    }

    /**
     * @param alertType the alertType to set
     */
    public void setAlertType(AlertType alertType) {
        this.alertType = alertType;
    }
}
