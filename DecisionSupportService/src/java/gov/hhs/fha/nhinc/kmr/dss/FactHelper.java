package gov.hhs.fha.nhinc.kmr.dss;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.ObjectSet;
import com.db4o.config.Configuration;
import com.db4o.query.Query;
import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.AddressFactType;
import gov.hhs.fha.nhinc.adapter.fact.AllergyFactType;
import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;
import gov.hhs.fha.nhinc.adapter.fact.CodeSystemPair;
import gov.hhs.fha.nhinc.adapter.fact.FulfillmentFactType;
import gov.hhs.fha.nhinc.adapter.fact.MedicationFactType;
import gov.hhs.fha.nhinc.adapter.fact.NameFactType;
import gov.hhs.fha.nhinc.adapter.fact.OrderFactType;
import gov.hhs.fha.nhinc.adapter.fact.PersonFactType;
import gov.hhs.fha.nhinc.adapter.fact.PreConditionFactType;
import gov.hhs.fha.nhinc.adapter.fact.ProblemFactType;
import gov.hhs.fha.nhinc.adapter.fact.ReactionFactType;
import gov.hhs.fha.nhinc.adapter.fact.ResultFactType;
import gov.hhs.fha.nhinc.adapter.fact.SeverityFactType;
import gov.hhs.fha.nhinc.adapter.fact.SupportSourceFactType;
import gov.hhs.fha.nhinc.adapter.fact.TelecomFactType;
import gov.hhs.fha.nhinc.adapter.fact.ValueType;
import gov.hhs.fha.nhinc.adapter.fact.ValueUnitPair;

import gov.hhs.fha.nhinc.kmr.FactWrapper;
import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort;
import gov.hhs.fha.nhinc.kmr.properties.DateProperty;
import gov.hhs.fha.nhinc.kmr.properties.DatePropertyAccessor;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import gov.hhs.fha.nhinc.kmr.VirtualMedicalRecord;

import gov.hhs.fha.nhinc.util.hash.SHA1HashCode;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import gov.hhs.fha.nhinc.adapter.fact.AdmissionFactType;
import gov.hhs.fha.nhinc.adapter.fact.AppointmentFactType;
import gov.hhs.fha.nhinc.adapter.fact.DiagnosisFactType;
import gov.hhs.fha.nhinc.adapter.fact.ImmunizationFactType;
import gov.hhs.fha.nhinc.adapter.fact.ProcedureFactType;
import gov.hhs.fha.nhinc.adapter.fact.VitalFactType;
import gov.hhs.fha.nhinc.adapter.factservice.client.AdapterFactServiceClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Steven Clark (from fact handler by Duane DeCouteau)
 */
public class FactHelper {

   private static Log log = LogFactory.getLog(FactHelper.class);
   private static ObjectServer factQueue = null;
   private static ObjectServer vmrStore = null;
   private AdapterFactServiceClient factClient = null;
   private String host = null;
   private String user = null;
   private String pass = null;
   private int factPort;
   private int vmrPort;

   public FactHelper() {
      try {
         PropertyAccessor.forceRefresh("dss");
         factClient = new AdapterFactServiceClient(PropertyAccessor.getProperty("dss", "ADAPTER_FACT_SERVICE"));
         host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
         user = PropertyAccessor.getProperty("dss", "KMR_USER");
         pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
         factPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_FACT_PORT"));
         vmrPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_VMR_PORT"));
      } catch (Exception e) {
      }
   }

   @Override
   protected void finalize() throws Throwable {
      closeFactQueue();
      closeVMRStore();
   }

   private boolean openFactQueue() {
      if (factQueue == null) {
         log.debug("openFactQueue: opening fact queue");
         String dbFile = null;
         try {
            dbFile = PropertyAccessor.getProperty("dss", "FILE_PATH") +
                    PropertyAccessor.getProperty("dss", "DB4OBJECTS_FACT_QUEUE");
            log.debug("openFactQueue:  open DB4OBJECTS_FACT_QUEUE=" + dbFile);
            File dbtest = new File(dbFile);
            if (dbtest.exists()) {
               try {
                  Configuration conf = Db4o.newConfiguration();
                  conf.lockDatabaseFile(false);

//                  int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_FACT_PORT"));
                  factQueue = Db4o.openServer(dbFile, factPort);
                  log.debug("openFactQueue:  open factQueue port = " + factPort);
                  factQueue.grantAccess(PropertyAccessor.getProperty("dss", "KMR_USER"),
                          PropertyAccessor.getProperty("dss", "KMR_PASS"));
                  return true;
               } catch (Exception ex) {
                  log.error("openFactQueue: Unable to open fact queue.  Possible cause: DB4object is already open", ex);
                  return false;
               }
            }
         } catch (Exception fx) {
            log.error("Error finding DB4OBJECTS_FACT_QUEUE=" + dbFile, fx);
            return false;
         }

//         if (dbexists) {
//            try {
//               Configuration conf = Db4o.newConfiguration();
//               conf.lockDatabaseFile(false);
//
//               int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_FACT_PORT"));
//               factQueue = Db4o.openServer(dbFile, iPort);
//               log.info("openFactQueue:  open factQueue port = " + iPort);
//               factQueue.grantAccess(PropertyAccessor.getProperty("dss", "KMR_USER"),
//                       PropertyAccessor.getProperty("dss", "KMR_PASS"));
//            } catch (Exception ex) {
//               log.error("Unable to open fact queue.  Possible cause: DB4object is already open", ex);
//            }
//         }
      }

      return false;
   }

   private void closeFactQueue() {
      if (factQueue != null) {
         log.debug("Closing fact queue");
         try {
            factQueue.close();
            factQueue = null;
         } catch (Exception ex) {
            log.error("Unable to close fact queue.", ex);
         }
      }
   }

   private void openVMRStore() {
      if (vmrStore == null) {
         log.debug("openVMRStore: Opening vmr store");
         boolean dbexists = false;
         String dbFile = null;
         try {
            dbFile = PropertyAccessor.getProperty("dss", "FILE_PATH") +
                    PropertyAccessor.getProperty("dss", "DB4OBJECTS_VMR_STORE");
            log.debug("openVMRStore: vmr store file " + dbFile);
            File dbtest = new File(dbFile);
            if (dbtest.exists()) {
               dbexists = true;
            }
         } catch (Exception fx) {
            log.error("Error finding vmr store", fx);
         }
         if (dbexists) {
            try {
               Configuration conf = Db4o.newConfiguration();
               conf.lockDatabaseFile(false);

//               int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_VMR_PORT"));
               vmrStore = Db4o.openServer(dbFile, vmrPort);
               vmrStore.grantAccess(PropertyAccessor.getProperty("dss", "KMR_USER"),
                       PropertyAccessor.getProperty("dss", "KMR_PASS"));
            } catch (Exception ex) {
               log.error("Unable to open vmr store.  Possible cause: DB4object is already open", ex);
            }
         }
      }
   }

