/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.factservice;

import gov.hhs.fha.nhinc.adapter.commondatalayer.client.InvalidParameterException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.client.NonMatchingQueryIdException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.client.ResponseCodeErrorException;
import java.rmi.RemoteException;

/**
 *
 * @author kim
 */
public class Fault {

  public final static String UNKNOWN_ERROR = "9999";
  public final static String PARAMETER_ERROR = "0001";
  public final static String MONMATCHING_RESPONSE_ERROR = "0002";
  public final static String RESPONSE_ERROR = "0003";
  String faultMessage;
  String faultCode;

  public Fault() {
  }

  public Fault(String faultCode, String faultMessage) {
    this.faultCode = faultCode;
    this.faultMessage = faultMessage;
  }

  public String getFaultCode() {
    return faultCode;
  }

  public void setFaultCode(String faultCode) {
    this.faultCode = faultCode;
  }

  public String getFaultMessage() {
    return faultMessage;
  }

  public void setFaultMessage(String faultMessage) {
    this.faultMessage = faultMessage;
  }

  public static String getErrorCode(Exception e) {
    if (e instanceof InvalidParameterException) {
      return Fault.PARAMETER_ERROR;
    }

    if (e instanceof NonMatchingQueryIdException) {
      return Fault.MONMATCHING_RESPONSE_ERROR;
    }

    if (e instanceof ResponseCodeErrorException) {
      return Fault.RESPONSE_ERROR;
    }

    return Fault.UNKNOWN_ERROR;
  }
}
