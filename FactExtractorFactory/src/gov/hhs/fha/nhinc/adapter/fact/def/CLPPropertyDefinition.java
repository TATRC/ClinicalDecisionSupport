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
public class CLPPropertyDefinition 
        extends CSPropertyDefinition
        implements PropertyDefinition, Serializable
{

   protected String codeXpath;
   protected String labelXpath;

   public CLPPropertyDefinition() {
      super();
   }

   public CLPPropertyDefinition(CLPPropertyDefinition def) {
      super(def);
      this.codeXpath = def.codeXpath;
      this.labelXpath = def.labelXpath;
   }

   public CLPPropertyDefinition(String codeXpath, String labelXpath, String codeSystemXpath, String codeSystemNameXpath, String propertyClassName, String propertyName) {
      super(codeSystemXpath, codeSystemNameXpath, propertyClassName, propertyName);
      this.codeXpath = codeXpath;
      this.labelXpath = labelXpath;
   }

   public String getCodeXpath() {
      return codeXpath;
   }

   public void setCodeXpath(String codeXpath) {
      this.codeXpath = codeXpath;
   }

   public String getLabelXpath() {
      return labelXpath;
   }

   public void setLabelXpath(String labelXpath) {
      this.labelXpath = labelXpath;
   }

  /**
   * Locate the set of characters in the xpath expression and replace with a new value.
   * @param chars set of characters in the xpath expression
   * @param value replacement value
   */
   @Override
   public void updateXpathExpression(String indexCharset, String indexVal) {
      super.updateXpathExpression(indexCharset, indexVal);
      if (codeXpath != null) {
         codeXpath = codeXpath.replace(indexCharset, indexVal);
      }
      if (labelXpath != null) {
         labelXpath = labelXpath.replace(indexCharset, indexVal);
      }
   }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("CLPPropertyDefinition[");
    str.append("name=" + propertyName + ",xpath=" + propertyXpath + System.getProperty("line.separator"));
    str.append(",code-xpath=" + codeXpath + System.getProperty("line.separator"));
    str.append(",label-xpath=" + labelXpath + System.getProperty("line.separator"));
    str.append(",codeSystem-xpath=" + codeSystemXpath + System.getProperty("line.separator"));
    str.append(",codeSystemName-xpath=" + codeSystemNameXpath + System.getProperty("line.separator"));
    str.append(",class=" + propertyClassName);
    str.append("]");

    return str.toString();
  }
}