   private void closeVMRStore() {
      if (vmrStore != null) {
         log.debug("Closing vmr store");
         try {
            vmrStore.close();
            vmrStore = null;
         } catch (Exception ex) {
            log.error("Unable to close VMR store.", ex);
         }
      }
   }

   public Boolean isPatientBeingProcessed(String patientId) {
      ObjectContainer client = null;
      Boolean res = new Boolean(false);
      try {
         openVMRStore();
         client = vmrStore.openClient();
         Query query = client.query();
         query.constrain(PatientCohort.class);
         query.descend("patientId").constrain(patientId);
         ObjectSet result = query.execute();
         if (result != null && result.size() > 0) {
            res = new Boolean(true);
         }
      } catch (Exception ex) {
         log.error("Error reading VMR store: ", ex);
      } finally {
         client.close();
      }
      return res;
   }

   public Boolean deleteFactEvent(FactWrapper fact) {
      ObjectContainer client = null;
      Query query = null;
      Boolean dbIsDirty = new Boolean(false);
      Boolean res = new Boolean(false);
      int iCount = 0;
      try {
         openFactQueue();
//         String host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
//         String user = PropertyAccessor.getProperty("dss", "KMR_USER");
//         String pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
//         int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_FACT_PORT"));
         client = Db4o.openClient(host, factPort, user, pass);
         query = client.query();
         query.constrain(FactWrapper.class);
         query.descend("uuid").constrain(fact.getUUID());
         ObjectSet result = query.execute();
         while (result.hasNext()) {
            FactWrapper obj = (FactWrapper) result.next();
            if (obj.getUUID().equalsIgnoreCase(fact.getUUID())) {
               client.delete(obj);
               dbIsDirty = true;
               iCount++;
            }
         }

         if (dbIsDirty) {
            client.commit();
         }
         log.debug(iCount + " facts removed from queue.");
         res = new Boolean(true);
      } catch (Exception ex) {
         log.error("Error deleting fact event.", ex);
      } finally {
         client.close();
      }
      return res;
   }

   public Boolean storeFactEvent(FactType fact) {
      ObjectContainer client = null;
      Boolean res = new Boolean(false);
      try {
         fact.setIdHash(calcIdHash(fact));
         fact.setFactHash(calcFactHash(fact));

         openFactQueue();
//         String host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
//         String user = PropertyAccessor.getProperty("dss", "KMR_USER");
//         String pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
//         int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_FACT_PORT"));
//         log.debug("storeFactEvent: Open DB4OBJECTS_HOST=" + host + ", DB4OBJECTS_FACT_PORT=" + factPort);
         client = Db4o.openClient(host, factPort, user, pass);
         client.store(new FactWrapper(fact.getPrimaryKey(), fact));
         client.commit();
         res = new Boolean(true);
      } catch (Exception ex) {
         log.error("Error storing fact event.", ex);
      } finally {
         client.close();
      }
      return res;
   }

   public List<FactType> getFactEvents(String patientId, Object objClass) {
      List<FactType> res = new ArrayList<FactType>();
      try {
         List<FactWrapper> facts = readFactQueue(patientId);
         Iterator<FactWrapper> iter = facts.iterator();
         while (iter.hasNext()) {
            FactType fact = iter.next().getFact();
            if (fact.getClass().equals(objClass)) {
               res.add(fact);
            }
         }
      } catch (Exception ex) {
         log.error("Error reading fact events from object store.", ex);
      }
      return res;
   }

   public List<FactWrapper> readFactQueue(String patientId) {
      ObjectContainer client = null;
      List<FactWrapper> res = new ArrayList<FactWrapper>();
      try {
         openFactQueue();
//         String host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
//         String user = PropertyAccessor.getProperty("dss", "KMR_USER");
//         String pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
//         int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_FACT_PORT"));
         client = Db4o.openClient(host, factPort, user, pass);
         Query query = client.query();
         query.constrain(FactWrapper.class);
         query.descend("primaryKey").constrain(patientId);
         ObjectSet result = query.execute();
         while (result.hasNext()) {
            res.add((FactWrapper) result.next());
         }
      } catch (Exception ex) {
         log.error("Error reading fact events from object store.", ex);
      } finally {
         if (client != null) {
            client.close();
         }
      }
      return res;
   }

   public Boolean deleteAllPatientEvents(String patientId) {
      ObjectContainer client = null;
      ObjectSet result = null;
      Query query = null;
      Boolean res = new Boolean(false);
      boolean dbIsDirty = false;
      try {
         openFactQueue();
//         String host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
//         String user = PropertyAccessor.getProperty("dss", "KMR_USER");
//         String pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
//         int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_FACT_PORT"));
         client = Db4o.openClient(host, factPort, user, pass);

         // All FactTypes
         query = client.query();
         query.constrain(FactWrapper.class);
         query.descend("primaryKey").constrain(patientId);
         result = query.execute();
         log.debug(result.size() + " matching objects found for removal.");
         while (result.hasNext()) {
            Object obj = result.next();
            log.debug("Deleting " + ((FactWrapper) obj).getFact().getClass().getName());
            client.delete(obj);
            dbIsDirty = true;
         }

         if (dbIsDirty) {
            client.commit();
         }
         res = new Boolean(true);
      } catch (Exception ex) {
         if (dbIsDirty && (client != null)) {
            client.rollback();
         }
         log.error(ex);
      } finally {
         if (client != null) {
            client.close();
         }
      }
      return res;
   }

   public List<FactType> getHistoricDemographicFacts(String patientId) {
      List<FactType> res = null;
      try {
         if (PropertyAccessor.getPropertyBoolean("dss", "SIMULATE_DEMOGRAPHICS")) {
            log.debug("getHistoricDemographicFacts: simulating demographics for patient " + patientId);
            PropertyAccessor.forceRefresh("demographics" + patientId);
            NameFactType name = new NameFactType();
            name.setFamilyName(PropertyAccessor.getProperty("demographics" + patientId, "FAMILY_NAME"));
            name.setFirstName(PropertyAccessor.getProperty("demographics" + patientId, "FIRST_NAME"));
            name.setMiddleName(PropertyAccessor.getProperty("demographics" + patientId, "MIDDLE_NAME"));
            PersonFactType person = new PersonFactType();
            person.setPrimaryKey(patientId);
            person.setLegalName(name);
            CodeLabelPair gender = new CodeLabelPair();
            gender.setLabel(PropertyAccessor.getProperty("demographics" + patientId, "GENDER"));
            person.setGender(gender);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dob = df.parse(PropertyAccessor.getProperty("demographics" + patientId, "DOB"));
            person.setDateOfBirth(dob);
            ValueUnitPair age = new ValueUnitPair();
            age.setValue(PropertyAccessor.getProperty("demographics" + patientId, "AGE"));
            person.setAge(age);
            res = new ArrayList<FactType>();
            res.add(person);
         } else {
            log.debug("getHistoricDemographicFacts: simuretrieve demographics for patient " + patientId);
            res = factClient.getDemographicsFact(patientId);
         }
      } catch (Exception ex) {
         log.error("getHistoricDemographicFacts: Error retrieving historic demographic facts", ex);
      }

      return res;
   }

