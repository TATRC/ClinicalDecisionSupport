/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.adapter.fact.extractor;

/**
 *
 * @author kim
 */
public class InvalidFactExtractorTypeException extends Exception {

   public InvalidFactExtractorTypeException(Throwable arg0) {
      super(arg0);
   }

   public InvalidFactExtractorTypeException(String arg0, Throwable arg1) {
      super(arg0, arg1);
   }

   public InvalidFactExtractorTypeException(String arg0) {
      super(arg0);
   }
}
