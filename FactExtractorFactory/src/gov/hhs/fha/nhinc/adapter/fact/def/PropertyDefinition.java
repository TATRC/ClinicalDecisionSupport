/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.def;

/**
 *
 * @author kim
 */
public interface PropertyDefinition extends Cloneable {

  /**
   * 
   * @return
   */
  public String getPropertyClassName();

  /**
   * 
   * @param propertyClassName
   */
  public void setPropertyClassName(String propertyClassName);

  /**
   * 
   * @return
   */
  public String getPropertyName();

  /**
   *
   * @param propertyName
   */
  public void setPropertyName(String propertyName);
  
  /**
   * 
   * @return
   */
  public String getPropertyXpath();

  /**
   * 
   * @param propertyXpath
   */
  public void setPropertyXpath(String propertyXpath);

  public void updateXpathExpression(String indexCharset, String indexVal);

  public PropertyDefinition clone();

}