   public List<FactType> getHistoricAllergyFacts(String patientId) {
      List<FactType> res = null;
      try {
         //request.setRoot(homeCommunityId);
         //request.setAssigningAuthorityName(homeCommunityName);
         DateProperty beginDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Allergies", "Begin");
         DateProperty endDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Allergies", "End");
         log.debug("getHistoricAllergyFacts: beginDate=[" + beginDate + "] endDate=[" + endDate + "]");

         res = factClient.getAllergyFacts(
                 patientId,
                 (beginDate != null && !beginDate.isUnbounded() ? beginDate.getCDATime() : null),
                 (endDate != null && !endDate.isUnbounded() ? endDate.getCDATime() : null));
      } catch (Exception ex) {
         log.error("getHistoricAllergyFacts: Error retrieving historic allergy facts", ex);
      }

      return res;
   }

   public List<FactType> getHistoricMedicationFacts(String patientId) {
      List<FactType> res = null;
      try {
         DateProperty beginDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Medications", "Begin");
         DateProperty endDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Medications", "End");
         log.debug("getHistoricMedicationFacts: beginDate=[" + beginDate + "] endDate=[" + endDate + "]");

         res = factClient.getMedicationFacts(
                 patientId,
                 (beginDate != null && !beginDate.isUnbounded() ? beginDate.getCDATime() : null),
                 (endDate != null && !endDate.isUnbounded() ? endDate.getCDATime() : null));
      } catch (Exception ex) {
         log.error("getHistoricMedicationFacts: Error retrieving historic medication facts", ex);
      }
      return res;
   }

   public List<FactType> getHistoricProblemFacts(String patientId) {
      List<FactType> res = null;
      try {
         DateProperty beginDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Problems", "Begin");
         DateProperty endDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Problems", "End");
         log.debug("getHistoricProblemFacts: beginDate=[" + beginDate + "] endDate=[" + endDate + "]");

         res = factClient.getProblemFacts(
                 patientId,
                 (beginDate != null && !beginDate.isUnbounded() ? beginDate.getCDATime() : null),
                 (endDate != null && !endDate.isUnbounded() ? endDate.getCDATime() : null));
      } catch (Exception ex) {
         log.error("getHistoricProblemFacts: Error retrieving historic problem facts", ex);
      }
      return res;
   }

   /**
    * Retrieve historical result facts.
    * @param patientId
    * @return
    */
   public List<FactType> getHistoricResultFacts(String patientId) {
      List<FactType> res = null;
      try {
         DateProperty beginDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Results", "Begin");
         DateProperty endDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Results", "End");
         log.debug("getHistoricResultFacts: beginDate=[" + beginDate + "] endDate=[" + endDate + "]");

         res = factClient.getTestResultFacts(
                 patientId,
                 (beginDate != null && !beginDate.isUnbounded() ? beginDate.getCDATime() : null),
                 (endDate != null && !endDate.isUnbounded() ? endDate.getCDATime() : null));
      } catch (Exception ex) {
         log.error("getHistoricResultFacts: Error retrieving historic lab result facts", ex);
      }
      return res;
   }

   /**
    * Retrieve historical procedure facts.
    * @param patientId
    * @return
    */
   public List<FactType> getHistoricProcedureFacts(String patientId) {
      List<FactType> res = null;
      try {
         DateProperty beginDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Procedures", "Begin");
         DateProperty endDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Procedures", "End");
         log.debug("getHistoricProcedureFacts: beginDate=[" + beginDate + "] endDate=[" + endDate + "]");

         res = factClient.getProcedureFacts(
                 patientId,
                 (beginDate != null && !beginDate.isUnbounded() ? beginDate.getCDATime() : null),
                 (endDate != null && !endDate.isUnbounded() ? endDate.getCDATime() : null));
      } catch (Exception ex) {
         log.error("getHistoricProcedureFacts: Error retrieving historic procedure facts", ex);
      }
      return res;
   }

   /**
    * Retrieve historical appointment facts.
    * @param patientId
    * @param statuses
    * @return
    */
   public List<FactType> getHistoricAppointmentFacts(String patientId, List<String> statuses) {
      List<FactType> res = null;
      try {
         DateProperty beginDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Appointments", "Begin");
         DateProperty endDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Appointments", "End");
         log.debug("getHistoricAppointmentFacts: beginDate=[" + beginDate + "] endDate=[" + endDate + "]");

         res = factClient.getAppointmentFacts(
                 patientId,
                 (beginDate != null && !beginDate.isUnbounded() ? beginDate.getCDATime() : null),
                 (endDate != null && !endDate.isUnbounded() ? endDate.getCDATime() : null),
                 statuses);
      } catch (Exception ex) {
         log.error("getHistoricAppointmentFacts: Error retrieving historic appointment facts", ex);
      }
      return res;
   }

   public List<FactType> getHistoricAdmissionFacts(String patientId, List<String> statuses) {
      List<FactType> res = null;
      try {
         DateProperty beginDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Admissions", "Begin");
         DateProperty endDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Admissions", "End");
         log.debug("getHistoricAdmissionFacts: beginDate=[" + beginDate + "] endDate=[" + endDate + "]");

         res = factClient.getAdmissionFacts(
                 patientId,
                 (beginDate != null && !beginDate.isUnbounded() ? beginDate.getCDATime() : null),
                 (endDate != null && !endDate.isUnbounded() ? endDate.getCDATime() : null),
                 statuses);
      } catch (Exception ex) {
         log.error("getHistoricAdmissionFacts: Error retrieving historic admission facts", ex);
      }
      return res;
   }

   /**
    * Retrieve historical vital sign facts.
    * @param patientId
    * @return
    */
   public List<FactType> getHistoricVitalFacts(String patientId) {
      List<FactType> res = null;
      try {
         DateProperty beginDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Vitals", "Begin");
         DateProperty endDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Vitals", "End");
         log.debug("getHistoricVitalFacts: beginDate=[" + beginDate + "] endDate=[" + endDate + "]");

         res = factClient.getVitalFacts(
                 patientId,
                 (beginDate != null && !beginDate.isUnbounded() ? beginDate.getCDATime() : null),
                 (endDate != null && !endDate.isUnbounded() ? endDate.getCDATime() : null));
      } catch (Exception ex) {
         log.error("getHistoricVitalFacts: Error retrieving historic vital signs facts", ex);
      }
      return res;
   }

