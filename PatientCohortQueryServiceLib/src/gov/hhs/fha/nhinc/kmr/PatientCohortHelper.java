package gov.hhs.fha.nhinc.kmr;

import javax.xml.ws.BindingProvider;

import com.thoughtworks.xstream.XStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort;
import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohortQuery;
import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohortQueryPortType;
import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohortQueryRequestType;
import gov.hhs.fha.nhinc.kmr.patientcohort.PopulationRegistriesType;
import gov.hhs.fha.nhinc.kmr.patientcohort.PopulationType;
import gov.hhs.fha.nhinc.kmr.patientcohort.PreferenceType;
import gov.hhs.fha.nhinc.kmr.patientcohort.PreferencesType;

import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessException;

/**
 *
 * @author Steven Clark
 */
public class PatientCohortHelper {

   private static Log log = LogFactory.getLog(PatientCohortHelper.class);
   private String serviceURL;

   /**
    * No-argument Constructor
    *
    * @return PatientCohortHelper instance
    */
   public PatientCohortHelper() {
      try {
         serviceURL = PropertyAccessor.getProperty("dss", "PATIENT_COHORT_SERVICE");
         log.info("PatientCohortHelper: Instantiated with PATIENT_COHORT_SERVICE=" + serviceURL);
      } catch (PropertyAccessException pae) {
         log.error("Error initializing endpoint properties", pae);
         // use defaults
         this.serviceURL = "http://nhinint01.asu.edu:8080/PatientCohortQuery/PatientCohortQueryService";
         log.error("PatientCohortHelper: Instantiated with default " + this.serviceURL);
      }
   }

   /**
    * Constructor with patient cohort service endpoint
    *
    * @param serviceURL - String containing the task manager service endpoint
    * @return PatientCohortHelper instance
    */
   public PatientCohortHelper(String serviceURL) {
      if (serviceURL != null && !serviceURL.isEmpty()) {
         this.serviceURL = serviceURL;
         log.info("PatientCohortHelper:  Instantiated with " + serviceURL);
      } else {
         try {
            serviceURL = PropertyAccessor.getProperty("dss", "PATIENT_COHORT_SERVICE");
            log.debug("PatientCohortHelper: Instantiated with PATIENT_COHORT_SERVICE=" + serviceURL);
         } catch (PropertyAccessException pae) {
            log.error("PatientCohortHelper: Error initializing endpoint properties - ", pae);
            // use defaults
            this.serviceURL = "http://nhinint01.asu.edu:8080/PatientCohortQuery/PatientCohortQueryService";
            log.error("PatientCohortHelper; Instantiated with default " + this.serviceURL);
         }
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
    * Send Patient Cohort Query request message to the patient cohort service
    *
    * @param String - String containing patient ID
    * @return PatientCohort Object
    */
   public PatientCohort sendQuery(String patientId) {
      PatientCohort res = null;
      try {
         PatientCohortQueryRequestType request = new PatientCohortQueryRequestType();
         request.setPatientId(patientId);

         log.debug("sendQuery: preparing Patient Cohort Query Request for patient: " + request.getPatientId());
         XStream xstream = new XStream();
         xstream.alias("PatientCohortQueryRequestType", PatientCohortQueryRequestType.class);

         log.debug("sendQuery: sending PatientCohortQueryRequest=" + xstream.toXML(request));

         PatientCohortQuery service = new PatientCohortQuery();
         PatientCohortQueryPortType port = service.getPatientCohortQueryPortSoap();
         ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceURL);
         res = port.patientCohortQuery(request);

         xstream = new XStream();
         xstream.alias("PatientCohort", PatientCohort.class);
         xstream.alias("PopulationRegistriesType", PopulationRegistriesType.class);
         xstream.alias("PopulationType", PopulationType.class);
         xstream.alias("PreferencesType", PreferencesType.class);
         xstream.alias("PreferenceType", PreferenceType.class);

         log.debug("sendQuery: received Patient Cohort Query Response=" + xstream.toXML(res));
      } catch (Exception ex) {
         if (res == null) {
            log.error("sendQuery: Error calling Patient Cohort Service: ", ex);
         } else {
            log.error("sendQuery: Error marshalling Patient Cohort Service reply: ", ex);
         }

         // Temporary cheat while PatientCohortService is under construction
         //if (res == null || res.getPatientId() == null || res.getPatientId().isEmpty())
         // {
         //     res = new PatientCohort();
         //     res.setPatientId(patientId);
         // }
      }
      return res;
   }
}
