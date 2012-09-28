/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.helper;

import gov.hhs.fha.nhinc.adapter.commondatalayer.client.InvalidParameterException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.DateUtils;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.Utils;
import gov.hhs.fha.nhinc.adapter.fact.EncounterSearchPayloadType;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import java.util.Date;
import java.util.Properties;
import org.hl7.v3.ActClassControlAct;
import org.hl7.v3.CD;
import org.hl7.v3.CE;
import org.hl7.v3.CS;
import org.hl7.v3.CV;
import org.hl7.v3.FindEncountersPRPAIN900300UV02RequestType;
import org.hl7.v3.II;
import org.hl7.v3.IVLTS;
import org.hl7.v3.IVXBTS;
import org.hl7.v3.PRPAIN900300UV02MCCIMT000100UV01Message;
import org.hl7.v3.PRPAIN900300UV02QUQIMT021001UV01ControlActProcess;
import org.hl7.v3.PRPAMT900300UV02CareEventID;
import org.hl7.v3.PRPAMT900300UV02EncounterStatus;
import org.hl7.v3.PRPAMT900300UV02EncounterTimeframe;
import org.hl7.v3.PRPAMT900300UV02PatientId;
import org.hl7.v3.PRPAMT900300UV02PatientLocationID;
import org.hl7.v3.PRPAMT900300UV02QueryByParameterPayload;
import org.hl7.v3.PRPAMT900300UV02TypeOfEncounter;
import org.hl7.v3.TSExplicit;
import org.hl7.v3.XActMoodIntentEvent;
import org.hl7.v3.ObjectFactory;

/**
 *
 * @author kim
 */
public class FindEncountersRequestHelper extends RequestHelper {

   public final static String FIND_ENCOUNTERS_INTERACTION = "PRPA_IN900300UV02";
   public final static String FIND_ENCOUNTERS_QUERY_TRIGGER = "PRPA_TE900300UV02";

   public FindEncountersRequestHelper(Properties properties, ObjectFactory hl7Factory) {
      super(properties, hl7Factory);
   }

   public FindEncountersPRPAIN900300UV02RequestType createRequest(FactQueryRequestType request) throws InvalidParameterException, Exception {

      // check interaction id
      if (request.getInteractionId() == null || request.getInteractionId().isEmpty() ||
              !request.getInteractionId().equalsIgnoreCase(FIND_ENCOUNTERS_INTERACTION)) {
         throw new InvalidParameterException("Interaction ID is invalid, expected " + FIND_ENCOUNTERS_INTERACTION + ", received " + request.getInteractionId());
      }

      // check interaction id
      if (request.getTriggerEventCode() == null || request.getTriggerEventCode().isEmpty() ||
              !request.getTriggerEventCode().equalsIgnoreCase(FIND_ENCOUNTERS_QUERY_TRIGGER)) {
         throw new InvalidParameterException("Trigger Evenet is invalid, expected " + FIND_ENCOUNTERS_QUERY_TRIGGER + ", received " + request.getTriggerEventCode());
      }

      // must have the right payload
      if (request.getEncounterPayload() == null) {
         throw new InvalidParameterException("Missing required payload!");
      }

      // check type of encounter
      if (request.getEncounterPayload().getTypeOfEncounter() == null || request.getEncounterPayload().getTypeOfEncounter().isEmpty()) {
         throw new InvalidParameterException("Type Of Encounter criteria is missing!");
      }
      else {
         // must have value of "AMB" or "IMP"
         if (!request.getEncounterPayload().getTypeOfEncounter().equalsIgnoreCase("AMB") &&
             !request.getEncounterPayload().getTypeOfEncounter().equalsIgnoreCase("IMP")) {
             throw new InvalidParameterException("Type Of Encounter=" + request.getEncounterPayload().getTypeOfEncounter() + " is not supported!");
         }
      }

      // check patient if
      if (request.getEncounterPayload().getPatientId() == null || request.getEncounterPayload().getPatientId().isEmpty()) {
         throw new InvalidParameterException("Patient identifier is missing!");
      }
      
      FindEncountersPRPAIN900300UV02RequestType msg = new FindEncountersPRPAIN900300UV02RequestType();

      try {
        msg.setQuery(buildPRPAIN900300UV02(request));
      }
      catch(Exception e) {
        e.printStackTrace();
        throw e;
      }

      return msg;
   }

   public PRPAIN900300UV02MCCIMT000100UV01Message buildPRPAIN900300UV02(FactQueryRequestType request) {
      PRPAIN900300UV02MCCIMT000100UV01Message query = new PRPAIN900300UV02MCCIMT000100UV01Message();

      query.setId(setMessageId());

      TSExplicit creationTime = new TSExplicit();
      creationTime.setValue(DateUtils.convertToCDATime(new Date()));
      query.setCreationTime(creationTime);

      query.setInteractionId(setInteractionId(FIND_ENCOUNTERS_INTERACTION));

      query.setProcessingCode(setCSCode(PROCESSING_CODE));

      query.setProcessingModeCode(setCSCode(PROCESSING_MOOD_CODE));

      query.setAcceptAckCode(setCSCode(ACCEPT_ACK_CODE));

      // Set the receiver and sender
      query.getReceiver().add(createReceiver());
      query.setSender(createSender(request.getSenderId()));

      query.setControlActProcess(createControlActProcess(request.getEncounterPayload()));

      return query;
   }

