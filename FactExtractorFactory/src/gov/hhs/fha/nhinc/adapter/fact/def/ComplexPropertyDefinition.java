/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.def;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author kim
 */
public class ComplexPropertyDefinition extends SimplePropertyDefinition implements Serializable {

  protected Map<String, SimplePropertyDefinition> objectProperties;

  public ComplexPropertyDefinition() {
    super();
  }

  public ComplexPropertyDefinition(ComplexPropertyDefinition def) {
    super(def);
    this.objectProperties = def.objectProperties;
  }

  public void setObjectProperties(Map<String, SimplePropertyDefinition> objectProperties) {
    this.objectProperties = objectProperties;
  }

  public Map<String, SimplePropertyDefinition> getObjectProperties() {
    return objectProperties;
  }

  @Override
  public String toString() {
    StringBuffer str = new StringBuffer();

    str.append("ComplexListPropertyDefinition[");
    str.append("name=" + propertyName + ",xpath=" + propertyXpath + System.getProperty("line.separator"));
    if (objectProperties != null) {
      str.append(",count-objs=" + objectProperties.size() + System.getProperty("line.separator"));
      Set<Entry<String, SimplePropertyDefinition>> propSet = objectProperties.entrySet();
      for (Entry prop: propSet)
        str.append("\tkey=" + prop.getKey() + ",value=" +  prop.getValue() + System.getProperty("line.separator"));
    }
    str.append(",class=" + propertyClassName);
    str.append("]");

    return str.toString();
  }
}
