/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.helper;

import gov.hhs.fha.nhinc.adapter.commondatalayer.client.InvalidParameterException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.Utils;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.RecordQueryPayloadType;
import java.util.Date;
import java.util.Properties;
import org.hl7.v3.ActClassControlAct;
import org.hl7.v3.CD;
import org.hl7.v3.CE;
import org.hl7.v3.CS;
import org.hl7.v3.CareRecordQUPCIN040100UV01RequestType;
import org.hl7.v3.II;
import org.hl7.v3.QUPCIN040100UV01MCCIMT000100UV01Message;
import org.hl7.v3.QUPCIN040100UV01QUQIMT020001UV01ControlActProcess;
import org.hl7.v3.QUPCIN040100UV01QUQIMT020001UV01QueryByParameter;
import org.hl7.v3.QUPCMT040000UV01CareRecordId;
import org.hl7.v3.QUPCMT040000UV01ParameterList;
import org.hl7.v3.QUPCMT040000UV01PatientId;
import org.hl7.v3.XActMoodIntentEvent;
import org.hl7.v3.ObjectFactory;

/**
 *
 * @author kim
 */
public class CareRecordRequestHelper extends RequestHelper {

   public final static String CARE_RECORD_QUERY_INTERACTION = "QUPC_IN040100UV01";
   public final static String CARE_RECORD_QUERY_TRIGGER = "QUPC_TE040100UV01";

   public CareRecordRequestHelper(Properties properties, ObjectFactory hl7Factory) {
      super(properties, hl7Factory);
   }

   public CareRecordQUPCIN040100UV01RequestType createRequest(FactQueryRequestType request) throws InvalidParameterException {

      // check interaction id
      if (request.getInteractionId() == null || request.getInteractionId().isEmpty() ||
              !request.getInteractionId().equalsIgnoreCase(CARE_RECORD_QUERY_INTERACTION)) {
         throw new InvalidParameterException("Interaction ID is invalid, expected " + CARE_RECORD_QUERY_INTERACTION + ", received " + request.getInteractionId());
      }

      // check interaction id
      if (request.getTriggerEventCode() == null || request.getTriggerEventCode().isEmpty() ||
              !request.getTriggerEventCode().equalsIgnoreCase(CARE_RECORD_QUERY_TRIGGER)) {
         throw new InvalidParameterException("Trigger Evenet is invalid, expected " + CARE_RECORD_QUERY_TRIGGER + ", received " + request.getTriggerEventCode());
      }

      // must have the right payload
      if (request.getRecordQueryPayload() == null) {
         throw new InvalidParameterException("Missing required payload!");
      }

      // check type of encounter
      if (request.getRecordQueryPayload().getTypeOfEncounter() == null || request.getRecordQueryPayload().getTypeOfEncounter().isEmpty()) {
         throw new InvalidParameterException("Type Of Encounter criteria is missing!");
      }
      else {
         // must have value of "AMB" or "IMP"
         if (!request.getRecordQueryPayload().getTypeOfEncounter().equalsIgnoreCase("AMB") &&
             !request.getRecordQueryPayload().getTypeOfEncounter().equalsIgnoreCase("IMP")) {
             throw new InvalidParameterException("Type Of Encounter=" + request.getEncounterPayload().getTypeOfEncounter() + " is not supported!");
         }
      }

      // check patient if
      if (request.getRecordQueryPayload().getPatientId() == null || request.getRecordQueryPayload().getPatientId().isEmpty()) {
         throw new InvalidParameterException("Patient identifier is missing!");
      }

      // check record id
      if (request.getRecordQueryPayload().getRecordId() == null || request.getRecordQueryPayload().getRecordId().isEmpty()) {
         throw new InvalidParameterException("Record identifier is missing!");
      }
      
      CareRecordQUPCIN040100UV01RequestType msg = new CareRecordQUPCIN040100UV01RequestType();

      msg.setQuery(buildQUPCIN040100UV01(request));

      return msg;
   }

   public QUPCIN040100UV01MCCIMT000100UV01Message buildQUPCIN040100UV01(FactQueryRequestType request) {
      QUPCIN040100UV01MCCIMT000100UV01Message query = new QUPCIN040100UV01MCCIMT000100UV01Message();

      query.setId(setMessageId());

      query.setCreationTime(setMessageTimestamp(new Date()));

      query.setInteractionId(setInteractionId(CARE_RECORD_QUERY_INTERACTION));

      query.setProcessingCode(setCSCode(PROCESSING_CODE));

      query.setProcessingModeCode(setCSCode(PROCESSING_MOOD_CODE));

      query.setAcceptAckCode(setCSCode(ACCEPT_ACK_CODE));

      // Set the receiver and sender
      query.getReceiver().add(createReceiver());
      query.setSender(createSender(request.getSenderId()));

      query.setControlActProcess(createControlActProcess(request.getRecordQueryPayload()));

      return query;
   }

   private QUPCIN040100UV01QUQIMT020001UV01ControlActProcess createControlActProcess(RecordQueryPayloadType payload) {
      QUPCIN040100UV01QUQIMT020001UV01ControlActProcess controlActProcess = new QUPCIN040100UV01QUQIMT020001UV01ControlActProcess();

      controlActProcess.setMoodCode(XActMoodIntentEvent.EVN);
      controlActProcess.setClassCode(ActClassControlAct.CACT);

      CD triggerCode = new CD();
      triggerCode.setCode(CARE_RECORD_QUERY_TRIGGER);
      //triggerCode.setCodeSystem("2.16.840.1.113883.1.6");
      controlActProcess.setCode(triggerCode);

      CE priority = new CE();
      priority.setCode("R");
      controlActProcess.getPriorityCode().add(priority);

      controlActProcess.setQueryByParameter(
              factory.createQUPCIN040100UV01QUQIMT020001UV01ControlActProcessQueryByParameter(createQueryParams(payload)));

      return controlActProcess;
   }

   private QUPCIN040100UV01QUQIMT020001UV01QueryByParameter createQueryParams(RecordQueryPayloadType payload) {
      QUPCIN040100UV01QUQIMT020001UV01QueryByParameter params = new QUPCIN040100UV01QUQIMT020001UV01QueryByParameter();

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

      QUPCMT040000UV01ParameterList paramslist = new QUPCMT040000UV01ParameterList();

      // set patient identifier
      if (payload.getPatientId() != null && !payload.getPatientId().isEmpty()) {
         QUPCMT040000UV01PatientId patientId = new QUPCMT040000UV01PatientId();
         II idValue = new II();
         idValue.setExtension(payload.getPatientId());
         idValue.setRoot(homeOid);
         patientId.setValue(idValue);
         paramslist.setPatientId(factory.createQUPCMT040000UV01ParameterListPatientId(patientId));
      }

      // set encounter id
      if (!payload.getRecordId().isEmpty()) {
         QUPCMT040000UV01CareRecordId recordId = new QUPCMT040000UV01CareRecordId();
         II recordIdValue = new II();
         recordIdValue.setExtension(payload.getRecordId());
         recordId.setValue(id);
         paramslist.setCareRecordId(recordId);
      }

      params.getParameterList().add(paramslist);

      return params;
   }
}