   private PRPAIN900300UV02QUQIMT021001UV01ControlActProcess createControlActProcess(EncounterSearchPayloadType payload) {
      PRPAIN900300UV02QUQIMT021001UV01ControlActProcess controlActProcess = new PRPAIN900300UV02QUQIMT021001UV01ControlActProcess();

      controlActProcess.setMoodCode(XActMoodIntentEvent.EVN);
      controlActProcess.setClassCode(ActClassControlAct.CACT);

      CD triggerCode = new CD();
      triggerCode.setCode(FIND_ENCOUNTERS_QUERY_TRIGGER);
      //triggerCode.setCodeSystem("2.16.840.1.113883.1.6");
      controlActProcess.setCode(triggerCode);

      CE priority = new CE();
      priority.setCode("R");
      controlActProcess.getPriorityCode().add(priority);

      controlActProcess.setQueryByParameterPayload(
         factory.createPRPAIN900300UV02QUQIMT021001UV01ControlActProcessQueryByParameterPayload(createQueryParams(payload)));

      return controlActProcess;
   }

   private PRPAMT900300UV02QueryByParameterPayload createQueryParams(EncounterSearchPayloadType payload) {
      PRPAMT900300UV02QueryByParameterPayload params = new PRPAMT900300UV02QueryByParameterPayload();

      II id = new II();
      id.setRoot(homeOid);
      id.setExtension(Utils.generateDocumentId());
      params.setQueryId(id);

      CS statusCode = new CS();
      statusCode.setCode("new");
      params.setStatusCode(statusCode);

      CS responseModalityCode = new CS();
      responseModalityCode.setCode("R");

      CS responsePriorityCode = new CS();
      responsePriorityCode.setCode("I");

      // set patient identifier
      if (payload.getPatientId() != null && !payload.getPatientId().isEmpty()) {
         PRPAMT900300UV02PatientId patientId = new PRPAMT900300UV02PatientId();
         II idValue = new II();
         idValue.setExtension(payload.getPatientId());
         idValue.setRoot(homeOid);
         patientId.getValue().add(idValue);
         params.setPatientId(patientId);
      }

      // set type of encounter
      PRPAMT900300UV02TypeOfEncounter typeOfEncounter = new PRPAMT900300UV02TypeOfEncounter();
      CD typeOfEncounterValue = new CD();
      typeOfEncounterValue.setCode(payload.getTypeOfEncounter().toUpperCase());
      typeOfEncounter.setValue(typeOfEncounterValue);
      params.setTypeOfEncounter(factory.createPRPAMT900300UV02QueryByParameterPayloadTypeOfEncounter(typeOfEncounter));

      // set specified encounters as criteria
      if (!payload.getCareEventId().isEmpty()) {
         PRPAMT900300UV02CareEventID careEvent = new PRPAMT900300UV02CareEventID();
         for (int i= 0; i < payload.getCareEventId().size(); i++) {
            II careEventId = new II();
            careEventId.setExtension(payload.getCareEventId().get(i));
            careEvent.getValue().add(careEventId);
         }
         params.setCareEventID(factory.createPRPAMT900300UV02QueryByParameterPayloadCareEventID(careEvent));
      }

      // set time intervals criteria
      PRPAMT900300UV02EncounterTimeframe searchRimeFrame = new PRPAMT900300UV02EncounterTimeframe();
      IVLTS timeIntervals = new IVLTS();

      IVXBTS startTime = new IVXBTS();
      if (payload.getEncounterStartTimeFrame() != null && !payload.getEncounterStartTimeFrame().isEmpty()) {
         startTime.setValue(payload.getEncounterStartTimeFrame());
      } else {
         startTime.getNullFlavor().add("UNK");
      }
      timeIntervals.getRest().add(factory.createIVLTSLow(startTime));
      
      IVXBTS stopTime = new IVXBTS();
      if (payload.getEncounterEndTimeFrame() != null && !payload.getEncounterEndTimeFrame().isEmpty()) {
         stopTime.setValue(payload.getEncounterStartTimeFrame());
      } else {
         stopTime.getNullFlavor().add("UNK");
      }
      timeIntervals.getRest().add(factory.createIVLTSHigh(stopTime));

      searchRimeFrame.setValue(timeIntervals);
      params.setEncounterTimeframe(factory.createPRPAMT900300UV02QueryByParameterPayloadEncounterTimeframe(searchRimeFrame));

      // set encounter status filter criteria
      if (payload.getEncounterStatus() != null && !payload.getEncounterStatus().isEmpty()) {
         PRPAMT900300UV02EncounterStatus encounterStatus = new PRPAMT900300UV02EncounterStatus();
         for (int i= 0; i < payload.getEncounterStatus().size(); i++) {
            CV status = new CV();
            status.setCode(payload.getEncounterStatus().get(i));
            encounterStatus.getValue().add(status);
         }
         params.setEncounterStatus(factory.createPRPAMT900300UV02QueryByParameterPayloadEncounterStatus(encounterStatus));
      }
      
      // set identifier for patient location
      if (payload.getPatientLocationId() != null && !payload.getPatientLocationId().isEmpty()) {
         PRPAMT900300UV02PatientLocationID locationId = new PRPAMT900300UV02PatientLocationID();
         II locationIdValue = new II();
         locationIdValue.setExtension(payload.getPatientLocationId());
         params.getPatientLocationID().add(locationId);
      }
      
      return params;
   }
}
