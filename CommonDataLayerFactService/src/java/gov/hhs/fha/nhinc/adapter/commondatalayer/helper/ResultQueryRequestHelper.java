/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.helper;

import gov.hhs.fha.nhinc.adapter.commondatalayer.client.InvalidParameterException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.Utils;
import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.NameFactType;
import gov.hhs.fha.nhinc.adapter.fact.ResultEventPayloadType;
import java.util.Date;
import java.util.Properties;
import org.hl7.v3.ActClassControlAct;
import org.hl7.v3.CD;
import org.hl7.v3.CE;
import org.hl7.v3.CS;
import org.hl7.v3.CV;
import org.hl7.v3.EnExplicitFamily;
import org.hl7.v3.EnExplicitGiven;
import org.hl7.v3.EnExplicitPrefix;
import org.hl7.v3.EnExplicitSuffix;
import org.hl7.v3.II;
import org.hl7.v3.IVLTS;
import org.hl7.v3.IVXBTS;
import org.hl7.v3.PNExplicit;
import org.hl7.v3.POLBIN354000UV01MCCIMT000100UV01Message;
import org.hl7.v3.POLBIN354000UV01QUQIMT020001UV01ControlActProcess;
import org.hl7.v3.POLBIN354000UV01QUQIMT020001UV01QueryByParameter;
import org.hl7.v3.POLBMT300000UV01ActId;
import org.hl7.v3.POLBMT300000UV01ActMoodCode;
import org.hl7.v3.POLBMT300000UV01ObservationEffectiveTime;
import org.hl7.v3.POLBMT300000UV01ObservationStatusCode;
import org.hl7.v3.POLBMT300000UV01ObservationType;
import org.hl7.v3.POLBMT300000UV01PatientAdministrativeGender;
import org.hl7.v3.POLBMT300000UV01PatientDOB;
import org.hl7.v3.POLBMT300000UV01PatientID;
import org.hl7.v3.POLBMT300000UV01PatientName;
import org.hl7.v3.POLBMT300000UV01QueryByParameterPayload;
import org.hl7.v3.ResultQueryPOLBIN354000UV01MessageType;
import org.hl7.v3.XActMoodIntentEvent;
import org.hl7.v3.ObjectFactory;

/**
 * This
 * @author kim
 */
public class ResultQueryRequestHelper extends RequestHelper {

   public final static String RESULT_QUERY_INTERACTION_ID = "POLB_IN543000UV";
   public final static String RESULT_QUERY_TRIGGER = "QUPC_TE043100UV";

   public ResultQueryRequestHelper(Properties properties, ObjectFactory hl7Factory) {
      super(properties, hl7Factory);
   }

   public ResultQueryPOLBIN354000UV01MessageType createRequest(FactQueryRequestType request) throws InvalidParameterException {
      ResultQueryPOLBIN354000UV01MessageType msg = new ResultQueryPOLBIN354000UV01MessageType();

      // check interaction id
      if (request.getInteractionId() == null || request.getInteractionId().isEmpty() ||
              !request.getInteractionId().equalsIgnoreCase(RESULT_QUERY_INTERACTION_ID)) {
         throw new InvalidParameterException("Interaction ID is invalid, expected " + RESULT_QUERY_INTERACTION_ID + ", received " + request.getInteractionId());
      }

      // check interaction id
      if (request.getTriggerEventCode() == null || request.getTriggerEventCode().isEmpty() ||
              !request.getTriggerEventCode().equalsIgnoreCase(RESULT_QUERY_TRIGGER)) {
         throw new InvalidParameterException("Trigger Evenet is invalid, expected " + RESULT_QUERY_TRIGGER + ", received " + request.getTriggerEventCode());
      }

      if (request.getResultEventPayload() == null) {
         throw new InvalidParameterException("Missing required payload!");
      }

      if (request.getResultEventPayload().getPatientId() == null ||
              request.getResultEventPayload().getPatientId().isEmpty()) {
         throw new InvalidParameterException("Missing required patient in payload!");
      }

      msg.setQuery(buildPOLBIN354000U01(request.getSenderId(), request.getResultEventPayload()));

      return msg;
   }

