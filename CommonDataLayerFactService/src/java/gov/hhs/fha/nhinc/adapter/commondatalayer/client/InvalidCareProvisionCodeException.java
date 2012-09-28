/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.adapter.commondatalayer.client;

/**
 *
 * @author kim
 */
public class InvalidCareProvisionCodeException extends Exception {
  public InvalidCareProvisionCodeException(String message) {
    super(message);
  }
}