   /**
    * Retrieve historical immunization facts
    * @param patientId
    * @return
    */
   public List<FactType> getHistoricImmunizationFacts(String patientId) {
      List<FactType> res = null;
      try {
         DateProperty beginDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Immunizations", "Begin");
         DateProperty endDate = DatePropertyAccessor.getDateProperty("dss", "Patient", "Immunizations", "End");
         log.debug("getHistoricImmunizationFacts: beginDate=[" + beginDate + "] endDate=[" + endDate + "]");

         res = factClient.getImmunizationFacts(
                 patientId,
                 (beginDate != null && !beginDate.isUnbounded() ? beginDate.getCDATime() : null),
                 (endDate != null && !endDate.isUnbounded() ? endDate.getCDATime() : null));
      } catch (Exception ex) {
         log.error("getHistoricImmunizationFacts: Error retrieving historic immunization facts", ex);
      }
      return res;
   }

   /**
    * Retrieve PatientCohort object from DB4 object data store.
    * @param patientId
    * @return
    */
   public PatientCohort getPatientCohort(String patientId) {
      ObjectContainer client = null;
      PatientCohort res = null;
      try {
         openVMRStore();
//         String host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
//         String user = PropertyAccessor.getProperty("dss", "KMR_USER");
//         String pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
//         int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_VMR_PORT"));
         client = Db4o.openClient(host, vmrPort, user, pass);
         Query query = client.query();
         query.constrain(PatientCohort.class);
         query.descend("patientId").constrain(patientId);
         ObjectSet result = query.execute();
         if (result.size() > 0) {
            res = (PatientCohort) result.get(0);
            log.debug("getPatientCohort: PatientCohort retrieved for patient: " + patientId);
         } else {
            log.debug("getPatientCohort: PatientCohort not found for patient: " + patientId);
         }
      } catch (Exception ex) {
         log.error("getPatientCohort: Error retrieving PatientCohort from db4o", ex);
      } finally {
         if (client != null) {
            client.close();
         }
      }
      return res;
   }

   public Boolean storePatientCohort(PatientCohort patientCohort) {
      Boolean res = new Boolean(false);
      ObjectContainer client = null;
      if (patientCohort != null) {
         try {
            openVMRStore();
//            String host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
//            String user = PropertyAccessor.getProperty("dss", "KMR_USER");
//            String pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
//            int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_VMR_PORT"));
            client = Db4o.openClient(host, vmrPort, user, pass);
            client.store(patientCohort);
            client.commit();
            res = new Boolean(true);
            log.debug("storePatientCohort: PatientCohort stored for patient: " + patientCohort.getPatientId());
         } catch (Exception ex) {
            log.error(ex);
         } finally {
            if (client != null) {
               client.close();
            }
         }
      }
      return res;
   }

   public Boolean deletePatientCohort(String patientId) {
      ObjectContainer client = null;
      Boolean res = new Boolean(false);
      boolean dbIsDirty = false;
      try {
         openVMRStore();
//         String host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
//         String user = PropertyAccessor.getProperty("dss", "KMR_USER");
//         String pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
//         int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_VMR_PORT"));
         client = Db4o.openClient(host, vmrPort, user, pass);
         Query query = client.query();
         query.constrain(PatientCohort.class);
         query.descend("patientId").constrain(patientId);
         ObjectSet result = query.execute();
         while (result.hasNext()) {
            PatientCohort obj = (PatientCohort) result.next();
            client.delete(obj);
            dbIsDirty = true;
         }
         if (dbIsDirty && (client != null)) {
            client.commit();
         }
         res = new Boolean(true);
      } catch (Exception ex) {
         log.error(ex);
      } finally {
         if (client != null) {
            client.close();
         }
      }
      return res;
   }

