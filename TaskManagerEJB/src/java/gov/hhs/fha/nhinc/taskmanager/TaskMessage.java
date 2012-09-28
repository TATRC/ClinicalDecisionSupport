/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

/**
 * Task message for task handler processing.
 *
 * @author cmatser
 */
public class TaskMessage
        implements java.io.Serializable {
    private String taskID;

    /**
     * @return the taskID
     */
    public String getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

}
