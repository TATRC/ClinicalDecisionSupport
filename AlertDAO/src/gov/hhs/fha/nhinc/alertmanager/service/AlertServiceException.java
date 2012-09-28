/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.service;

/**
 *
 * @author cmatser
 */
public class AlertServiceException extends Exception {

    public AlertServiceException() { super(); }

    public AlertServiceException(String message) { super(message); }

    public AlertServiceException(Throwable cause) { super(cause); }

    public AlertServiceException(String message, Throwable cause) { super(message, cause); }

}
