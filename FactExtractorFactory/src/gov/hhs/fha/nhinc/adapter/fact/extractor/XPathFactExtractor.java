/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.adapter.fact.extractor;

/**
 *
 * @author kim
 */
public interface XPathFactExtractor extends FactExtractor {

   /**
    * Return the base xpath expression
    */
   public String getFactBaseXpath();

   /**
    * Set the expath expression
    * @param expression
    */
   public void setFactBaseXpath(String expression);
  
}
