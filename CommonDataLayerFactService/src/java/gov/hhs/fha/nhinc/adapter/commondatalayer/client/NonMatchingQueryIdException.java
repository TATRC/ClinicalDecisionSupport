/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.client;

/**
 *
 * @author kim
 */
public class NonMatchingQueryIdException extends Exception {

  public NonMatchingQueryIdException(String message) {
    super(message);
  }
}
