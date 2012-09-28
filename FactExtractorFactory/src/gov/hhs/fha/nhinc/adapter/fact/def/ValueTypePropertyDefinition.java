/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.def;

import gov.hhs.fha.nhinc.adapter.fact.ValueType;
import java.io.Serializable;

/**
 *
 * @author kim
 */
public class ValueTypePropertyDefinition
        extends CSPropertyDefinition
        implements PropertyDefinition, Serializable
{

  // xpath expression to locate the value property
  protected String valueXpath;
  // indicator whether this property is displayable or not.
  protected String displayIndXpath;

  public ValueTypePropertyDefinition() {
    super();
  }

  public ValueTypePropertyDefinition(ValueTypePropertyDefinition def) {
    super(def);
    this.valueXpath = def.valueXpath;
    this.displayIndXpath = def.displayIndXpath;
  }

  public ValueTypePropertyDefinition(String valueXpath, String displayIndXpath, 
    String codeSystemXpath, String codeSystemNameXpath, String propertyClassName,
    String propertyName) {
    super(codeSystemXpath, codeSystemNameXpath, propertyClassName, propertyName);
    this.valueXpath = valueXpath;
    this.displayIndXpath = displayIndXpath;
    //this.propertyClassName = propertyClassName;
    //this.propertyName = propertyName;
  }

  public String getValueXpath() {
    return valueXpath;
  }

  public void setValueXpath(String valueXpath) {
    this.valueXpath = valueXpath;
  }

  public String getDisplayIndXpath() {
    return displayIndXpath;
  }

  public void setDisplayIndXpath(String displayIndXpath) {
    this.displayIndXpath = displayIndXpath;
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
    if (displayIndXpath != null) {
      displayIndXpath = displayIndXpath.replace(indexCharset, indexVal);
    }
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ValueTypePropertyDefinition[");
    str.append("name=" + propertyName + ",xpath=" + propertyXpath + System.getProperty("line.separator"));
    str.append(",value-xpath=" + valueXpath + System.getProperty("line.separator"));
    str.append(",displayInd-xpath=" + displayIndXpath + System.getProperty("line.separator"));
    str.append(",codeSystem-xpath=" + codeSystemXpath + System.getProperty("line.separator"));
    str.append(",codeSystemName-xpath=" + codeSystemNameXpath + System.getProperty("line.separator"));
    str.append(",class=" + propertyClassName);
    str.append("]");

    return str.toString();
  }
}
