package gov.hhs.fha.nhinc.kmr.dss;

import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.kmr.FactWrapper;
import gov.hhs.fha.nhinc.kmr.InclusionTable;

import java.util.List;
import java.util.Iterator;

import gov.hhs.fha.nhinc.kmr.DroolsHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Steven Clark
 */
public class FactHandler extends Thread {

   private static Log log = LogFactory.getLog(FactHandler.class);
   private DroolsHelper droolsHelper = null;
   private FactHelper factHelper = null;
   private DSSSession session = null;
   private String patientId = null;
   private String droolsSessionId = null;
   private String droolsSessionEndpoint = null;

   public FactHandler(DSSSession session) {
      if (session != null) {
         this.session = session;
         patientId = session.getKnowledgeSession().getPatientId();
      }
   }

   @Override
   protected void finalize() throws Throwable {
      droolsHelper = null;
      factHelper = null;
   }

   private DroolsHelper getDroolsHelper() {
      if (droolsHelper == null) {
         droolsHelper = new DroolsHelper();
      }
      return droolsHelper;
   }

   private FactHelper getFactHelper() {
      if (factHelper == null) {
         factHelper = new FactHelper();
      }
      return factHelper;
   }

   @Override
   public void run() {
      String droolsCmd = null;
      String droolsResult = null;
      List<FactWrapper> facts = null;

      // Load the patient's cohort object
      log.debug("run: Load patient cohort for " + patientId);
      session.setPatientCohort(getFactHelper().getPatientCohort(patientId));

      // Get the patient's VMR
      session.setVirtualMedicalRecord(getFactHelper().getVirtualMedicalRecord(patientId));
      if (session.getVirtualMedicalRecord() == null) {
         // Create and store it
         log.debug("run: create VMR for " + patientId);
         session.setVirtualMedicalRecord(getFactHelper().createVirtualMedicalRecord(patientId));
         if (session.getVirtualMedicalRecord() != null) {
            log.debug("run: store VMR for " + patientId);
            getFactHelper().storeVirtualMedicalRecord(session.getVirtualMedicalRecord());
         } else {
            log.debug("run: failed to create VMR for " + patientId);
         }
      }

      // Initialize the Drools session
      if (session.getVirtualMedicalRecord() != null) {
         log.debug("run: initialize Drools session " + session.getKnowledgeSession().getSessionId());
         getDroolsHelper().initSession(session.getKnowledgeSession());
         droolsSessionId = session.getKnowledgeSession().getSessionId();
         log.debug("run: droolsSessionId=" + droolsSessionId);
         droolsSessionEndpoint = droolsSessionId.replace("ks", "soap");
         log.debug("run: droolsSessionEndpoint=" + droolsSessionEndpoint);

         // Insert the clinical reference tables next
         if (session.getInclusionTables() != null && session.getInclusionTables().size() > 0) {
            Iterator iter = session.getInclusionTables().iterator();
            while (iter.hasNext()) {
               InclusionTable incTable = (InclusionTable) iter.next();
               droolsCmd = getDroolsHelper().createRules(session.getKnowledgeSession().getSessionId(), incTable);
               log.debug("run: Drools command:\n" + droolsCmd);
               droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
               log.debug("run: Drools result:\n" + droolsResult);
               getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
            }
         } else {
            log.debug("run: No clinical reference tables to insert.");
         }

      }

      // Proceed if we are successful
      if (session.getVirtualMedicalRecord() != null && session.getPatientCohort() != null && droolsSessionId != null) {
         // Send the demographic fact before the other historic facts, since its rule sets up some critical global variables
         droolsCmd = getDroolsHelper().createRules(droolsSessionId,
                 session.getVirtualMedicalRecord().getDemographics());
         log.debug("run: Drools command:\n" + droolsCmd);
         droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
         log.debug("run: Drools result=[" + droolsResult + "]");

         // Keep track of the fact handles that are inserted in the drools session
         // This list will be needed for a brute-force reinitialization of the session
         getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());

         // Insert the Patient Cohort Object next
         droolsCmd = getDroolsHelper().createRules(session.getKnowledgeSession().getSessionId(), session.getPatientCohort());
         log.debug("run: Drools command:\n" + droolsCmd);
         droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
         log.debug("run: Drools result=[" + droolsResult + "]");
         getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());

         // Create a batch of rules for each fact type in the VMR
         // Allergies
         for (FactType fact : session.getVirtualMedicalRecord().getAllergies()) {
            droolsCmd = getDroolsHelper().createRules(droolsSessionId, fact);
            log.debug("run: Batch rules for AllergyFactType - command=[" + droolsCmd + "]");
            droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("run: Drools result=[" + droolsResult + "]");
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
         }

         // Medications
         for (FactType fact : session.getVirtualMedicalRecord().getMedications()) {
            droolsCmd = getDroolsHelper().createRules(droolsSessionId, fact);
            log.debug("run: Batch rules for MedicationFactType - command=[" + droolsCmd + "]");
            droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("run: Drools result=[" + droolsResult + "]");
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
         }

         // Problems
         for (FactType fact : session.getVirtualMedicalRecord().getProblems()) {
            droolsCmd = getDroolsHelper().createRules(droolsSessionId, fact);
            log.debug("run: Batch rules for ProblemFactType - command=[" + droolsCmd + "]");
            droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("run: Drools result=[" + droolsResult + "]");
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
         }

         // Lab Results
         for (FactType fact : session.getVirtualMedicalRecord().getResults()) {
            droolsCmd = getDroolsHelper().createRules(droolsSessionId, fact);
            log.debug("run: Batch rules for ResultFactType - command=[" + droolsCmd + "]");
            droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("run: Drools result=[" + droolsResult + "]");
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
         }

