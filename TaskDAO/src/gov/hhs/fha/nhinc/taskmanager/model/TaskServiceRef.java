/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.model;

/**
 *
 * @author cmatser
 */
public class TaskServiceRef {
    private Long taskServiceRefId;
    private String type;
    private String name;
    private String description;
    private String location;
    private String serviceParam1;
    private String serviceParam2;
    private String serviceParam3;
    private String serviceParam4;

    /**
     * @return the taskServiceRefId
     */
    public Long getTaskServiceRefId() {
        return taskServiceRefId;
    }

    /**
     * @param taskServiceRefId the taskServiceRefId to set
     */
    public void setTaskServiceRefId(Long taskServiceRefId) {
        this.taskServiceRefId = taskServiceRefId;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
    public void setDescription(String description) {
        this.description = description;
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
     * @return the serviceParam1
     */
    public String getServiceParam1() {
        return serviceParam1;
    }

    /**
     * @param serviceParam1 the serviceParam1 to set
     */
    public void setServiceParam1(String serviceParam1) {
        this.serviceParam1 = serviceParam1;
    }

    /**
     * @return the serviceParam2
     */
    public String getServiceParam2() {
        return serviceParam2;
    }

    /**
     * @param serviceParam2 the serviceParam2 to set
     */
    public void setServiceParam2(String serviceParam2) {
        this.serviceParam2 = serviceParam2;
    }

    /**
     * @return the serviceParam3
     */
    public String getServiceParam3() {
        return serviceParam3;
    }

    /**
     * @param serviceParam3 the serviceParam3 to set
     */
    public void setServiceParam3(String serviceParam3) {
        this.serviceParam3 = serviceParam3;
    }

    /**
     * @return the serviceParam4
     */
    public String getServiceParam4() {
        return serviceParam4;
    }

    /**
     * @param serviceParam4 the serviceParam4 to set
     */
    public void setServiceParam4(String serviceParam4) {
        this.serviceParam4 = serviceParam4;
    }

}