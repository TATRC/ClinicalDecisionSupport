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
public abstract class PropertyDefinitionImpl implements PropertyDefinition, Serializable, Cloneable {

  protected String propertyXpath;
  protected String propertyClassName;
  protected String propertyName;

  @Override
  public String getPropertyClassName() {
    return propertyClassName;
  }

  @Override
  public void setPropertyClassName(String propertyClassName) {
    this.propertyClassName = propertyClassName;
  }

  @Override
  public String getPropertyName() {
    return propertyName;
  }

  @Override
  public void setPropertyName(String propertyName) {
    this.propertyName = propertyName;
  }

  @Override
  public String getPropertyXpath() {
    return propertyXpath;
  }

  @Override
  public void setPropertyXpath(String propertyXpath) {
    this.propertyXpath = propertyXpath;
  }

  /**
   * Locate the set of characters in the xpath expression and replace with a new value.
   * @param chars set of characters in the xpath expression
   * @param value replacement value
   */
  @Override
  public abstract void updateXpathExpression(String indexCharset, String indexVal);

  @Override
  public PropertyDefinition clone() {
    try {
      return (PropertyDefinition) super.clone();
    } catch (CloneNotSupportedException e) {
      return null;
    }
  }
}