   private POLBIN354000UV01MCCIMT000100UV01Message buildPOLBIN354000U01(String senderId, ResultEventPayloadType payload) throws InvalidParameterException {
      POLBIN354000UV01MCCIMT000100UV01Message query = new POLBIN354000UV01MCCIMT000100UV01Message();

      query.setId(setMessageId());

      query.setCreationTime(setMessageTimestamp(new Date()));

      query.setInteractionId(setInteractionId(RESULT_QUERY_INTERACTION_ID));

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

   private POLBIN354000UV01QUQIMT020001UV01ControlActProcess createControlActProcess(ResultEventPayloadType payload) throws InvalidParameterException {
      POLBIN354000UV01QUQIMT020001UV01ControlActProcess controlActProcess =
              new POLBIN354000UV01QUQIMT020001UV01ControlActProcess();

      controlActProcess.setMoodCode(XActMoodIntentEvent.EVN);
      controlActProcess.setClassCode(ActClassControlAct.CACT);

      II id = new II();
      id.setRoot(homeOid);
      id.setExtension(Utils.generateDocumentId());
      controlActProcess.getId().add(id);

      CD triggerCode = new CD();
      triggerCode.setCode(RESULT_QUERY_TRIGGER);
      controlActProcess.setCode(triggerCode);

      CE priority = new CE();
      priority.setCode("R");
      controlActProcess.getPriorityCode().add(priority);

      // set QueryByParameter in ControlActProcess object
      controlActProcess.setQueryByParameter(factory.createPOLBIN354000UV01QUQIMT020001UV01ControlActProcessQueryByParameter(createQueryParams(payload)));

      return controlActProcess;
   }

   private POLBIN354000UV01QUQIMT020001UV01QueryByParameter createQueryParams(ResultEventPayloadType payload) throws InvalidParameterException {
      POLBIN354000UV01QUQIMT020001UV01QueryByParameter queryParams = new POLBIN354000UV01QUQIMT020001UV01QueryByParameter();

      II id = new II();
      id.setExtension(Utils.generateDocumentId());
      id.setRoot(homeOid);
      queryParams.setQueryId(id);

      CS statusCode = new CS();
      statusCode.setCode("new");
      queryParams.setStatusCode(statusCode);

      POLBMT300000UV01QueryByParameterPayload paramPayload = createQueryByParameterPayload(payload);

      queryParams.getQueryByParameterPayload().add(paramPayload);

      return queryParams;
   }

   private POLBMT300000UV01ActId createActId(String value) {
      POLBMT300000UV01ActId event = new POLBMT300000UV01ActId();

      II id = new II();
      id.setExtension(value);
      event.setValue(id);

      return event;
   }

   private POLBMT300000UV01PatientID createPatientID(String value) {
      POLBMT300000UV01PatientID patientID = new POLBMT300000UV01PatientID();

      II id = new II();
      id.setExtension(value);
      patientID.getValue().add(id);

      return patientID;
   }

   private POLBMT300000UV01PatientName createPatientName(NameFactType value) {
      POLBMT300000UV01PatientName patientName = new POLBMT300000UV01PatientName();

      PNExplicit name = new PNExplicit();
      name.getUse().add("L");

      EnExplicitFamily lastname = new EnExplicitFamily();
      lastname.setContent(value.getFamilyName());
      name.getContent().add(factory.createENExplicitFamily(lastname));

      EnExplicitGiven firstname = new EnExplicitGiven();
      firstname.setContent(value.getFirstName());
      name.getContent().add(factory.createENExplicitGiven(firstname));

      if (value.getMiddleName() != null && !value.getMiddleName().isEmpty()) {
         EnExplicitGiven middlename = new EnExplicitGiven();
         middlename.setContent(value.getMiddleName());
         name.getContent().add(factory.createENExplicitGiven(middlename));
      }

      if (value.getSuffix() != null && !value.getSuffix().isEmpty()) {
         EnExplicitSuffix sfx = new EnExplicitSuffix();
         sfx.setContent(value.getPrefix());
         name.getContent().add(factory.createENExplicitSuffix(sfx));
      }

      if (value.getPrefix() != null && !value.getPrefix().isEmpty()) {
         EnExplicitPrefix pfx = new EnExplicitPrefix();
         pfx.setContent(value.getPrefix());
         name.getContent().add(factory.createENExplicitPrefix(pfx));
      }

      patientName.getValue().add(name);

      return patientName;
   }

   private POLBMT300000UV01PatientAdministrativeGender createPatientGender(CodeLabelPair value) {
      POLBMT300000UV01PatientAdministrativeGender gender = new POLBMT300000UV01PatientAdministrativeGender();

      CE code = new CE();
      code.setCode(value.getCode());
      code.setDisplayName(value.getLabel());

      gender.setValue(code);

      return gender;
   }

   private POLBMT300000UV01PatientDOB createPatientDOB(String value) {
      POLBMT300000UV01PatientDOB patientDOB = new POLBMT300000UV01PatientDOB();

      IVLTS dob = new IVLTS();
      dob.setValue(value);

      return patientDOB;
   }

   private POLBMT300000UV01ObservationStatusCode createObsStatusCode(String value) {
      POLBMT300000UV01ObservationStatusCode obsStatusCode = new POLBMT300000UV01ObservationStatusCode();

      CV code = new CV();
      code.setCode(value);

      obsStatusCode.setValue(null);

      return obsStatusCode;
   }

   private POLBMT300000UV01ObservationType createObsType(CodeLabelPair value) {
      POLBMT300000UV01ObservationType obsType = new POLBMT300000UV01ObservationType();

      CD code = new CD();
      code.setCode(value.getCode());
      if (value.getCodeSystem() != null && !value.getCodeSystem().isEmpty())
      code.setCodeSystem(value.getCodeSystem());
      if (value.getCodeSystemName() != null && !value.getCodeSystemName().isEmpty())
         code.setCodeSystemName(value.getCodeSystemName());
      if (value.getLabel() != null && !value.getLabel().isEmpty())
         code.setDisplayName(value.getLabel());
      
      return obsType;
   }

   private POLBMT300000UV01ObservationEffectiveTime createObsInterval(String startTime, String endTime) {
      POLBMT300000UV01ObservationEffectiveTime obsInterval = new POLBMT300000UV01ObservationEffectiveTime();

      IVLTS interval = new IVLTS();

      IVXBTS intervalStartTime = new IVXBTS();
      if (!startTime.isEmpty()) {
         intervalStartTime.setValue(startTime);
      }
      else {
         intervalStartTime.getNullFlavor().add("UNK");
      }
      interval.getRest().add(factory.createIVLTSLow(intervalStartTime));

      IVXBTS intervalEndTime = new IVXBTS();
      if (!endTime.isEmpty()) {
         intervalEndTime.setValue(endTime);
      }
      else {
         intervalEndTime.getNullFlavor().add("UNK");
      }
      interval.getRest().add(factory.createIVLTSHigh(intervalEndTime));
      
      obsInterval.getValue().add(interval);

      return obsInterval;
   }

   private POLBMT300000UV01QueryByParameterPayload createQueryByParameterPayload(ResultEventPayloadType requestPayload) throws InvalidParameterException {
      POLBMT300000UV01QueryByParameterPayload paramList = new POLBMT300000UV01QueryByParameterPayload();

      boolean primaryCriteria = false;

      // set status code
      CS statusCode = new CS();
      statusCode.setCode("executing");
      statusCode.setCodeSystem("2.16.840.1.113883.5.103");
      statusCode.setCodeSystemName("QueryStatusCode");
      paramList.setStatusCode(statusCode);

      // set act mood code
      POLBMT300000UV01ActMoodCode actMoodCode = new POLBMT300000UV01ActMoodCode();
      CV moodCode = new CV();
      moodCode.setCode("EVN");
      actMoodCode.setValue(moodCode);
      paramList.setActMoodCode(actMoodCode);

      // set patient identifier
      paramList.getPatientID().add(createPatientID(requestPayload.getPatientId()));

      // event id provided -- create a query for event(s) result -- default query
      if (requestPayload.getActId() != null && requestPayload.getActId().size() > 0) {
         // set primary criteria
         for (int i = 0; i < requestPayload.getActId().size(); i++) {
            String eventId = requestPayload.getActId().get(i);
            if (eventId != null || !eventId.isEmpty()) {
               paramList.getActId().add(createActId(eventId));
               primaryCriteria = true;
            }
         }
      }

      if (requestPayload.getObservationType() != null &&
          requestPayload.getObservationType().getCode() != null &&
          !requestPayload.getObservationType().getCode().isEmpty()) {
        // set primary criteria
        paramList.getObservationType().add(createObsType(requestPayload.getObservationType()));
        primaryCriteria = true;
      }

      if (!primaryCriteria)
        throw new InvalidParameterException("Missing result event identifier and/or result type!");

      // set secondary criteria/validation and/or filter
      if (primaryCriteria) {
         // patient name
         if (requestPayload.getPatientName() != null) {
            paramList.getPatientName().add(createPatientName(requestPayload.getPatientName()));
         }

         // patient gender
         if (requestPayload.getPatientGender() != null &&
                 requestPayload.getPatientGender().getCode() != null &&
                 !requestPayload.getPatientGender().getCode().isEmpty()) {
            paramList.setPatientAdministrativeGender(
                    factory.createPOLBMT300000UV01QueryByParameterPayloadPatientAdministrativeGender(
                    createPatientGender(requestPayload.getPatientGender())));
         }

         // patient date of birth
         if (requestPayload.getPatientDOB() != null && !requestPayload.getPatientDOB().isEmpty()) {
            paramList.setPatientDOB(
                    factory.createPOLBMT300000UV01QueryByParameterPayloadPatientDOB(
                    createPatientDOB(requestPayload.getPatientDOB())));
         }

         // patient observation status
         if (requestPayload.getObservationStatus() != null && !requestPayload.getObservationStatus().isEmpty()) {
            paramList.getObservationStatusCode().add(createObsStatusCode(requestPayload.getObservationStatus()));
         }

         // observation EffectiveTime
         if (requestPayload.getObservationStartTimePeriod() != null || requestPayload.getObservationStartTimePeriod() != null) {
            paramList.getObservationEffectiveTime().add(createObsInterval(requestPayload.getObservationStartTimePeriod(), requestPayload.getObservationStartTimePeriod()));
         }
      }

      return paramList;
   }
}