   public VirtualMedicalRecord createVirtualMedicalRecord(String patientId) {
      VirtualMedicalRecord res = null;
      //ObjectContainer client = null;
      try {
         VirtualMedicalRecord obj = new VirtualMedicalRecord();
         obj.setPatientId(patientId);
         List<FactType> lDemographics = getHistoricDemographicFacts(patientId);
         if ((lDemographics != null) && (lDemographics.size() > 0)) {
            log.debug("createVirtualMedicalRecord: " + lDemographics.size() + " PersonFactType/SupportSourceFactType fact objects.");
            for (FactType fact : lDemographics) {
               fact.setPrimaryKey(patientId);
               if (fact instanceof PersonFactType) {
                  if (((PersonFactType) fact).getLegalName() != null) {
                     obj.setDemographics((PersonFactType) fact);
                     obj.getDemographics().setIdHash(calcIdHash((FactType) fact));
                     obj.getDemographics().setFactHash(calcFactHash((FactType) fact));
                     obj.getDemographics().setHistorical(true);
                  }
               } else {
                  SupportSourceFactType supportSource = (SupportSourceFactType) fact;
                  if (supportSource.getLegalName() != null) {
                     supportSource.setIdHash(calcIdHash((FactType) fact));
                     supportSource.setFactHash(calcFactHash((FactType) fact));
                     supportSource.setHistorical(true);
                     obj.getSupportSources().add(supportSource);
                  }
               }
            }

            // allergies
            List<FactType> lAllergy = getHistoricAllergyFacts(patientId);
            if (lAllergy != null && !lAllergy.isEmpty()) {
               log.debug("createVirtualMedicalRecord: " + lAllergy.size() + " AllergyFactType fact objects.");
               for (FactType fact : lAllergy) {
                  AllergyFactType allergy = (AllergyFactType) fact;
                  log.debug(AdapterFactServiceClient.toString(allergy));
                  if (allergy.getCodedProduct() != null || allergy.getFreeTextProduct() != null) {
                     allergy.setPrimaryKey(patientId);
                     allergy.setIdHash(calcIdHash((FactType) allergy));
                     allergy.setFactHash(calcFactHash((FactType) allergy));
                     allergy.setHistorical(true);
                     obj.getAllergies().add(allergy);
                  }
               }
            }

            // medications
            List<FactType> lMedication = getHistoricMedicationFacts(patientId);
            if (lMedication != null && !lMedication.isEmpty()) {
               log.debug("createVirtualMedicalRecord: " + lMedication.size() + " MedicationFactType fact objects.");
               for (FactType fact : lMedication) {
                  MedicationFactType medication = (MedicationFactType) fact;
                  log.debug(AdapterFactServiceClient.toString(medication));
                  if (medication.getCodedProductName() != null || medication.getFreeTextProductName() != null) {
                     medication.setPrimaryKey(patientId);
                     medication.setIdHash(calcIdHash((FactType) medication));
                     medication.setFactHash(calcFactHash((FactType) medication));
                     medication.setHistorical(true);
                     obj.getMedications().add(medication);
                  }
               }
            }

            // problems
            List<FactType> lProblem = getHistoricProblemFacts(patientId);
            if (lProblem != null && !lProblem.isEmpty()) {
               log.debug("createVirtualMedicalRecord: " + lProblem.size() + " ProblemFactType fact objects.");
               for (FactType fact : lProblem) {
                  ProblemFactType problem = (ProblemFactType) fact;
                  log.debug(AdapterFactServiceClient.toString(problem));
                  if (problem.getCodedProblem() != null || problem.getFreeTextProblem() != null) {
                     problem.setPrimaryKey(patientId);
                     problem.setIdHash(calcIdHash((FactType) problem));
                     problem.setFactHash(calcFactHash((FactType) problem));
                     problem.setHistorical(true);
                     obj.getProblems().add(problem);
                  }
               }
            }

            // results
            List<FactType> lResult = getHistoricResultFacts(patientId);
            if (lResult != null && !lResult.isEmpty()) {
               log.debug("createVirtualMedicalRecord: " + lResult.size() + " ResultFactType fact objects.");
               for (FactType fact : lResult) {
                  ResultFactType result = (ResultFactType) fact;
                  log.debug(AdapterFactServiceClient.toString(result));
                  if (result.getCodedTestType() != null || result.getFreeTextTestType() != null) {
                     result.setPrimaryKey(patientId);
                     result.setIdHash(calcIdHash((FactType) result));
                     result.setFactHash(calcFactHash((FactType) result));
                     result.setHistorical(true);
                     // specimen date is used for event timing and is required
                     // no point in adding until we have better null checking for @timestamp
                     // TODO Decide how best to handle this case
                     if (result.getSpecimenDate() != null) {
                        obj.getResults().add(result);
                     }
                  }
               }
            }

            // procedures
            List<FactType> facts = getHistoricProcedureFacts(patientId);
            if (facts != null && !facts.isEmpty()) {
               log.debug("createVirtualMedicalRecord: " + facts.size() + " ProcedureFactType fact objects.");
               for (FactType fact : facts) {
                  ProcedureFactType procedure = (ProcedureFactType) fact;
                  log.debug(AdapterFactServiceClient.toString(procedure));
                  if (procedure.getProcedureType() != null || 
                      (procedure.getFreeTextProcedureType() != null && !procedure.getFreeTextProcedureType().isEmpty())) {
                     procedure.setPrimaryKey(patientId);
                     procedure.setIdHash(calcIdHash(procedure));
                     procedure.setFactHash(calcFactHash(procedure));
                     procedure.setHistorical(true);
                     // specimen date is used for event timing and is required
                     // no point in adding until we have better null checking for @timestamp
                     // TODO Decide how best to handle this case
                     if (procedure.getProcedureDate() != null) {
                        obj.getProcedures().add(procedure);
                     }
                  }
               }
            }

            // vitals
            facts = getHistoricVitalFacts(patientId);
            if (facts != null && !facts.isEmpty()) {
               log.debug("createVirtualMedicalRecord: " + facts.size() + " VitalFactType fact objects.");
               for (FactType fact : facts) {
                  VitalFactType vital = (VitalFactType) fact;
                  log.debug(AdapterFactServiceClient.toString(vital));
                  if (vital.getCodedResultType() != null ||
                      (vital.getFreeTextResultType() != null && !vital.getFreeTextResultType().isEmpty())) {
                     vital.setPrimaryKey(patientId);
                     vital.setIdHash(calcIdHash(fact));
                     vital.setFactHash(calcFactHash(fact));
                     vital.setHistorical(true);
                     // specimen date is used for event timing and is required
                     // no point in adding until we have better null checking for @timestamp
                     // TODO Decide how best to handle this case
                     if (vital.getResultDate() != null) {
                        obj.getVitals().add(vital);
                     }
                  }
               }
            }

            // immunizations
            facts = getHistoricImmunizationFacts(patientId);
            if (facts != null && !facts.isEmpty()) {
               log.debug("createVirtualMedicalRecord: " + facts.size() + " ImmunizationFactType fact objects.");
               for (FactType fact : facts) {
                  ImmunizationFactType immunization = (ImmunizationFactType) fact;
                  log.debug(AdapterFactServiceClient.toString(immunization));
                  if (immunization.getCodedProduct() != null ||
                      (immunization.getFreeTextProductName() != null && !immunization.getFreeTextProductName().isEmpty())) {
                     immunization.setPrimaryKey(patientId);
                     immunization.setIdHash(calcIdHash(fact));
                     immunization.setFactHash(calcFactHash(fact));
                     immunization.setHistorical(true);
                     // specimen date is used for event timing and is required
                     // no point in adding until we have better null checking for @timestamp
                     // TODO Decide how best to handle this case
                     if (immunization.getAdministeredDate() != null) {
                        obj.getImmunizations().add(immunization);
                     }
                  }
               }
            }

            // appointments
            List<String> encounterStatus = new ArrayList<String>();
            encounterStatus.add("active");
            encounterStatus.add("completed");
            facts = getHistoricAppointmentFacts(patientId, encounterStatus);
            if (facts != null && !facts.isEmpty()) {
               log.debug("createVirtualMedicalRecord: " + facts.size() + " AppointmentFactType fact objects.");
               for (FactType fact : facts) {
                  AppointmentFactType appointment = (AppointmentFactType) fact;
                  log.debug(AdapterFactServiceClient.toString(appointment));
                  if (appointment.getCodedLocation() != null) {
                     appointment.setPrimaryKey(patientId);
                     appointment.setIdHash(calcIdHash(fact));
                     appointment.setFactHash(calcFactHash(fact));
                     appointment.setHistorical(true);
                     // specimen date is used for event timing and is required
                     // no point in adding until we have better null checking for @timestamp
                     // TODO Decide how best to handle this case
                     if (appointment.getStartDateTime() != null) {
                        obj.getAppointments().add(appointment);
                     }
                  }
               }
            }

           // admissions
            facts = getHistoricAdmissionFacts(patientId, encounterStatus);
            if (facts != null && !facts.isEmpty()) {
               log.debug("createVirtualMedicalRecord: " + facts.size() + " AdmissionFactType fact objects.");
               for (FactType fact : facts) {
                  AdmissionFactType admission = (AdmissionFactType) fact;
                  log.debug(AdapterFactServiceClient.toString(admission));
                  if (admission.getCodedServiceDeliveryLocation() != null) {
                     admission.setPrimaryKey(patientId);
                     admission.setIdHash(calcIdHash(fact));
                     admission.setFactHash(calcFactHash(fact));
                     admission.setHistorical(true);
                     // specimen date is used for event timing and is required
                     // no point in adding until we have better null checking for @timestamp
                     // TODO Decide how best to handle this case
                     if (admission.getStartDateTime() != null) {
                        obj.getAdmissions().add(admission);
                     }
                  }
               }
            }

            if (isValidFact(obj.getDemographics())) {
               res = obj;
               log.debug("createVirtualMedicalRecord:  VMR created for patient: " + patientId);
            } else {
               log.error("createVirtualMedicalRecord: Failed retrieval of historical demographic fact for patient " + patientId);
            }
         } else {
            log.error("createVirtualMedicalRecord: Failed retrieval of historical demographic fact for patient " + patientId);
         }
      } catch (Exception ex) {
         log.error(ex);
//      } finally {
//         if (client != null) {
//            client.close();
//         }
      }
      return res;
   }

