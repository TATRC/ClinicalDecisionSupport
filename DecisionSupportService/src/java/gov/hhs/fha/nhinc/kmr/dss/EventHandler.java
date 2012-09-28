package gov.hhs.fha.nhinc.kmr.dss;

import gov.hhs.fha.nhinc.adapter.fact.AdmissionFactType;
import java.util.List;
import java.util.Iterator;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.AllergyFactType;
import gov.hhs.fha.nhinc.adapter.fact.AppointmentFactType;
import gov.hhs.fha.nhinc.adapter.fact.DiagnosisFactType;
import gov.hhs.fha.nhinc.adapter.fact.EncounterFactType;
import gov.hhs.fha.nhinc.adapter.fact.MedicationFactType;
import gov.hhs.fha.nhinc.adapter.fact.PersonFactType;
import gov.hhs.fha.nhinc.adapter.fact.ProblemFactType;
import gov.hhs.fha.nhinc.adapter.fact.ProcedureFactType;
import gov.hhs.fha.nhinc.adapter.fact.ResultFactType;

import gov.hhs.fha.nhinc.kmr.DroolsHelper;
import gov.hhs.fha.nhinc.kmr.PatientCohortHelper;
import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Steven Clark
 */
@WebService()
public class EventHandler {

   private Log log = LogFactory.getLog(EventHandler.class);
   private FactHelper factHelper = null;
   private DroolsHelper droolsHelper = null;
   private PatientCohortHelper cohortHelper = null;

   @Override
   protected void finalize() throws Throwable {
      droolsHelper = null;
      factHelper = null;
      cohortHelper = null;
   }

   private FactHelper getFactHelper() {
      if (factHelper == null) {
         factHelper = new FactHelper();
      }
      return factHelper;
   }

   private DroolsHelper getDroolsHelper() {
      if (droolsHelper == null) {
         droolsHelper = new DroolsHelper();
      }
      return droolsHelper;
   }

   private PatientCohortHelper getPatientCohortHelper() {
      if (cohortHelper == null) {
         cohortHelper = new PatientCohortHelper();
      }
      return cohortHelper;
   }

   /**
    * Web service operation
    */
   @WebMethod(operationName = "storeDemographicsFactEvent")
   public Boolean storeDemographicsFactEvent(@WebParam(name = "fact") PersonFactType fact) {
      initSession(fact);
      return getFactHelper().storeFactEvent(fact);
   }

   /**
    * Web service operation
    */
   @WebMethod(operationName = "storeAllergyFactEvent")
   public Boolean storeAllergyFactEvent(@WebParam(name = "fact") List<AllergyFactType> facts) {
      Boolean res = new Boolean(false);
      if (facts != null && facts.size() > 0) {
         initSession(facts.get(0));
         Iterator iter = facts.iterator();
         while (iter.hasNext()) {
            getFactHelper().storeFactEvent((AllergyFactType) iter.next());
         }
         res = new Boolean(true);
      }
      return res;
   }

   /**
    * Web service operation
    */
   @WebMethod(operationName = "storeMedicationFactEvent")
   public Boolean storeMedicationFactEvent(@WebParam(name = "fact") List<MedicationFactType> facts) {
      Boolean res = new Boolean(false);
      if (facts != null && facts.size() > 0) {
         initSession(facts.get(0));
         Iterator iter = facts.iterator();
         while (iter.hasNext()) {
            getFactHelper().storeFactEvent((MedicationFactType) iter.next());
         }
         res = new Boolean(true);
      }
      return res;
   }

   /**
    * Web service operation
    */
   @WebMethod(operationName = "storeProblemFactEvent")
   public Boolean storeProblemFactEvent(@WebParam(name = "fact") List<ProblemFactType> facts) {
      Boolean res = new Boolean(false);
      if (facts != null && facts.size() > 0) {
         initSession(facts.get(0));
         Iterator iter = facts.iterator();
         while (iter.hasNext()) {
            getFactHelper().storeFactEvent((ProblemFactType) iter.next());
         }
         res = new Boolean(true);
      }
      return res;
   }

   /**
    * Web service operation
    */
   @WebMethod(operationName = "storeResultFactEvent")
   public Boolean storeResultFactEvent(@WebParam(name = "fact") List<ResultFactType> facts) {
      Boolean res = new Boolean(false);
      if (facts != null && facts.size() > 0) {
         if (facts.get(0).getPrimaryKey().isEmpty()) {
            facts.get(0).setPrimaryKey(facts.get(0).getPatientId().getValue());
         }
         log.debug("storeResultFactEvent: obtain session for ... " + facts.get(0).getPrimaryKey());
         initSession(facts.get(0));
         Iterator iter = facts.iterator();
         while (iter.hasNext()) {
            getFactHelper().storeFactEvent((ResultFactType) iter.next());
         }
         res = new Boolean(true);
      }
      return res;
   }

