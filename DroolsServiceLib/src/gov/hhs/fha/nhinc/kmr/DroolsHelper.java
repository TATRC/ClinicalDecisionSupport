package gov.hhs.fha.nhinc.kmr;

import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.AllergyFactType;
import gov.hhs.fha.nhinc.adapter.fact.MedicationFactType;
import gov.hhs.fha.nhinc.adapter.fact.ProblemFactType;
import gov.hhs.fha.nhinc.adapter.fact.ResultFactType;

import gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort;

import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessor;
import gov.hhs.fha.nhinc.kmr.properties.PropertyAccessException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.drools.command.runtime.BatchExecutionCommandImpl;
import org.drools.command.runtime.SetGlobalCommand;
import org.drools.command.runtime.rule.InsertObjectCommand;
import org.drools.command.runtime.rule.FireAllRulesCommand;
import org.drools.command.runtime.rule.GetObjectsCommand;
import org.drools.command.runtime.rule.RetractCommand;
import org.drools.jax.soap.CommandExecutor;
import org.drools.jax.soap.CommandExecutorPortType;
import org.drools.xml.jaxb.util.DroolsJaxbContextHelper;

import java.io.StringWriter;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Steven Clark
 */
public class DroolsHelper {

   private static Log log = LogFactory.getLog(DroolsHelper.class);
   private String droolsBaseURL;
   private String droolsGeneralKSession;
   private String droolsURL;
   private String dssURL;
   private String tmURL;
   private String namespaceURI = "http://soap.jax.drools.org/";
   private Map<String, Dispatch<SOAPMessage>> dispatches = new HashMap<String, Dispatch<SOAPMessage>>();
   private static List<String> myDomainClasses = null;
   private static List<String> inclusionTableClasses = null;
   private static List<String> cohortClasses = null;

   static {
      myDomainClasses = new ArrayList<String>();
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.FactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.AddressFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.AllergyFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeSystemPair");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.FulfillmentFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.MedicationFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.NameFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.OrderFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.PersonFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.PreConditionFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ProblemFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ReactionFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ResultFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.SeverityFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.SupportSourceFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.TelecomFactType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ValueType");
      myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ValueUnitPair");

      inclusionTableClasses = new ArrayList<String>();
      inclusionTableClasses.add("gov.hhs.fha.nhinc.kmr.InclusionTable");
      inclusionTableClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair");
      inclusionTableClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeSystemPair");

      cohortClasses = new ArrayList<String>();
      cohortClasses.add("gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort");
      cohortClasses.add("gov.hhs.fha.nhinc.kmr.patientcohort.PopulationRegistriesType");
      cohortClasses.add("gov.hhs.fha.nhinc.kmr.patientcohort.PopulationType");
      cohortClasses.add("gov.hhs.fha.nhinc.kmr.patientcohort.PreferenceType");
      cohortClasses.add("gov.hhs.fha.nhinc.kmr.patientcohort.PreferencesType");
   }

   public DroolsHelper() {
      try {
         //droolsURL = PropertyAccessor.getProperty("dss", "DROOLS_SERVICE_ENDPOINT");
         droolsBaseURL = PropertyAccessor.getProperty("dss", "DROOLS_KSERVICE_BASE");
         droolsGeneralKSession = PropertyAccessor.getProperty("dss", "DROOLS_GENERAL_KSESSION");
         dssURL = PropertyAccessor.getProperty("dss", "DSS_SESSION_SERVICE");
         tmURL = PropertyAccessor.getProperty("dss", "TASK_MANAGER_SERVICE");

         // log info
         log.info("DroolsHelper:: default droolsBaseURL (DROOLS_KSERVICE_BASE) set to " + droolsBaseURL);
         log.info("DroolsHelper:: default dssURL (DSS_SESSION_SERVICE) set to " + dssURL);
         log.info("DroolsHelper:: default tmURL (TASK_MANAGER_SERVICE) set to " + tmURL);
      } catch (PropertyAccessException pae) {
         log.error("DroolsHelper:: Error initializing endpoint properties", pae);
      }
   }