   public VirtualMedicalRecord getVirtualMedicalRecord(String patientId) {
      ObjectContainer client = null;
      VirtualMedicalRecord res = null;
      try {
         openVMRStore();
//         String host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
//         String user = PropertyAccessor.getProperty("dss", "KMR_USER");
//         String pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
//         int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_VMR_PORT"));
         client = Db4o.openClient(host, vmrPort, user, pass);
         Query query = client.query();
         query.constrain(VirtualMedicalRecord.class);
         query.descend("patientId").constrain(patientId);
         ObjectSet result = query.execute();
         if (result.size() > 0) {
            res = (VirtualMedicalRecord) result.get(0);
            log.debug("VMR retrieved for patient: " + patientId);
         } else {
            log.debug("VMR not found for patient: " + patientId);
         }
      } catch (Exception ex) {
         log.error("Error retrieving VMR from db4o", ex);
      } finally {
         if (client != null) {
            client.close();
         }
      }
      return res;
   }

   public Boolean storeVirtualMedicalRecord(VirtualMedicalRecord vmr) {
      Boolean res = new Boolean(false);
      ObjectContainer client = null;
      if (vmr != null) {
         try {
            openVMRStore();
//            String host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
//            String user = PropertyAccessor.getProperty("dss", "KMR_USER");
//            String pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
//            int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_VMR_PORT"));
            client = Db4o.openClient(host, vmrPort, user, pass);
            client.store(vmr);
            client.commit();
            res = new Boolean(true);
            log.debug("VMR stored for patient: " + vmr.getPatientId());
         } catch (Exception ex) {
            log.error(ex);
         } finally {
            if (client != null) {
               client.close();
            }
         }
      }
      return res;
   }

   public Boolean deleteVirtualMedicalRecord(String patientId) {
      ObjectContainer client = null;
      Boolean res = new Boolean(false);
      boolean dbIsDirty = false;
      try {
         openVMRStore();
//         String host = PropertyAccessor.getProperty("dss", "DB4OBJECTS_HOST");
//         String user = PropertyAccessor.getProperty("dss", "KMR_USER");
//         String pass = PropertyAccessor.getProperty("dss", "KMR_PASS");
//         int iPort = Integer.parseInt(PropertyAccessor.getProperty("dss", "DB4OBJECTS_VMR_PORT"));
         client = Db4o.openClient(host, vmrPort, user, pass);
         Query query = client.query();
         query.constrain(VirtualMedicalRecord.class);
         query.descend("patientId").constrain(patientId);
         ObjectSet result = query.execute();
         while (result.hasNext()) {
            VirtualMedicalRecord obj = (VirtualMedicalRecord) result.next();
            client.delete(obj);
            dbIsDirty = true;
         }
         if (dbIsDirty && (client != null)) {
            client.commit();
         }
         res = new Boolean(true);
      } catch (Exception ex) {
         log.error(ex);
      } finally {
         if (client != null) {
            client.close();
         }
      }
      return res;
   }

   public boolean isValidFact(FactType fact) {
      boolean valid = false;
      try {
         if (!fact.getPrimaryKey().isEmpty()) {
            if (fact instanceof PersonFactType) {
               if (!((PersonFactType) fact).getLegalName().getFamilyName().isEmpty() &&
                       !((PersonFactType) fact).getLegalName().getFirstName().isEmpty() &&
                       ((PersonFactType) fact).getGender() != null &&
                       ((PersonFactType) fact).getDateOfBirth() != null) {
                  valid = true;
               }
            } else if (fact instanceof SupportSourceFactType) {
               // TODO Determine minimum validation requirements
               if (!((SupportSourceFactType) fact).getLegalName().getFamilyName().isEmpty() &&
                       !((SupportSourceFactType) fact).getLegalName().getFirstName().isEmpty()) {
                  valid = true;
               }
            } else if (fact instanceof AllergyFactType) {
               // TODO Determine minimum validation requirements
               if (!((AllergyFactType) fact).getFreeTextProduct().isEmpty()) {
                  valid = true;
               }
               if (((AllergyFactType) fact).getCodedProduct() != null &&
                       !((AllergyFactType) fact).getCodedProduct().getCode().isEmpty()) {
                  valid = true;
               }
            } else if (fact instanceof MedicationFactType) {
               // TODO Determine minimum validation requirements
               if (!((MedicationFactType) fact).getFreeTextProductName().isEmpty()) {
                  valid = true;
               }
               if (((MedicationFactType) fact).getCodedProductName() != null &&
                       !((MedicationFactType) fact).getCodedProductName().getCode().isEmpty()) {
                  valid = true;
               }
            } else if (fact instanceof ProblemFactType) {
               // TODO Determine minimum validation requirements
               if (!((ProblemFactType) fact).getFreeTextProblem().isEmpty()) {
                  valid = true;
               }
               if (((ProblemFactType) fact).getCodedProblem() != null &&
                       !((ProblemFactType) fact).getCodedProblem().getCode().isEmpty()) {
                  valid = true;
               }
            } else if (fact instanceof ResultFactType) {
               // TODO Determine minimum validation requirements
               if (!((ResultFactType) fact).getId().get(0).getValue().isEmpty()) {
                  if (!((ResultFactType) fact).getFreeTextTestType().isEmpty()) {
                     valid = true;
                  }
                  if (((ResultFactType) fact).getCodedTestType() != null &&
                          !((ResultFactType) fact).getCodedTestType().getCode().isEmpty()) {
                     valid = true;
                  }
               }
            } else if (fact instanceof VitalFactType) {
               // TODO Determine minimum validation requirements
               if (!((VitalFactType) fact).getId().get(0).getValue().isEmpty()) {
                  if (!((VitalFactType) fact).getFreeTextResultType().isEmpty()) {
                     valid = true;
                  }
                  if (((VitalFactType) fact).getCodedResultType() != null &&
                          !((VitalFactType) fact).getCodedResultType().getCode().isEmpty()) {
                     valid = true;
                  }
               }
            } else if (fact instanceof ImmunizationFactType) {
               // TODO Determine minimum validation requirements
               if (!((ImmunizationFactType) fact).getId().get(0).getValue().isEmpty()) {
                  if (!((ImmunizationFactType) fact).getFreeTextProductName().isEmpty()) {
                     valid = true;
                  }
                  if (((ImmunizationFactType) fact).getCodedProduct() != null &&
                          !((ImmunizationFactType) fact).getCodedProduct().getCode().isEmpty()) {
                     valid = true;
                  }
               }
            } else if (fact instanceof ProcedureFactType) {
               // TODO Determine minimum validation requirements
               if (!((ProcedureFactType) fact).getId().get(0).getValue().isEmpty()) {
                  if (!((ProcedureFactType) fact).getFreeTextProcedureType().isEmpty()) {
                     valid = true;
                  }
                  if (((ProcedureFactType) fact).getProcedureType() != null &&
                          !((ProcedureFactType) fact).getProcedureType().getCode().isEmpty()) {
                     valid = true;
                  }
               }
            } else if (fact instanceof AppointmentFactType) {
               // TODO Determine minimum validation requirements
               if (!((AppointmentFactType) fact).getId().get(0).getValue().isEmpty()) {
                  if (!((AppointmentFactType) fact).getFreeTextLocation().isEmpty()) {
                     valid = true;
                  }
                  if (((AppointmentFactType) fact).getCodedLocation() != null &&
                          !((AppointmentFactType) fact).getCodedLocation().getCode().isEmpty()) {
                     valid = true;
                  }
               }
           } else if (fact instanceof AdmissionFactType) {
               // TODO Determine minimum validation requirements
               if (!((AdmissionFactType) fact).getId().get(0).getValue().isEmpty()) {
                  if (!((AdmissionFactType) fact).getServiceDeliveryLocationName().isEmpty()) {
                     valid = true;
                  }
                  if (((AdmissionFactType) fact).getCodedServiceDeliveryLocation() != null &&
                          !((AdmissionFactType) fact).getCodedServiceDeliveryLocation().getCode().isEmpty()) {
                     valid = true;
                  }
               }
           }
         }
      } catch (Exception e) {
      }
      return valid;
   }