   /**
    * Web service operation
    */
   @WebMethod(operationName = "storeAppointmentFactEvent")
   public Boolean storeAppointmentFactEvent(@WebParam(name = "fact") List<AppointmentFactType> facts) {
      Boolean res = new Boolean(false);
      if (facts != null && facts.size() > 0) {
         if (facts.get(0).getPrimaryKey().isEmpty()) {
            facts.get(0).setPrimaryKey(facts.get(0).getPatientId().getValue());
         }
         log.debug("storeAppointmentFactEvent: obtain session for ... " + facts.get(0).getPrimaryKey());
         initSession(facts.get(0));
         Iterator iter = facts.iterator();
         while (iter.hasNext()) {
            getFactHelper().storeFactEvent((AppointmentFactType) iter.next());
         }
         res = new Boolean(true);
      }
      return res;
   }

   /**
    * Web service operation
    */
   @WebMethod(operationName = "storeDiagnosisFactEvent")
   public Boolean storeDiagnosisFactEvent(@WebParam(name = "fact") List<DiagnosisFactType> facts) {
      Boolean res = new Boolean(false);
      if (facts != null && facts.size() > 0) {
         if (facts.get(0).getPrimaryKey().isEmpty()) {
            facts.get(0).setPrimaryKey(facts.get(0).getPatientId().getValue());
         }
         log.debug("storeDiagnosisFactEvent: obtain session for ... " + facts.get(0).getPrimaryKey());
         initSession(facts.get(0));
         Iterator iter = facts.iterator();
         while (iter.hasNext()) {
            getFactHelper().storeFactEvent((DiagnosisFactType) iter.next());
         }
         res = new Boolean(true);
      }
      return res;
   }

   /**
    * Web service operation
    */
   @WebMethod(operationName = "storeProcedureFactEvent")
   public Boolean storeProcedureFactEvent(@WebParam(name = "fact") List<ProcedureFactType> facts) {
      Boolean res = new Boolean(false);
      if (facts != null && facts.size() > 0) {
         if (facts.get(0).getPrimaryKey().isEmpty()) {
            facts.get(0).setPrimaryKey(facts.get(0).getPatientId().getValue());
         }
         log.debug("storeProcedureFactEvent: obtain session for ... " + facts.get(0).getPrimaryKey());
         initSession(facts.get(0));
         Iterator iter = facts.iterator();
         while (iter.hasNext()) {
            getFactHelper().storeFactEvent((ProcedureFactType) iter.next());
         }
         res = new Boolean(true);
      }
      return res;
   }

   /**
    * Web service operation
    */
   @WebMethod(operationName = "storeAdmissionFactEvent")
   public Boolean storeAdmissionFactEvent(@WebParam(name = "fact") List<AdmissionFactType> facts) {
      Boolean res = new Boolean(false);
      if (facts != null && facts.size() > 0) {
         if (facts.get(0).getPrimaryKey().isEmpty()) {
            facts.get(0).setPrimaryKey(facts.get(0).getPatientId().getValue());
         }
         log.debug("storeAdmissionFactEvent: obtain session for ... " + facts.get(0).getPrimaryKey());
         initSession(facts.get(0));
         Iterator iter = facts.iterator();
         while (iter.hasNext()) {
            getFactHelper().storeFactEvent((AdmissionFactType) iter.next());
         }
         res = new Boolean(true);
      }
      return res;
   }

   /**
    * Web service operation
    */
   @WebMethod(operationName = "storeEncounterFactEvent")
   public Boolean storeEncounterFactEvent(@WebParam(name = "fact") List<EncounterFactType> facts) {
      Boolean res = new Boolean(false);
      if (facts != null && facts.size() > 0) {
         if (facts.get(0).getPrimaryKey().isEmpty()) {
            facts.get(0).setPrimaryKey(facts.get(0).getPatientId().getValue());
         }
         log.debug("storeEncounterFactEvent: obtain session for ... " + facts.get(0).getPrimaryKey());
         initSession(facts.get(0));
         Iterator iter = facts.iterator();
         while (iter.hasNext()) {
            getFactHelper().storeFactEvent((EncounterFactType) iter.next());
         }
         res = new Boolean(true);
      }
      return res;
   }

   private synchronized void initSession(FactType fact) {
      if ((fact != null) && (fact.getPrimaryKey() != null)) {
         if (!getFactHelper().isPatientBeingProcessed(fact.getPrimaryKey())) {
            log.debug("initSession: init new session for ... " + fact.getPrimaryKey());
            // session has not yet been created for this patient
            PatientCohort cohort = getPatientCohortHelper().sendQuery(fact.getPrimaryKey());
            log.debug("initSession: obatined PatientCohort for " + fact.getPrimaryKey());
            if (cohort != null) {
               getFactHelper().storePatientCohort(cohort);
               // Call the "rules about rules" session
               getDroolsHelper().getSessionRules(cohort);
            } else {
               log.error("initSession: Failed to obtain patient cohort information.");
            }
         } else {
            log.debug("initSession: Patient " + fact.getPrimaryKey() + " is currently being processed... just queue the new event.");
         }
      } else {
         log.error("initSession: Invalid fact object");
      }
   }
}
