/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.factservice;

/**
 *
 * @author kim
 */
public class FaultMessage extends Exception {

  private Fault faultInfo;

  public FaultMessage(Fault faultInfo) {
    this.faultInfo = faultInfo;
  }

  public Fault getFaultInfo() {
    return faultInfo;
  }
}
