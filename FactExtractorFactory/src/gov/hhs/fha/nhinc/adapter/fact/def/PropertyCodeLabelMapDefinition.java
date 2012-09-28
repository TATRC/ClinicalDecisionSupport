/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.fact.def;

import java.io.Serializable;

/**
 * This class provides a way to represent property that needed to be express as a collection where its key is
 * of type <b>CodelabelPair</b> and its values are defined by the <b>CodeLabelListPropertyDefinition</b> class.
 *
 *    Propety collection:
 *       key = CodeLabelPair object
 *       values = List of CodeLabelPair objects
 *
 * @author kim
 */
public class PropertyCodeLabelMapDefinition extends CLPListPropertyDefinition implements Serializable {

   // define the properties of the map key
   protected SimplePropertyDefinition keyDefinition;

   // define the xpath expression representing the size of the map collection
   protected String keySizeXpathExpression;

   public void setKeySizeXpathExpression(String keySizeXpathExpression) {
      this.keySizeXpathExpression = keySizeXpathExpression;
   }

   public SimplePropertyDefinition getKeyDefinition() {
      return keyDefinition;
   }

   public void setKeyDefinition(SimplePropertyDefinition keyDefinition) {
      this.keyDefinition = keyDefinition;
   }

}
