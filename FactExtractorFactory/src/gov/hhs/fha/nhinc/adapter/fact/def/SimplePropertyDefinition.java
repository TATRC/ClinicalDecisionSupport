/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.def;

import java.io.Serializable;

/**
 *
 * @author kim
 */
public class SimplePropertyDefinition extends PropertyDefinitionImpl implements PropertyDefinition, Serializable, Cloneable {

  public SimplePropertyDefinition() {
  }

  public SimplePropertyDefinition(String propertyName, String propertyClassName) {
    this.propertyClassName = propertyClassName;
    this.propertyName = propertyName;
  }

  public SimplePropertyDefinition(String propertyName, String propertyClassName, String propertyXpath) {
    this.propertyClassName = propertyClassName;
    this.propertyName = propertyName;
    this.propertyXpath = propertyXpath;
  }
  
  public SimplePropertyDefinition(SimplePropertyDefinition def) {
    this.propertyXpath = def.propertyXpath;
    this.propertyClassName = def.propertyClassName;
    this.propertyName = def.propertyName;
  }

  /**
   * Locate the set of characters in the xpath expression and replace with a new value.
   * @param chars set of characters in the xpath expression
   * @param value replacement value
   */
  @Override
  public void updateXpathExpression(String indexCharset, String indexVal) {
    if (propertyXpath != null) {
      propertyXpath = propertyXpath.replace(indexCharset, indexVal);
    }
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("SimplePropertyDefinition[");
    str.append("property name=" + propertyName + ",property xpath=" + propertyXpath + System.getProperty("line.separator"));
    str.append(",class=" + propertyClassName);
    str.append("]");

    return str.toString();
  }
}
