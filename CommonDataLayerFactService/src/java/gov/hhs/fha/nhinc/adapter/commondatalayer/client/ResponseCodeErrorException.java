/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.client;

/**
 *
 * @author kim
 */
public class ResponseCodeErrorException extends Exception {

  String code = null;

  public ResponseCodeErrorException(String code) {
    super(code);
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
