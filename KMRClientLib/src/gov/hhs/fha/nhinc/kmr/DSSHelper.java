package gov.hhs.fha.nhinc.kmr;

import gov.hhs.fha.nhinc.kmr.dss.KnowledgeSession;
import gov.hhs.fha.nhinc.kmr.dss.SessionService;
import gov.hhs.fha.nhinc.kmr.dss.Session;

import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessException;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import javax.xml.ws.BindingProvider;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Steven Clark
 */
public class DSSHelper {

   private static Log log = LogFactory.getLog(DSSHelper.class);
   private String serviceURL;
   private static String DEF_DSS_SERVICE_URL;

   static {
      try {
         DEF_DSS_SERVICE_URL = PropertyAccessor.getProperty("dss", "DSS_SESSION_SERVICE");
         System.out.println("DEF_DSS_SERVICE_URL = " + DEF_DSS_SERVICE_URL);
      } catch (PropertyAccessException pae) {
         log.error("Error initializing default endpoint properties", pae);
      }
   }

   /**
    * default-no argument constructor required for Drools
    */
   public DSSHelper() {
      // use defaults
      //this.serviceURL = "http://localhost:8080/DecisionSupportService/SessionService";
      this.serviceURL = DEF_DSS_SERVICE_URL;

      System.out.println("Instantiated DSSHelper with no arguments");
   }

   /**
    * Constructor with DSS session service endpoint
    *
    * @param serviceURL - String containing the DSS session service endpoint
    */
   public DSSHelper(String serviceURL) {
      if (serviceURL != null && !serviceURL.isEmpty()) {
         this.serviceURL = serviceURL;
         System.out.println("Instantiated DSSHelper with serviceURL: " + serviceURL);
      } else {
         // use defaults
         //this.serviceURL = "http://localhost:8080/DecisionSupportService/SessionService";
         this.serviceURL = DEF_DSS_SERVICE_URL;
         System.out.println("**** Instantiated DSSHelper with default serviceURL: " + this.serviceURL);
      }
   }

   /**
    * Set the task manager service URL
    *
    * @param serviceURL - String containing the task manager service endpoint
    */
   public void setServiceURL(String serviceURL) {
      if (serviceURL != null && !serviceURL.isEmpty()) {
         this.serviceURL = serviceURL;
      }
   }

   /**
    * Send initSession request message to the DSS service
    *
    * @param session - KnowledgeSession object containing session id and list of rules
    */
   public void initSession(KnowledgeSession session) {
      try {
         if (session != null) {
            System.out.println("DSSHelper::  DSS init session with " + session.getPatientId());
            SessionService service = new SessionService();
            Session port = service.getSessionPort();
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
            boolean res = port.initKnowledgeSession(session);
            System.out.println("DSSHelper:: DSS init session response = " + res);
         } else {
            System.out.println("DSSHelper:: Invalid parameter: KnowledgeSession cannot be null");
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}
