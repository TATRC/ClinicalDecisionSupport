/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbook.service;

/**
 *
 * @author cmatser
 */
public class DBServiceException extends Exception {

    public DBServiceException() { super(); }

    public DBServiceException(String message) { super(message); }

    public DBServiceException(Throwable cause) { super(cause); }

    public DBServiceException(String message, Throwable cause) { super(message, cause); }

}
