/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.model;

import java.util.Set;

/**
 *
 * @author cmatser
 */
public class TaskType {
    private Long taskTypeId;
    private String name;
    private String description;
    private Set<Specification> specifications;

    /**
     * @return the id
     */
    public Long getTaskTypeId() {
        return taskTypeId;
    }

    /**
     * @param id the id to set
     */
    public void setTaskTypeId(Long taskTypeId) {
        this.taskTypeId = taskTypeId;
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
    public Set<Specification> getSpecifications() {
        return specifications;
    }

    /**
     * @param specifications the specifications to set
     */
    public void setSpecifications(Set<Specification> specifications) {
        this.specifications = specifications;
    }

}
