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
public class CLPListPropertyDefinition extends CLPPropertyDefinition implements Serializable {

   protected String listSizeXpath;

  public CLPListPropertyDefinition() {
    super();
  }

  public CLPListPropertyDefinition(CLPListPropertyDefinition def) {
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

    str.append("CLPListPropertyDefinition[");
    str.append("listSize-xpath=" + listSizeXpath + System.getProperty("line.separator"));
    str.append(super.toString());
    str.append("]");

    return str.toString();
  }
}
