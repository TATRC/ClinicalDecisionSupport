/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.service;

/**
 *
 * @author cmatser
 */
public class TaskServiceException extends Exception {

    public TaskServiceException() { super(); }

    public TaskServiceException(String message) { super(message); }

    public TaskServiceException(Throwable cause) { super(cause); }

    public TaskServiceException(String message, Throwable cause) { super(message, cause); }

}
