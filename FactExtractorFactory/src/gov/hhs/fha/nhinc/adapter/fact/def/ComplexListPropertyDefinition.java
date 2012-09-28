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
public class ComplexListPropertyDefinition extends ComplexPropertyDefinition implements Serializable {

  protected String listSizeXpath;
  protected String indexRef;

  public ComplexListPropertyDefinition() {
    super();
  }

  public ComplexListPropertyDefinition(ComplexListPropertyDefinition def) {
    super(def);
    this.listSizeXpath = def.listSizeXpath;
  }

  public void setListSizeXpath(String listSizeXpath) {
    this.listSizeXpath = listSizeXpath;
  }

  public String getListSizeXpath() {
    return listSizeXpath;
  }

  public String getIndexRef() {
    return indexRef;
  }

  public void setIndexRef(String indexRef) {
    this.indexRef = indexRef;
  }
}
