/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.adapter.commondatalayer.xml;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;


/**
 *
 * @author kim
 */
public class HL7NamespacePrefixMapper extends NamespacePrefixMapper {

   public HL7NamespacePrefixMapper() {
      super();
   }

   @Override
   public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
      String prefix = "";

      if (namespaceUri.equals("urn:hl7-org:v3")) {
         prefix = "hl7";
      }
      else if (namespaceUri.equals("urn:gov:hhs:fha:nhinc:common:nhinccommon")) {
         prefix = "ns2";
      }

      return prefix;
   }  
}