   public String calcFactHash(FactType fact) {
      String hash = null;
      // Too resource intensice to do a deep copy of this object
      // Save the values to be normalized into an object that implements FactType
      // then normalize values not useful in determining duplicates
      FactType copy = null;
      if (fact instanceof PersonFactType) {
         copy = new PersonFactType();
         ((PersonFactType) copy).setAge(((PersonFactType) fact).getAge());
         ((PersonFactType) fact).setAge(null);
      } else if (fact instanceof SupportSourceFactType) {
         copy = new SupportSourceFactType();
      } else if (fact instanceof AllergyFactType) {
         copy = new AllergyFactType();
      } else if (fact instanceof MedicationFactType) {
         copy = new MedicationFactType();
      } else if (fact instanceof ProblemFactType) {
         copy = new ProblemFactType();
      } else if (fact instanceof VitalFactType) {
         copy = new VitalFactType();
      } else if (fact instanceof ImmunizationFactType) {
         copy = new ImmunizationFactType();
      } else if (fact instanceof ProcedureFactType) {
         copy = new ProcedureFactType();
      } else if (fact instanceof AdmissionFactType) {
         copy = new ProcedureFactType();
      } else if (fact instanceof AppointmentFactType) {
         copy = new ProcedureFactType();
      } else if (fact instanceof DiagnosisFactType) {
         copy = new DiagnosisFactType();
      } else if (fact instanceof ResultFactType) {
         // Check for valid specimenDate
         if (((ResultFactType) fact).getSpecimenDate() == null) {
            log.debug("Invalid specimenDate.");
         }
//         else {
//            log.debug("specimenDate: " + ((ResultFactType) fact).getSpecimenDate());
//            log.debug("specimenDate class name: " + ((ResultFactType) fact).getSpecimenDate().getClass().getName());
//         }
         copy = new ResultFactType();
      }
      copy.setHistorical(fact.isHistorical());
      fact.setHistorical(false);
      copy.setFactHash(fact.getFactHash());
      fact.setFactHash(null);
      copy.setIdHash(fact.getIdHash());
      fact.setIdHash(null);
      copy.setSourceSystem(fact.getSourceSystem());
      fact.setSourceSystem(null);

      try {
         XStream xstream = new XStream();
         xstream.alias("FactType", FactType.class);
         xstream.alias("AddressFactType", AddressFactType.class);
         xstream.alias("AllergyFactType", AllergyFactType.class);
         xstream.alias("CodeLabelPair", CodeLabelPair.class);
         xstream.alias("CodeSystemPair", CodeSystemPair.class);
         xstream.alias("FulfillmentFactType", FulfillmentFactType.class);
         xstream.alias("MedicationFactType", MedicationFactType.class);
         xstream.alias("NameFactType", NameFactType.class);
         xstream.alias("OrderFactType", OrderFactType.class);
         xstream.alias("PersonFactType", PersonFactType.class);
         xstream.alias("PreConditionFactType", PreConditionFactType.class);
         xstream.alias("ProblemFactType", ProblemFactType.class);
         xstream.alias("ReactionFactType", ReactionFactType.class);
         xstream.alias("ResultFactType", ResultFactType.class);
         xstream.alias("SeverityFactType", SeverityFactType.class);
         xstream.alias("SupportSourceFactType", SupportSourceFactType.class);
         xstream.alias("TelecomFactType", TelecomFactType.class);
         xstream.alias("ValueType", ValueType.class);
         xstream.alias("ValueUnitPair", ValueUnitPair.class);
         xstream.alias("VitalFactType", VitalFactType.class);
         xstream.alias("ImmunizationFactType", ImmunizationFactType.class);
         xstream.alias("ProcedureFactType", ProcedureFactType.class);
         xstream.alias("AppointmentFactType", AppointmentFactType.class);
         xstream.alias("AdmissionFactType", AdmissionFactType.class);
         xstream.alias("DiagnosisFactType", AdmissionFactType.class);

         //log.debug(xstream.toXML(fact));
         hash = SHA1HashCode.calculateSHA1(xstream.toXML(fact));
         log.debug(fact.getClass().getName() + " hash value = " + hash);
      } catch (Exception ex) {
         log.error("Failed to create SHA-1 Hash code: ", ex);
      }

      // Replace the normalized values with the original values
      fact.setHistorical(copy.isHistorical());
      fact.setFactHash(copy.getFactHash());
      fact.setIdHash(copy.getIdHash());
      fact.setSourceSystem(copy.getSourceSystem());
      if (fact.getClass().equals(PersonFactType.class)) {
         ((PersonFactType) fact).setAge(((PersonFactType) copy).getAge());
      }

      return hash;
   }