         // Vitals
         for (FactType fact : session.getVirtualMedicalRecord().getVitals()) {
            droolsCmd = getDroolsHelper().createRules(droolsSessionId, fact);
            log.debug("run: Batch rules type [VitalFactType] command=[" + droolsCmd + "]");
            droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("run: Drools result=[" + droolsResult + "]");
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
         }

         // Immunizations
         for (FactType fact : session.getVirtualMedicalRecord().getImmunizations()) {
            droolsCmd = getDroolsHelper().createRules(droolsSessionId, fact);
            log.debug("run: Batch rules type[ImmunizationFactType] command=[" + droolsCmd + "]");
            droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("run: Drools result=[" + droolsResult + "]");
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
         }

         // Procedures
         for (FactType fact : session.getVirtualMedicalRecord().getProcedures()) {
            droolsCmd = getDroolsHelper().createRules(droolsSessionId, fact);
            log.debug("run: Batch rules type[ProcedureFactType] command=[" + droolsCmd + "]");
            droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("run: Drools result=[" + droolsResult + "]");
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
         }

         // Appointments
         for (FactType fact : session.getVirtualMedicalRecord().getAppointments()) {
            droolsCmd = getDroolsHelper().createRules(droolsSessionId, fact);
            log.debug("run: Batch rules type[AppointmentFactType] command=[" + droolsCmd + "]");
            droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("run: Drools result=[" + droolsResult + "]");
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
         }

         // Admissions
         for (FactType fact : session.getVirtualMedicalRecord().getAdmissions()) {
            droolsCmd = getDroolsHelper().createRules(droolsSessionId, fact);
            log.debug("run: Batch rules type[AdmissionFactType] command=[" + droolsCmd + "]");
            droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("run: Drools result=[" + droolsResult + "]");
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
         }

         // Diagnoses
         for (FactType fact : session.getVirtualMedicalRecord().getDiagnoses()) {
            droolsCmd = getDroolsHelper().createRules(droolsSessionId, fact);
            log.debug("run: Batch rules type[DiagnosisFactType] command=[" + droolsCmd + "]");
            droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("run: Drools result=[" + droolsResult + "]");
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());
         }

         // Continue checking
         while (getFactHelper().isPatientBeingProcessed(patientId)) {
            // Check fact queue for new event to process
            log.debug("run: check fact queue for patient " + patientId);
            facts = getFactHelper().readFactQueue(patientId);
            processFacts(facts);
            waitloop();
         }

      }
      
      if (droolsSessionId != null) {
         log.debug("run: Closing session for patient " + patientId);
         getFactHelper().deleteVirtualMedicalRecord(patientId);
         //getFactHelper().deletePatientCohort(patientId);
         //getFactHelper().deleteAllPatientEvents(patientId);
         getDroolsHelper().closeSession(droolsSessionId, droolsSessionEndpoint, session.getFactHandles());
      } else {
         // Either we failed to create a drools session, or we failed to get a patient VMR
         log.debug("run: Removing COHORT, VMR and FACT objects for patient: " + patientId);
         getFactHelper().deleteVirtualMedicalRecord(patientId);
         getFactHelper().deleteAllPatientEvents(patientId);
         getFactHelper().deletePatientCohort(patientId);
      }
   }

   private void processFacts(List<FactWrapper> facts) {
      if (facts != null) {
         log.debug(facts.size() + " facts read from queue.");
         Iterator<FactWrapper> iter = facts.iterator();
         while (iter.hasNext()) {
            FactWrapper wrapper = iter.next();
            if (wrapper.getFact() instanceof gov.hhs.fha.nhinc.adapter.fact.ResultFactType) {
               // Check for valid specimenDate
               if (((gov.hhs.fha.nhinc.adapter.fact.ResultFactType) wrapper.getFact()).getSpecimenDate() == null) {
                  // Check for valid resultDate
                  if (((gov.hhs.fha.nhinc.adapter.fact.ResultFactType) wrapper.getFact()).getResultDate() != null) {
                     log.debug("Invalid specimenDate... use resultDate...");
                     ((gov.hhs.fha.nhinc.adapter.fact.ResultFactType) wrapper.getFact()).setSpecimenDate(((gov.hhs.fha.nhinc.adapter.fact.ResultFactType) wrapper.getFact()).getResultDate());
                  } else {
                     log.debug("Invalid specimenDate... use today");
                     ((gov.hhs.fha.nhinc.adapter.fact.ResultFactType) wrapper.getFact()).setSpecimenDate(new java.util.Date());
                  }
               } else {
                  log.debug("specimenDate = " + ((gov.hhs.fha.nhinc.adapter.fact.ResultFactType) wrapper.getFact()).getSpecimenDate());
               }
            }
            
            String droolsCmd = getDroolsHelper().createRules(droolsSessionId, wrapper.getFact());
            log.debug("processFacts: drools command:\n" + droolsCmd);

            String droolsResult = getDroolsHelper().executeRules(droolsCmd, droolsSessionEndpoint);
            log.debug("processFacts: result=" + droolsResult);

            // Keep track of the fact handles that are inserted in the drools session
            // This list will be needed for a brute-force reinitialization of the session
            getDroolsHelper().updateHandleList(droolsResult, session.getFactHandles());

            getFactHelper().deleteFactEvent(wrapper);
         }
      }
   }

   private void waitloop() {
      try {
         //sleep for 10 seconds
         log.debug("Waiting for patient events for patient: " + patientId);
         sleep(10000);
      } catch (Exception ex) {
         log.error(ex);
      }
   }
}
