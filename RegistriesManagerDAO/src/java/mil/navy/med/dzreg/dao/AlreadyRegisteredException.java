/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mil.navy.med.dzreg.dao;

/**
 *
 * @author kim
 */
public class AlreadyRegisteredException extends Exception {

  public AlreadyRegisteredException(Throwable arg0) {
    super(arg0);
  }

  public AlreadyRegisteredException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public AlreadyRegisteredException(String arg0) {
    super(arg0);
  }

  public AlreadyRegisteredException() {
  }

}
