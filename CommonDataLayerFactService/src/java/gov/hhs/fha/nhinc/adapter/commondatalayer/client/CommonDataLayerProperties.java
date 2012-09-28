/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.client;

import java.util.Properties;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 *
 * @author kim
 */
public class CommonDataLayerProperties extends PropertyPlaceholderConfigurer {

   Properties calProperties;

   public String getProperty(String name) {
      if (name == null || name.length() < 1)
         return null;
      
      return calProperties.getProperty(name);
   }

   public void setCalProperties(Properties calProperties) {
      this.calProperties = calProperties;
   }
}
