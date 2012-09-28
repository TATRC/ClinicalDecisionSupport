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
public class ValueTypeListPropertyDefinition extends ValueTypePropertyDefinition implements Serializable {

   protected String listSizeXpath;
   protected String indexRef;

  public ValueTypeListPropertyDefinition() {
    super();
  }

  public ValueTypeListPropertyDefinition(ValueTypeListPropertyDefinition def) {
    super(def);
    this.listSizeXpath = def.listSizeXpath;
  }

  public String getListSizeXpath() {
    return listSizeXpath;
  }

  public void setListSizeXpath(String listSizeXpath) {
    this.listSizeXpath = listSizeXpath;
  }

  public String getIndexRef() {
    return indexRef;
  }

  public void setIndexRef(String indexRef) {
    this.indexRef = indexRef;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ValueTypeListPropertyDefinition[");
    str.append("listSize-xpath=" + listSizeXpath + System.getProperty("line.separator"));
    str.append(super.toString());
    str.append("]");

    return str.toString();
  }
}
