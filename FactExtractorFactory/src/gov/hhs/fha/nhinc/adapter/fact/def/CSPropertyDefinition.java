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
public class CSPropertyDefinition extends SimplePropertyDefinition implements PropertyDefinition, Serializable {

   protected String codeSystemXpath;
   protected String codeSystemNameXpath;

   public CSPropertyDefinition() {
      super();
   }

   public CSPropertyDefinition(CSPropertyDefinition def) {
      super(def);
      this.codeSystemXpath = def.codeSystemXpath;
      this.codeSystemNameXpath = def.codeSystemNameXpath;
   }

   public CSPropertyDefinition(String codeSystemXpath, String codeSystemNameXpath, String propertyClassName, String propertyName) {
     super(propertyName, propertyClassName);
      this.codeSystemXpath = codeSystemXpath;
      this.codeSystemNameXpath = codeSystemNameXpath;
   }

  public String getCodeSystemNameXpath() {
    return codeSystemNameXpath;
  }

  public void setCodeSystemNameXpath(String codeSystemNameXpath) {
    this.codeSystemNameXpath = codeSystemNameXpath;
  }

  public String getCodeSystemXpath() {
    return codeSystemXpath;
  }

  public void setCodeSystemXpath(String codeSystemXpath) {
    this.codeSystemXpath = codeSystemXpath;
  }

  /**
   * Locate the set of characters in the xpath expression and replace with a new value.
   * @param chars set of characters in the xpath expression
   * @param value replacement value
   */
   @Override
   public void updateXpathExpression(String indexCharset, String indexVal) {
      super.updateXpathExpression(indexCharset, indexVal);
      if (codeSystemXpath != null) {
         codeSystemXpath = codeSystemXpath.replace(indexCharset, indexVal);
      }
      if (codeSystemNameXpath != null) {
         codeSystemNameXpath = codeSystemNameXpath.replace(indexCharset, indexVal);
      }
   }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("CSPropertyDefinition[");
    str.append("name=" + propertyName + ",xpath=" + propertyXpath + System.getProperty("line.separator"));
    str.append(",codeSystem-xpath=" + codeSystemXpath + System.getProperty("line.separator"));
    str.append(",codeSystemName-xpath=" + codeSystemNameXpath + System.getProperty("line.separator"));
    str.append(",class=" + propertyClassName);
    str.append("]");

    return str.toString();
  }
}
