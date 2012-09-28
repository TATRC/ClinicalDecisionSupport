/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.account.service;

/**
 *
 * @author Sushma
 */
public class AccountServiceException extends Exception {

    public AccountServiceException() {
        super();
    }

    public AccountServiceException(String message) {
        super(message);
    }

    public AccountServiceException(Throwable cause) {
        super(cause);
    }

    public AccountServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
