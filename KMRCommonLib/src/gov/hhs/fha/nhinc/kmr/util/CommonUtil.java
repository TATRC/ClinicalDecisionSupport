/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.kmr.util;

import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author kim
 */
public class CommonUtil {

   private static Log log = LogFactory.getLog(CommonUtil.class);

   public static String generateId() {
      //java.rmi.server.UID uid = new java.rmi.server.UID();
      UUID uid = UUID.randomUUID();
      log.debug("generated id=" + uid.toString());
      return uid.toString();
   }
}
