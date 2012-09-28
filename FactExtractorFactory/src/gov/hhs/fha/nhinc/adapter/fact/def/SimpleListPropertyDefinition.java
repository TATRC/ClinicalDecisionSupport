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
public class SimpleListPropertyDefinition extends SimplePropertyDefinition implements Serializable {

  private String listSizeXpath;

  public SimpleListPropertyDefinition() {
    super();
  }

  public SimpleListPropertyDefinition(SimpleListPropertyDefinition def) {
    super(def);
    this.listSizeXpath = def.listSizeXpath;
  }

  public String getListSizeXpath() {
    return listSizeXpath;
  }

  public void setListSizeXpath(String listSizeXpath) {
    this.listSizeXpath = listSizeXpath;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("SimpleListPropertyDefinition[");
    str.append("name=" + propertyName + ",xpath=" + propertyXpath + System.getProperty("line.separator"));
    str.append(",size-xpath=" + listSizeXpath + System.getProperty("line.separator"));
    str.append(",class=" + propertyClassName);
    str.append("]");

    return str.toString();
  }
}
