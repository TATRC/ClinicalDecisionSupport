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
import org.hl7.v3.II;
import org.hl7.v3.PRPAIN201307UV02MCCIMT000100UV01Message;
import org.hl7.v3.PRPAIN201307UV02QUQIMT021001UV01ControlActProcess;
import org.hl7.v3.PRPAMT201307UVParameterList;
import org.hl7.v3.PRPAMT201307UVPatientIdentifier;
import org.hl7.v3.PRPAMT201307UVQueryByParameter;
import org.hl7.v3.PatientDemographicsPRPAIN201307UV02RequestType;
import org.hl7.v3.TSExplicit;
import org.hl7.v3.XActMoodIntentEvent;
import org.hl7.v3.ObjectFactory;

/**
 *
 * @author kim
 */
public class PatientInfoRequestHelper extends RequestHelper {

  public final static String PATIENT_INFO_INTERACTION = "PRPA_IN201307UV02";
  public final static String PATIENT_INFO_QUERY_TRIGGER = "PRPA_TE201307UV02";

  public PatientInfoRequestHelper(Properties properties, ObjectFactory hl7Factory) {
    super(properties, hl7Factory);
  }

  public PatientDemographicsPRPAIN201307UV02RequestType createRequest(FactQueryRequestType request) throws InvalidParameterException {

    // check interaction id
    if (request.getInteractionId() == null || request.getInteractionId().isEmpty() ||
            !request.getInteractionId().equalsIgnoreCase(PATIENT_INFO_INTERACTION)) {
      throw new InvalidParameterException("Interaction ID is invalid, expected " + PATIENT_INFO_INTERACTION + ", received " + request.getInteractionId());
    }

    // check interaction id
    if (request.getTriggerEventCode() == null || request.getTriggerEventCode().isEmpty() ||
            !request.getTriggerEventCode().equalsIgnoreCase(PATIENT_INFO_QUERY_TRIGGER)) {
      throw new InvalidParameterException("Trigger Evenet is invalid, expected " + PATIENT_INFO_QUERY_TRIGGER + ", received " + request.getTriggerEventCode());
    }

    // check payload
    if (request.getCareRecordPayload() == null) {
      throw new InvalidParameterException("Missing required payload!");
    }

    if (request.getCareRecordPayload().getPatientId() == null ||
            request.getCareRecordPayload().getPatientId().isEmpty()) {
      throw new InvalidParameterException("Missing required patient in payload!");
    }

    PatientDemographicsPRPAIN201307UV02RequestType msg = new PatientDemographicsPRPAIN201307UV02RequestType();

    msg.setLocalDeviceId(homeOid);
    msg.setReceiverOID(homeOid);
    msg.setSenderOID(homeOid);
    msg.setQuery(PRPAIN201307UV02(request.getSenderId(), request.getCareRecordPayload()));

    return msg;
  }

  public PRPAIN201307UV02MCCIMT000100UV01Message PRPAIN201307UV02(String senderId, CareRecordPayloadType payload) {
    PRPAIN201307UV02MCCIMT000100UV01Message query = new PRPAIN201307UV02MCCIMT000100UV01Message();

    query.setId(setMessageId());

    TSExplicit creationTime = new TSExplicit();
    creationTime.setValue(DateUtils.convertToCDATime(new Date()));
    query.setCreationTime(creationTime);

    query.setInteractionId(setInteractionId(PATIENT_INFO_INTERACTION));

    query.setProcessingCode(setCSCode(PROCESSING_CODE));

    query.setProcessingModeCode(setCSCode(PROCESSING_MOOD_CODE));

    query.setAcceptAckCode(setCSCode(ACCEPT_ACK_CODE));

    // Set the receiver and sender
    query.getReceiver().add(createReceiver());
    if (senderId != null && !senderId.isEmpty()) {
      query.setSender(createSender(senderId));
    } else {
      query.setSender(createSender(homeOid));
    }

    query.setControlActProcess(createControlActProcess(payload.getPatientId()));

    return query;
  }

  private PRPAIN201307UV02QUQIMT021001UV01ControlActProcess createControlActProcess(String patientId) {
    PRPAIN201307UV02QUQIMT021001UV01ControlActProcess controlActProcess = new PRPAIN201307UV02QUQIMT021001UV01ControlActProcess();
    controlActProcess.setClassCode(ActClassControlAct.CACT);
    controlActProcess.setMoodCode(XActMoodIntentEvent.EVN);

    CD triggerCode = new CD();
    triggerCode.setCode(PATIENT_INFO_QUERY_TRIGGER);
    //triggerCode.setCodeSystem("2.16.840.1.113883.1.6");
    controlActProcess.setCode(triggerCode);

    CE priority = new CE();
    priority.setCode("R");
    controlActProcess.getPriorityCode().add(priority);

    II subjectId = new II();
    subjectId.setExtension(patientId);
    controlActProcess.setQueryByParameter(factory.createPRPAIN201307UV02QUQIMT021001UV01ControlActProcessQueryByParameter(createQueryParams(subjectId)));

    return controlActProcess;
  }

  private PRPAMT201307UVQueryByParameter createQueryParams(II subjectId) {
    PRPAMT201307UVQueryByParameter params = new PRPAMT201307UVQueryByParameter();

    // set query id
    II id = new II();
    id.setRoot(homeOid);
    id.setExtension(Utils.generateDocumentId());
    params.setQueryId(id);

    // set status
    CS statusCode = new CS();
    statusCode.setCode("new");
    params.setStatusCode(statusCode);

    PRPAMT201307UVParameterList paramList = new PRPAMT201307UVParameterList();

    // Set subject Id
    PRPAMT201307UVPatientIdentifier patientId = new PRPAMT201307UVPatientIdentifier();
    patientId.getValue().add(subjectId);
    paramList.getPatientIdentifier().add(patientId);

    params.setParameterList(paramList);

    return params;
  }
}
