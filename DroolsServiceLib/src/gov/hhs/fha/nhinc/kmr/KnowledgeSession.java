package gov.hhs.fha.nhinc.kmr;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Steven Clark
 */
public class KnowledgeSession {

   private String patientId;
   private String sessionId;
   private List<String> kmids;

   /**
    * @return the patientId
    */
   public String getPatientId() {
      return patientId;
   }

   /**
    * @param patientId the patientId to set
    */
   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   /**
    * @return the sessionId
    */
   public String getSessionId() {
      return sessionId;
   }

   /**
    * @param patientId the sessionId to set
    */
   public void setSessionId(String sessionId) {
      this.sessionId = sessionId;
   }

   /**
    * @return the knowledge module ids
    */
   public List<String> getKMIDs() {
      if (kmids == null) {
         kmids = new ArrayList<String>();
      }
      return kmids;
   }
   
   @Override
   public String toString() {          
      return "KnowledgeSession[patientId=" + patientId + ",sessionId=" + sessionId + ",kmids=" + getKMIDs().size() + "]";
   }
}
