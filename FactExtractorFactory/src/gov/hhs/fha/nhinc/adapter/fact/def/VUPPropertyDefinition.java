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
public class VUPPropertyDefinition extends SimplePropertyDefinition implements PropertyDefinition, Serializable {

  protected String valueXpath;
  protected String unitXpath;

  public VUPPropertyDefinition() {
    super();
  }

  public VUPPropertyDefinition(VUPPropertyDefinition def) {
    super(def);
    this.valueXpath = def.valueXpath;
    this.unitXpath = def.unitXpath;
  }

  public VUPPropertyDefinition(String valueXpath, String unitXpath, String propertyClassName, String propertyName) {
    this.valueXpath = valueXpath;
    this.unitXpath = unitXpath;
    this.propertyClassName = propertyClassName;
    this.propertyName = propertyName;
  }

  public String getValueXpath() {
    return valueXpath;
  }

  public void setValueXpath(String valueXpath) {
    this.valueXpath = valueXpath;
  }

  public String getUnitXpath() {
    return unitXpath;
  }

  public void setUnitXpath(String unitXpath) {
    this.unitXpath = unitXpath;
  }

  /**
   * Locate the set of characters in the xpath expression and replace with a new value.
   * @param chars set of characters in the xpath expression
   * @param value replacement value
   */
  @Override
  public void updateXpathExpression(String indexCharset, String indexVal) {
    super.updateXpathExpression(indexCharset, indexVal);
    if (valueXpath != null) {
      valueXpath = valueXpath.replace(indexCharset, indexVal);
    }
    if (unitXpath != null) {
      unitXpath = unitXpath.replace(indexCharset, indexVal);
    }
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("VUPPropertyDefinition[");
    str.append("name=" + propertyName + ",xpath=" + propertyXpath + System.getProperty("line.separator"));
    str.append(",value-xpath=" + valueXpath + System.getProperty("line.separator"));
    str.append(",unit-xpath=" + unitXpath + System.getProperty("line.separator"));
    str.append(",class=" + propertyClassName);
    str.append("]");

    return str.toString();
  }
}
