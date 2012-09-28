/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.model;

/**
 *
 * @author cmatser
 */
public class Specification {
    private Long specificationId;
    private String name;
    private String value;
    private TaskType taskType;

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
     * @return the taskType
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * @param taskType the taskType to set
     */
    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }
}
