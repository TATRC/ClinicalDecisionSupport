/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.helper;

import gov.hhs.fha.nhinc.adapter.commondatalayer.client.InvalidParameterException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.DateUtils;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.Utils;
import gov.hhs.fha.nhinc.adapter.fact.CareRecordPayloadType;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import java.util.Date;
import java.util.Properties;
import org.hl7.v3.ActClassControlAct;
import org.hl7.v3.CD;
import org.hl7.v3.CE;
import org.hl7.v3.CS;
import org.hl7.v3.CareRecordQUPCIN043100UV01RequestType;
import org.hl7.v3.II;
import org.hl7.v3.IVLTSExplicit;
import org.hl7.v3.IVXBTSExplicit;
import org.hl7.v3.QUPCIN043100UV01MCCIMT000100UV01Message;
import org.hl7.v3.QUPCIN043100UV01QUQIMT020001UV01ControlActProcess;
import org.hl7.v3.QUPCIN043100UV01QUQIMT020001UV01QueryByParameter;
import org.hl7.v3.QUPCMT040300UV01CareProvisionCode;
import org.hl7.v3.QUPCMT040300UV01CareRecordTimePeriod;
import org.hl7.v3.QUPCMT040300UV01ParameterList;
import org.hl7.v3.QUPCMT040300UV01PatientId;
import org.hl7.v3.TSExplicit;
import org.hl7.v3.XActMoodIntentEvent;
import org.hl7.v3.ObjectFactory;

/**
 * This
 * @author kim
 */
public class CareRecordProfileRequestHelper extends RequestHelper {

   public final static String CARE_RECORD_QUERY_INTERACTION_ID = "QUPC_IN043100UV";
   public final static String CARE_RECORD_QUERY_TRIGGER = "QUPC_TE043100UV01";
   public final static String CARE_PROVISION_CODE_SYSNAME = "IHEActCode";
   public final static String CARE_PROVISION_CODE_SYSOID = "1.3.5.1.4.1.19376.1.5.3.2";

   public CareRecordProfileRequestHelper(Properties properties, ObjectFactory hl7Factory) {
      super(properties, hl7Factory);
   }

   public CareRecordQUPCIN043100UV01RequestType createRequest(FactQueryRequestType request) throws InvalidParameterException {
      CareRecordQUPCIN043100UV01RequestType msg = new CareRecordQUPCIN043100UV01RequestType();

      // check interaction id
      if (request.getInteractionId() == null || request.getInteractionId().isEmpty() ||
              !request.getInteractionId().equalsIgnoreCase(CARE_RECORD_QUERY_INTERACTION_ID)) {
         throw new InvalidParameterException("Interaction ID is invalid, expected " + CARE_RECORD_QUERY_INTERACTION_ID + ", received " + request.getInteractionId());
      }

      // check interaction id
      if (request.getTriggerEventCode() == null || request.getTriggerEventCode().isEmpty() ||
              !request.getTriggerEventCode().equalsIgnoreCase(CARE_RECORD_QUERY_TRIGGER)) {
         throw new InvalidParameterException("Trigger Evenet is invalid, expected " + CARE_RECORD_QUERY_TRIGGER + ", received " + request.getTriggerEventCode());
      }

      // check payload
      //if (request.getCareRecordPayload() == null) {
      //   throw new InvalidParameterException("Missing required payload!");
      //}

//      if (request.getCareRecordPayload().getPatientId() == null ||
//              request.getCareRecordPayload().getPatientId().isEmpty()) {
//         throw new InvalidParameterException("Missing required patient in payload!");
//      }
//
//      if (request.getCareRecordPayload().getCareProvisionCode() == null ||
//              request.getCareRecordPayload().getCareProvisionCode().isEmpty()) {
//         throw new InvalidParameterException("Missing required care record type!");
//      }

      msg.setLocalDeviceId(homeOid);
      msg.setReceiverOID(homeOid);
      msg.setSenderOID(homeOid);

      msg.setQuery(buildQUPCIN043100UV01(request.getSenderId(), request.getCareRecordPayload()));

      return msg;
   }

