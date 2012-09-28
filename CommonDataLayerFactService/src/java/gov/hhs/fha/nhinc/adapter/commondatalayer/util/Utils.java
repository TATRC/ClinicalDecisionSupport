/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.util;

import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author kim
 */
public class Utils {

   private static Log log = LogFactory.getLog(Utils.class);

   public static String generateDocumentId() {
      //java.rmi.server.UID uid = new java.rmi.server.UID();
      UUID uid = UUID.randomUUID();
      log.debug("generated document id=" + uid.toString());
      return uid.toString();
   }
}