   public String calcIdHash(FactType fact) {
      String hash = null;
      String sId = fact.getPrimaryKey();
      try {
         if (fact instanceof PersonFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            sId = sId.concat(((PersonFactType) fact).getLegalName().getFamilyName());
            sId = sId.concat(((PersonFactType) fact).getLegalName().getFirstName());
            sId = sId.concat(new Long(((PersonFactType) fact).getDateOfBirth().getTime()).toString());
            sId = sId.concat(((PersonFactType) fact).getGender().getLabel());
         } else if (fact instanceof SupportSourceFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            sId = sId.concat(((SupportSourceFactType) fact).getLegalName().getFamilyName());
            sId = sId.concat(((SupportSourceFactType) fact).getLegalName().getFirstName());
         } else if (fact instanceof AllergyFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            if (((AllergyFactType) fact).getFreeTextProduct() != null) {
               sId = sId.concat(((AllergyFactType) fact).getFreeTextProduct());
            }
            if (((AllergyFactType) fact).getCodedProduct() != null) {
               sId = sId.concat(((AllergyFactType) fact).getCodedProduct().getCode());
               sId = sId.concat(((AllergyFactType) fact).getCodedProduct().getCodeSystem());
            }
         } else if (fact instanceof MedicationFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            if (((MedicationFactType) fact).getFreeTextProductName() != null) {
               sId = sId.concat(((MedicationFactType) fact).getFreeTextProductName());
            }
            if (((MedicationFactType) fact).getCodedProductName() != null) {
               sId = sId.concat(((MedicationFactType) fact).getCodedProductName().getCode());
               sId = sId.concat(((MedicationFactType) fact).getCodedProductName().getCodeSystem());
            }
         } else if (fact instanceof ProblemFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            if (((ProblemFactType) fact).getFreeTextProblem() != null) {
               sId = sId.concat(((ProblemFactType) fact).getFreeTextProblem());
            }
            if (((ProblemFactType) fact).getCodedProblem() != null) {
               sId = sId.concat(((ProblemFactType) fact).getCodedProblem().getCode());
               sId = sId.concat(((ProblemFactType) fact).getCodedProblem().getCodeSystem());
            }
         } else if (fact instanceof ResultFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            sId = sId.concat(((ResultFactType) fact).getId().get(0).getValue());
            if (((ResultFactType) fact).getFreeTextTestType() != null) {
               sId = sId.concat(((ResultFactType) fact).getFreeTextTestType());
            }
            if (((ResultFactType) fact).getCodedTestType() != null) {
               sId = sId.concat(((ResultFactType) fact).getCodedTestType().getCode());
               sId = sId.concat(((ResultFactType) fact).getCodedTestType().getCodeSystem());
            }
         } else if (fact instanceof VitalFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            sId = sId.concat(((VitalFactType) fact).getId().get(0).getValue());
            if (((VitalFactType) fact).getFreeTextResultType() != null) {
               sId = sId.concat(((VitalFactType) fact).getFreeTextResultType());
            }
            if (((VitalFactType) fact).getCodedResultType() != null) {
               sId = sId.concat(((VitalFactType) fact).getCodedResultType().getCode());
               sId = sId.concat(((VitalFactType) fact).getCodedResultType().getCodeSystem());
            }
        } else if (fact instanceof ImmunizationFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            sId = sId.concat(((ImmunizationFactType) fact).getId().get(0).getValue());
            if (((ImmunizationFactType) fact).getFreeTextProductName() != null) {
               sId = sId.concat(((ImmunizationFactType) fact).getFreeTextProductName());
            }
            if (((ImmunizationFactType) fact).getCodedProduct() != null) {
               sId = sId.concat(((ImmunizationFactType) fact).getCodedProduct().getCode());
               sId = sId.concat(((ImmunizationFactType) fact).getCodedProduct().getCodeSystem());
            }
        } else if (fact instanceof ProcedureFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            sId = sId.concat(((ProcedureFactType) fact).getId().get(0).getValue());
            if (((ProcedureFactType) fact).getFreeTextProcedureType() != null) {
               sId = sId.concat(((ProcedureFactType) fact).getFreeTextProcedureType());
            }
            if (((ProcedureFactType) fact).getProcedureType() != null) {
               sId = sId.concat(((ProcedureFactType) fact).getProcedureType().getCode());
               sId = sId.concat(((ProcedureFactType) fact).getProcedureType().getCodeSystem());
            }
        } else if (fact instanceof AppointmentFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            sId = sId.concat(((AppointmentFactType) fact).getId().get(0).getValue());
            if (((AppointmentFactType) fact).getFreeTextLocation() != null) {
               sId = sId.concat(((AppointmentFactType) fact).getFreeTextLocation());
            }
            if (((AppointmentFactType) fact).getCodedLocation() != null) {
               sId = sId.concat(((AppointmentFactType) fact).getCodedLocation().getCode());
               sId = sId.concat(((AppointmentFactType) fact).getCodedLocation().getCodeSystem());
            }
        } else if (fact instanceof AdmissionFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            sId = sId.concat(((AdmissionFactType) fact).getId().get(0).getValue());
            if (((AdmissionFactType) fact).getServiceDeliveryLocationName() != null) {
               sId = sId.concat(((AdmissionFactType) fact).getServiceDeliveryLocationName());
            }
            if (((AdmissionFactType) fact).getCodedServiceDeliveryLocation() != null) {
               sId = sId.concat(((AdmissionFactType) fact).getCodedServiceDeliveryLocation().getCode());
               sId = sId.concat(((AdmissionFactType) fact).getCodedServiceDeliveryLocation().getCodeSystem());
            }
        } else if (fact instanceof DiagnosisFactType) {
            //TODO: Identify all components that identify these records, but would not change in an update
            sId = sId.concat(((DiagnosisFactType) fact).getId().get(0).getValue());
            if (((DiagnosisFactType) fact).getEncounterId() != null) {
               sId = sId.concat(((DiagnosisFactType) fact).getEncounterId().getValue());
            }
         }

         hash = SHA1HashCode.calculateSHA1(sId);
         log.debug(fact.getClass().getName() + " id hash value = " + hash);
      } catch (Exception ex) {
         log.error("Failed to create SHA-1 Hash code: ", ex);
      }
      return hash;
   }
}