   private QUPCIN043100UV01MCCIMT000100UV01Message buildQUPCIN043100UV01(String senderId, CareRecordPayloadType payload) {
      QUPCIN043100UV01MCCIMT000100UV01Message query = new QUPCIN043100UV01MCCIMT000100UV01Message();

      query.setId(setMessageId());

      TSExplicit creationTime = new TSExplicit();
      creationTime.setValue(DateUtils.convertToCDATime(new Date()));
      query.setCreationTime(creationTime);

      query.setInteractionId(setInteractionId(CARE_RECORD_QUERY_INTERACTION_ID));

      query.setProcessingCode(setCSCode(PROCESSING_CODE));

      query.setProcessingModeCode(setCSCode(PROCESSING_MOOD_CODE));

      query.setAcceptAckCode(setCSCode(ACCEPT_ACK_CODE));

      // Set the receiver and sender
      query.getReceiver().add(createReceiver());
      if (senderId != null && !senderId.isEmpty()) {
         query.setSender(createSender(senderId));
      }
      else {
         query.setSender(createSender(homeOid));
      }

      // set ControlActProcess in Message object
      query.setControlActProcess(createControlActProcess(payload));

      return query;
   }

   private QUPCIN043100UV01QUQIMT020001UV01ControlActProcess createControlActProcess(CareRecordPayloadType payload) {
      QUPCIN043100UV01QUQIMT020001UV01ControlActProcess controlActProcess =
              new QUPCIN043100UV01QUQIMT020001UV01ControlActProcess();

      controlActProcess.setMoodCode(XActMoodIntentEvent.EVN);
      controlActProcess.setClassCode(ActClassControlAct.CACT);

      CD triggerCode = new CD();
      triggerCode.setCode(CARE_RECORD_QUERY_TRIGGER);
      //code.setCodeSystem("2.16.840.1.113883.1.6");
      controlActProcess.setCode(triggerCode);

      CE priority = new CE();
      priority.setCode("R");
      controlActProcess.getPriorityCode().add(priority);

      // set QueryByParameter in ControlActProcess object
      controlActProcess.setQueryByParameter(factory.createQUPCIN043100UV01QUQIMT020001UV01ControlActProcessQueryByParameter(createQueryParams(payload)));

      return controlActProcess;
   }

   private QUPCIN043100UV01QUQIMT020001UV01QueryByParameter createQueryParams(CareRecordPayloadType payload) {
      QUPCIN043100UV01QUQIMT020001UV01QueryByParameter queryParams = new QUPCIN043100UV01QUQIMT020001UV01QueryByParameter();

      // set query id
      II id = new II();
      id.setExtension(Utils.generateDocumentId());
      id.setRoot(homeOid);
      queryParams.setQueryId(id);

      // set status
      CS statusCode = new CS();
      statusCode.setCode("new");
      queryParams.setStatusCode(statusCode);

      QUPCMT040300UV01ParameterList paramList = new QUPCMT040300UV01ParameterList();

      // set subject id
      QUPCMT040300UV01PatientId patientID = new QUPCMT040300UV01PatientId();
      II subjectId = new II();
      subjectId.setExtension(payload.getPatientId());
      patientID.setValue(subjectId);
      paramList.setPatientId(patientID);

      // set provision code
      QUPCMT040300UV01CareProvisionCode careCode = new QUPCMT040300UV01CareProvisionCode();
      CD code = new CD();
      code.setCode(payload.getCareProvisionCode());
      code.setCodeSystem(CARE_PROVISION_CODE_SYSOID);
      code.setCodeSystemName(CARE_PROVISION_CODE_SYSNAME);
      careCode.setValue(code);
      paramList.setCareProvisionCode(factory.createQUPCMT040300UV01ParameterListCareProvisionCode(careCode));

      // set care start date and time
      boolean setCareDTM = false;
      IVLTSExplicit careTime = new IVLTSExplicit();
      if (payload.getCareRecordStartTimePeriod() != null && !payload.getCareRecordStartTimePeriod().isEmpty()) {
         IVXBTSExplicit lowValue = new IVXBTSExplicit();
         lowValue.setValue(payload.getCareRecordStartTimePeriod());
         careTime.getContent().add(factory.createIVLTSExplicitLow(lowValue));
         setCareDTM = true;
      }

      // set care end date and time
      if (payload.getCareRecordEndTimePeriod() != null && !payload.getCareRecordEndTimePeriod().isEmpty()) {
         IVXBTSExplicit hiValue = new IVXBTSExplicit();
         hiValue.setValue(payload.getCareRecordEndTimePeriod());
         careTime.getContent().add(factory.createIVLTSExplicitHigh(hiValue));
         setCareDTM = true;
      }

      if (setCareDTM) {
         QUPCMT040300UV01CareRecordTimePeriod careRecordDTM = new QUPCMT040300UV01CareRecordTimePeriod();
         careRecordDTM.setValue(careTime);
         paramList.setCareRecordTimePeriod(factory.createQUPCMT040300UV01ParameterListCareRecordTimePeriod(careRecordDTM));
      }

      queryParams.getParameterList().add(paramList);

      return queryParams;
   }
}