//   public DroolsHelper(String droolsURL, String dssURL, String tmURL) {
//      this.droolsURL = droolsURL;
//      this.dssURL = dssURL;
//      this.tmURL = tmURL;
//
//      // log info
//      log.info("DroolsHelper:: droolsURL=" + droolsURL);
//      log.info("DroolsHelper:: dssURL=" + dssURL);
//      log.info("DroolsHelper:: tmURL=" + tmURL);
//   }

   /*
    * Get knowledge session rules in preparation for a new patient session
    *
    * @param PatientCohort patientCohort - Contains the patient population information necessary to choose a set of knowledge modules
    * @return boolean value indicating success
    */
   public boolean getSessionRules(PatientCohort cohort) {
      boolean success = false;
      try {
         // Use general session to determine session rules for this patient
         String sessionId = PropertyAccessor.getProperty("dss", "DROOLS_GENERAL_KSESSION");
         log.debug("getSessionRules: DROOLS_GENERAL_KSESSION=" + sessionId);
         BatchExecutionCommandImpl batch = new BatchExecutionCommandImpl();
         batch.setLookup(sessionId);

         // set globals
         //DSSHelper dssHelper = new DSSHelper(dssURL);
         SetGlobalCommand setGlobalCommand = new SetGlobalCommand();
         //setGlobalCommand.setIdentifier("dssHelper");
         //setGlobalCommand.setObject(dssHelper);
         //setGlobalCommand.setOut(true);
         //setGlobalCommand.setOutIdentifier("dssHelper");
         //batch.getCommands().add(setGlobalCommand);

         //setGlobalCommand = new SetGlobalCommand();
         setGlobalCommand.setIdentifier("dssURL");
         setGlobalCommand.setObject(dssURL);
         setGlobalCommand.setOut(true);
         setGlobalCommand.setOutIdentifier("dssURL");
         batch.getCommands().add(setGlobalCommand);

         StringWriter xmlReq = new StringWriter();
         //List<String> myDomainClasses = new ArrayList<String>();
         //myDomainClasses.add("gov.hhs.fha.nhinc.kmr.DSSHelper");
         //JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(myDomainClasses, null);
         JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(new ArrayList<String>(), null);
         Marshaller marshaller = jc.createMarshaller();
         marshaller.setProperty("jaxb.formatted.output", true);
         marshaller.marshal(batch, xmlReq);
         // execute set-global command
         if (!xmlReq.toString().isEmpty()) {
            log.debug("getSessionRules: execute set-global command:\n" + xmlReq.toString());
            String result = executeRules(xmlReq.toString());
            log.debug(result);
         } else {
            log.error("getSessionRules: Failed to marshal Drools command");
         }

         // Send the patient cohort object to the general knowledge session with the historical flag = false.
         // There should be a rule to initialize the session parameters based on the patient info
         // and trigger a call to DSS SessionWS
         String droolsCmd = createRules(sessionId, cohort);
         if (droolsCmd != null && !droolsCmd.isEmpty()) {
            log.debug("getSessionRules: Drools command:\n" + droolsCmd);
            String droolsResult = executeRules(droolsCmd);
            if (droolsResult != null && !droolsResult.isEmpty()) {
               log.debug("getSessionRules: Drools result:\n" + droolsResult);
               success = true;
            }
         }
      } catch (Exception ex) {
         ex.printStackTrace();
         log.error("getSessionRules: Error calling Drools session", ex);
      }
      return success;
   }

   /*
    * Initialize a new knowledge session for a given patient
    *
    * @param KnowledgeSession sessionInfo - Contains the knowledge session info submitted to SessionWS by Drools engine
    * @return Boolean success
    */
   public Boolean initSession(KnowledgeSession sessionInfo) {
      Boolean success = new Boolean(false);
      String sessionId = null;
      try {
         // This is where we would use the session Info to initialize a drools session
         // and load knowledge modules into the session
         // For this release, all sessions are pre-configured and
         // the session ID is set to the patient id with a "ks" prefix
         sessionId = "ks" + sessionInfo.getPatientId();
         log.debug("initSession: sessionId=" + sessionId);
         BatchExecutionCommandImpl batch = new BatchExecutionCommandImpl();
         batch.setLookup(sessionId);

         // set globals
         SetGlobalCommand setGlobalCommand = new SetGlobalCommand();
         setGlobalCommand = new SetGlobalCommand();
         setGlobalCommand.setIdentifier("tmURL");
         setGlobalCommand.setObject(tmURL);
         setGlobalCommand.setOut(true);
         setGlobalCommand.setOutIdentifier("tmURL");
         batch.getCommands().add(setGlobalCommand);

         StringWriter xmlReq = new StringWriter();
         //List<String> myDomainClasses = new ArrayList<String>();
         //JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(myDomainClasses, null);
         JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(new ArrayList<String>(), null);
         Marshaller marshaller = jc.createMarshaller();
         marshaller.setProperty("jaxb.formatted.output", true);
         marshaller.marshal(batch, xmlReq);
         log.debug("initSession:\n" + xmlReq.toString());
         if (!xmlReq.toString().isEmpty()) {
            //String result = executeRules(xmlReq.toString());
            String result = executeRules(xmlReq.toString(), "ks" + sessionInfo.getPatientId());
            log.debug("initSession: " + result);
            sessionInfo.setSessionId(sessionId);
            success = new Boolean(true);
         } else {
            log.error("initSession: Failed to marshal Drools command");
         }
      } catch (Exception ex) {
         ex.printStackTrace();
         log.error("initSession: Error initializing Drools session", ex);
      }
      return success;
   }

   public void closeSession(String sessionId, String sessionEndpoint, List<String> factHandles) {
      StringWriter xmlReq = null;
      BatchExecutionCommandImpl batch = null;
      try {
         //List<String> myDomainClasses = new ArrayList<String>();
         //JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(myDomainClasses, null);
         JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(new ArrayList<String>(), null);
         Marshaller marshaller = jc.createMarshaller();
         marshaller.setProperty("jaxb.formatted.output", true);

         if (factHandles != null && factHandles.size() > 0) {
            batch = new BatchExecutionCommandImpl();
            batch.setLookup(sessionId);

            // We'll use rectract commands until we have something else to reset the session
            for (String factHandle : factHandles) {
               // Drools is expecting 6 ":" separated elements in the fact handle
               // If there are only 5, then we'll add ":DEFAULT"
               String sHandle = factHandle;
               if (factHandle.split(":").length == 5) {
                  sHandle = factHandle + ":DEFAULT";
               }
               RetractCommand retractCommand = new RetractCommand();
               retractCommand.setFactHandleFromString(sHandle);
               batch.getCommands().add(retractCommand);
            }

            xmlReq = new StringWriter();
            marshaller.marshal(batch, xmlReq);
            log.debug("closeSession: drools command\n" + xmlReq.toString());
            if (!xmlReq.toString().isEmpty()) {
               String result = executeRules(xmlReq.toString(), sessionEndpoint);
               log.debug("closeSession: " + result);
            } else {
               log.error("closeSession: Failed to marshal Drools command");
            }
         } else {
            log.debug("No known fact handles to retract.");
         }

         // See what objects are left...
         batch = new BatchExecutionCommandImpl();
         batch.setLookup(sessionId);
         GetObjectsCommand getObjectsCommand = new GetObjectsCommand();
         getObjectsCommand.setOutIdentifier("output");
         batch.getCommands().add(getObjectsCommand);

         xmlReq = new StringWriter();
         marshaller.marshal(batch, xmlReq);
         log.debug("closeSession: drools command\n" + xmlReq.toString());
         if (!xmlReq.toString().isEmpty()) {
            String result = executeRules(xmlReq.toString(), sessionEndpoint);
            log.debug("closeSession: " + result);
         } else {
            log.error("closeSession: Failed to marshal Drools command");
         }
      } catch (Exception ex) {
         log.error("closeSession: Error closing Drools session", ex);
      }
   }

   public String createRules(String session, PatientCohort cohort) {
      String res = null;
      try {
         BatchExecutionCommandImpl batch = new BatchExecutionCommandImpl();
         batch.setLookup(session);
         InsertObjectCommand insertObjectCommand = new InsertObjectCommand(cohort);
         insertObjectCommand.setOutIdentifier("output");
         batch.getCommands().add(insertObjectCommand);
         FireAllRulesCommand fireAllRulesCommand = new FireAllRulesCommand();
         //fireAllRulesCommand.setOutIdentifier("output");
         batch.getCommands().add(fireAllRulesCommand);

         StringWriter xmlReq = new StringWriter();
//         List<String> myDomainClasses = new ArrayList<String>();
//         myDomainClasses.add("gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort");
//         myDomainClasses.add("gov.hhs.fha.nhinc.kmr.patientcohort.PopulationRegistriesType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.kmr.patientcohort.PopulationType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.kmr.patientcohort.PreferenceType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.kmr.patientcohort.PreferencesType");

         //JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(myDomainClasses, null);
         JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(cohortClasses, null);
         Marshaller marshaller = jc.createMarshaller();
         marshaller.setProperty("jaxb.formatted.output", true);
         marshaller.marshal(batch, xmlReq);
         res = xmlReq.toString();
      } catch (Exception ex) {
         log.error("Error creating Drools command from patientcohort: ", ex);
      }
      return res;
   }

   public String createRules(String session, InclusionTable incTable) {
      String res = null;
      try {
         BatchExecutionCommandImpl batch = new BatchExecutionCommandImpl();
         batch.setLookup(session);
         InsertObjectCommand insertObjectCommand = new InsertObjectCommand(incTable);
         insertObjectCommand.setOutIdentifier("output");
         batch.getCommands().add(insertObjectCommand);
         FireAllRulesCommand fireAllRulesCommand = new FireAllRulesCommand();
         //fireAllRulesCommand.setOutIdentifier("output");
         batch.getCommands().add(fireAllRulesCommand);

         StringWriter xmlReq = new StringWriter();
//         List<String> myDomainClasses = new ArrayList<String>();
//         myDomainClasses.add("gov.hhs.fha.nhinc.kmr.InclusionTable");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeSystemPair");

         //JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(myDomainClasses, null);
         JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(inclusionTableClasses, null);
         Marshaller marshaller = jc.createMarshaller();
         marshaller.setProperty("jaxb.formatted.output", true);
         marshaller.marshal(batch, xmlReq);
         res = xmlReq.toString();
      } catch (Exception ex) {
         log.error("Error creating Drools command from clinical reference tables: ", ex);
      }
      return res;
   }

   public String createRules(String session, FactType fact) {
      String res = null;
      try {
         BatchExecutionCommandImpl batch = new BatchExecutionCommandImpl();
         batch.setLookup(session);
         InsertObjectCommand insertObjectCommand = new InsertObjectCommand(fact);
         insertObjectCommand.setOutIdentifier("output");
         batch.getCommands().add(insertObjectCommand);
         FireAllRulesCommand fireAllRulesCommand = new FireAllRulesCommand();
         //fireAllRulesCommand.setOutIdentifier("output");
         batch.getCommands().add(fireAllRulesCommand);

         StringWriter xmlReq = new StringWriter();
//         List<String> myDomainClasses = new ArrayList<String>();
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.FactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.AddressFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.AllergyFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeSystemPair");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.FulfillmentFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.MedicationFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.NameFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.OrderFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.PersonFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.PreConditionFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ProblemFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ReactionFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ResultFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.SeverityFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.SupportSourceFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.TelecomFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ValueType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ValueUnitPair");
         JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(myDomainClasses, null);
         Marshaller marshaller = jc.createMarshaller();
         marshaller.setProperty("jaxb.formatted.output", true);
         marshaller.marshal(batch, xmlReq);
         res = xmlReq.toString();
      } catch (Exception ex) {
         log.error("Error creating Drools command from clinical fact: ", ex);
      }
      return res;
   }

   public String createRules(String session, List<FactType> facts) {
      String res = null;
      int iFactCount = 0;
      try {
         BatchExecutionCommandImpl batch = new BatchExecutionCommandImpl();
         batch.setLookup(session);
         Iterator iter = facts.iterator();
         while (iter.hasNext()) {
            InsertObjectCommand insertObjectCommand = new InsertObjectCommand(iter.next());
            insertObjectCommand.setOutIdentifier("output" + iFactCount++);
            batch.getCommands().add(insertObjectCommand);
         }
         FireAllRulesCommand fireAllRulesCommand = new FireAllRulesCommand();
         //fireAllRulesCommand.setOutIdentifier("output");
         batch.getCommands().add(fireAllRulesCommand);

         StringWriter xmlReq = new StringWriter();
//         List<String> myDomainClasses = new ArrayList<String>();
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.FactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.AddressFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.AllergyFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeSystemPair");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.FulfillmentFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.MedicationFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.NameFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.OrderFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.PersonFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.PreConditionFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ProblemFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ReactionFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ResultFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.SeverityFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.SupportSourceFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.TelecomFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ValueType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ValueUnitPair");
         JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(myDomainClasses, null);
         Marshaller marshaller = jc.createMarshaller();
         marshaller.setProperty("jaxb.formatted.output", true);
         marshaller.marshal(batch, xmlReq);
         res = xmlReq.toString();
      } catch (Exception ex) {
         log.error("Error creating Drools command from clinical fact: ", ex);
      }
      return res;
   }

   public String createRules(String session, VirtualMedicalRecord vmr) {
      String res = null;
      Iterator iter = null;
      try {
         BatchExecutionCommandImpl batch = new BatchExecutionCommandImpl();
         batch.setLookup(session);

         List<AllergyFactType> lAllergy = vmr.getAllergies();
         iter = lAllergy.iterator();
         while (iter.hasNext()) {
            InsertObjectCommand insertObjectCommand = new InsertObjectCommand(iter.next());
            insertObjectCommand.setOutIdentifier("output");
            batch.getCommands().add(insertObjectCommand);
         }
         List<MedicationFactType> lMedication = vmr.getMedications();
         iter = lMedication.iterator();
         while (iter.hasNext()) {
            InsertObjectCommand insertObjectCommand = new InsertObjectCommand(iter.next());
            insertObjectCommand.setOutIdentifier("output");
            batch.getCommands().add(insertObjectCommand);
         }
         List<ProblemFactType> lProblem = vmr.getProblems();
         iter = lProblem.iterator();
         while (iter.hasNext()) {
            InsertObjectCommand insertObjectCommand = new InsertObjectCommand(iter.next());
            insertObjectCommand.setOutIdentifier("output");
            batch.getCommands().add(insertObjectCommand);
         }
         List<ResultFactType> lResult = vmr.getResults();
         iter = lResult.iterator();
         while (iter.hasNext()) {
            InsertObjectCommand insertObjectCommand = new InsertObjectCommand(iter.next());
            insertObjectCommand.setOutIdentifier("output");
            batch.getCommands().add(insertObjectCommand);
         }

         // Add this last so it will be inserted first
         InsertObjectCommand insertObjectCommand = new InsertObjectCommand(vmr.getDemographics());
         insertObjectCommand.setOutIdentifier("output");
         batch.getCommands().add(insertObjectCommand);

         FireAllRulesCommand fireAllRulesCommand = new FireAllRulesCommand();
         fireAllRulesCommand.setOutIdentifier("output");
         batch.getCommands().add(fireAllRulesCommand);

         StringWriter xmlReq = new StringWriter();
//         List<String> myDomainClasses = new ArrayList<String>();
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.FactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.AddressFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.AllergyFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.CodeSystemPair");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.FulfillmentFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.MedicationFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.NameFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.OrderFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.PersonFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.PreConditionFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ProblemFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ReactionFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ResultFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.SeverityFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.SupportSourceFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.TelecomFactType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ValueType");
//         myDomainClasses.add("gov.hhs.fha.nhinc.adapter.fact.ValueUnitPair");
         JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(myDomainClasses, null);
         Marshaller marshaller = jc.createMarshaller();
         marshaller.setProperty("jaxb.formatted.output", true);
         marshaller.marshal(batch, xmlReq);
         res = xmlReq.toString();
      } catch (Exception ex) {
         log.error("Error creating Drools command from VMR: ", ex);
      }
      return res;
   }

   public String executeRules(String cmd) {
      String res = null;
      try {
         CommandExecutor cmdExecutor = new CommandExecutor();
         CommandExecutorPortType port = cmdExecutor.getCommandExecutorPort();

         // no endpoint supplied, use default K-session
         String ksession = droolsGeneralKSession.replace("ks", "soap");
         String endpoint = droolsBaseURL + "/" + ksession;
         log.debug("executeRules:  Drools server URL=" + endpoint);

         ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
         Object response = port.execute(cmd);
         if (response != null) {
            res = ((Element) response).getTextContent();
         }
         /*
         URL wsdlURL = new URL(droolsBaseURL+"/"+sessionEndpoint+"?wsdl");
         Dispatch<SOAPMessage> dispatch;
         Service service = Service.create(wsdlURL, new QName(namespaceURI, "CommandExecutor"));
         dispatch = service.createDispatch(new QName(namespaceURI, "CommandExecutorPort"), SOAPMessage.class, Service.Mode.MESSAGE);
         ((BindingProvider)dispatch).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, droolsBaseURL+"/"+sessionEndpoint);
         SOAPMessage message = MessageFactory.newInstance().createMessage();
         SOAPBody body = message.getSOAPPart().getEnvelope().getBody();
         QName payloadName = new QName(namespaceURI, "execute", "ns1");
         QName argName = new QName(namespaceURI, "arg0", "ns1");
         SOAPElement payloadElement = body.addBodyElement(payloadName);
         SOAPElement argElement = payloadElement.addChildElement(argName);
         argElement.addTextNode(cmd);

         SOAPMessage response = dispatch.invoke(message);
         if (response != null && response.getSOAPBody() != null &&
         response.getSOAPBody().getFirstChild() != null)
         {
         log.debug("SOAP body contains: " + response.getSOAPBody().getFirstChild().getNodeName());
         if (response.getSOAPBody().getFirstChild().getFirstChild() != null)
         {
         log.debug(response.getSOAPBody().getFirstChild().getNodeName() + " contains: " + response.getSOAPBody().getFirstChild().getFirstChild().getNodeName());
         res = response.getSOAPBody().getFirstChild().getFirstChild().getTextContent();
         }
         else
         {
         res = response.getSOAPBody().getFirstChild().getTextContent();
         }
         }
         else
         {
         res = response.getSOAPBody().getTextContent();
         }
          */
      } catch (Exception ex) {
         log.error("Error executing Drools command: ", ex);
      }
      return res;
   }

   public String executeRules(String cmd, String endpoint) {
      String res = null;

      if (endpoint == null) {
         log.error("executeRules: Invalid endpoint -- " + endpoint);
      } else {
         String ksession = endpoint.replace("ks", "soap");
         ksession = droolsBaseURL + "/" + ksession;
         log.debug("executeRules: Drools Server URL=" + ksession);

         try {
            CommandExecutor cmdExecutor = new CommandExecutor();
            CommandExecutorPortType port = cmdExecutor.getCommandExecutorPort();
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ksession);
            Object response = port.execute(cmd);
            if (response != null) {
               res = ((Element) response).getTextContent();
            }
         } catch (Exception ex) {
            log.error("executeRules: Error executing Drools command: ", ex);
         }
      }
      return res;
   }

   public void updateHandleList(String xmlResponse, List<String> factHandles) {
      int iHandleCount = 0;
      if (xmlResponse != null && factHandles != null) {
         try {
            /*
            List<String> myDomainClasses = new ArrayList<String>();
            JAXBContext jc = DroolsJaxbContextHelper.createDroolsJaxbContext(myDomainClasses,null);
            ExecutionResults resp = (ExecutionResults) jc.createUnmarshaller().unmarshal(new ByteArrayInputStream(xmlResponse.getBytes()));
            Collection<String> identifiers = resp.getIdentifiers();
            for (String identifier: identifiers)
            {
            log.debug("Identifier: " + identifier);
            FactHandle factHandle = (FactHandle)resp.getFactHandle(identifier);
            if (factHandle != null)
            {
            factHandles.add(factHandle.toExternalForm());
            log.debug("factHandle.toExternalForm(): " + factHandle.toExternalForm());
            iHandleCount++;
            }
            }
             */
            Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xmlResponse.getBytes()));
            NodeList nlFacts = xmlDoc.getElementsByTagName("facts");
            if (nlFacts.getLength() > 0) {
               NodeList nlValues = ((Element) nlFacts.item(0)).getElementsByTagName("value");
               log.debug(nlFacts.getLength() + " value nodes found in facts node.");
               for (int i = 0; i < nlValues.getLength(); i++) {
                  if (nlValues.item(i).getTextContent() != null) {
                     factHandles.add(nlValues.item(i).getTextContent());
                     log.debug("factHandle: " + nlValues.item(i).getTextContent());
                     iHandleCount++;
                  }
               }
            }
            log.debug(iHandleCount + " fact handles added to list.");
         } catch (Exception ex) {
            log.error("Error updating fact handle list: ", ex);
         }
      }
   }
}
