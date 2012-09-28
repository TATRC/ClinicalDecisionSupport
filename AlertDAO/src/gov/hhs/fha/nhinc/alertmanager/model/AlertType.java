/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author cmatser
 */
public class AlertType {
    private Long alertTypeId;
    private String name;
    private String description;
    private Set<AlertSpec> specifications;

    /**
     * @return the id
     */
    public Long getAlertTypeId() {
        return alertTypeId;
    }

    /**
     * @param id the id to set
     */
    public void setAlertTypeId(Long alertTypeId) {
        this.alertTypeId = alertTypeId;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * @return the specifications
     */
    public Set<AlertSpec> getSpecifications() {

        if (specifications == null) {
            specifications = new HashSet<AlertSpec>();
        }

        return specifications;
    }

    /**
     * @param specifications the specifications to set
     */
    public void setSpecifications(Set<AlertSpec> specifications) {
        this.specifications = specifications;
    }

}
