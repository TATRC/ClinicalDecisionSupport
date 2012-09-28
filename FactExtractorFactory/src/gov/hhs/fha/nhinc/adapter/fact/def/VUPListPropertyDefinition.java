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
public class VUPListPropertyDefinition extends VUPPropertyDefinition implements Serializable {

  protected String listSizeXpath;

   public VUPListPropertyDefinition() {
      super();
   }

   public VUPListPropertyDefinition(VUPListPropertyDefinition def) {
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

    str.append("VUPListPropertyDefinition[");
    str.append("listSize-xpath=" + listSizeXpath + System.getProperty("line.separator"));
    str.append(super.toString());
    str.append("]");

    return str.toString();
  }
}
