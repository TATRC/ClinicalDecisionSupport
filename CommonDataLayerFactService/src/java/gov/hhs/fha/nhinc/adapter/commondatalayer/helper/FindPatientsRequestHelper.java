/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.helper;

import gov.hhs.fha.nhinc.adapter.commondatalayer.client.InvalidParameterException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.DateUtils;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.Utils;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.PatientSearchPayloadType;
import java.util.Date;
import java.util.Properties;
import org.hl7.v3.ActClassControlAct;
import org.hl7.v3.CD;
import org.hl7.v3.CE;
import org.hl7.v3.CS;
import org.hl7.v3.ENExplicit;
import org.hl7.v3.EnExplicitFamily;
import org.hl7.v3.EnExplicitGiven;
import org.hl7.v3.EnExplicitPrefix;
import org.hl7.v3.EnExplicitSuffix;
import org.hl7.v3.FindPatientsPRPAIN201305UVRequestType;
import org.hl7.v3.II;
import org.hl7.v3.IVLTSExplicit;
import org.hl7.v3.PNExplicit;
import org.hl7.v3.PRPAIN201305UVMCCIMT000100UV01Message;
import org.hl7.v3.PRPAIN201305UVQUQIMT021001UV01ControlActProcess;
import org.hl7.v3.PRPAMT201306UVLivingSubjectAdministrativeGender;
import org.hl7.v3.PRPAMT201306UVLivingSubjectBirthTime;
import org.hl7.v3.PRPAMT201306UVLivingSubjectId;
import org.hl7.v3.PRPAMT201306UVLivingSubjectName;
import org.hl7.v3.PRPAMT201306UVMothersMaidenName;
import org.hl7.v3.PRPAMT201306UVParameterList;
import org.hl7.v3.PRPAMT201306UVQueryByParameter;
import org.hl7.v3.TSExplicit;
import org.hl7.v3.XActMoodIntentEvent;
import org.hl7.v3.ObjectFactory;

/**
 *
 * @author kim
 */
public class FindPatientsRequestHelper extends RequestHelper {

   public final static String FIND_PATIENTS_INTERACTION = "PRPA_IN201305UV02";
   public final static String FIND_PATIENTS_QUERY_TRIGGER = "PRPA_TE201305UV02";

   public FindPatientsRequestHelper(Properties properties, ObjectFactory hl7Factory) {
      super(properties, hl7Factory);
   }

   public FindPatientsPRPAIN201305UVRequestType createRequest(FactQueryRequestType request) throws InvalidParameterException {

      // check interaction id
      if (request.getInteractionId() == null || request.getInteractionId().isEmpty() ||
              !request.getInteractionId().equalsIgnoreCase(FIND_PATIENTS_INTERACTION)) {
         throw new InvalidParameterException("Interaction ID is invalid, expected " + FIND_PATIENTS_INTERACTION + ", received " + request.getInteractionId());
      }

      // check interaction id
      if (request.getTriggerEventCode() == null || request.getTriggerEventCode().isEmpty() ||
              !request.getTriggerEventCode().equalsIgnoreCase(FIND_PATIENTS_QUERY_TRIGGER)) {
         throw new InvalidParameterException("Trigger Evenet is invalid, expected " + FIND_PATIENTS_QUERY_TRIGGER + ", received " + request.getTriggerEventCode());
      }

      if (request.getPatientSearchPayload() == null) {
         throw new InvalidParameterException("Missing required payload!");
      }

      FindPatientsPRPAIN201305UVRequestType msg = new FindPatientsPRPAIN201305UVRequestType();

      msg.setQuery(buildPRPAIN201305(request));

      return msg;
   }

   public PRPAIN201305UVMCCIMT000100UV01Message buildPRPAIN201305(FactQueryRequestType request) {
      PRPAIN201305UVMCCIMT000100UV01Message query = new PRPAIN201305UVMCCIMT000100UV01Message();

      query.setId(setMessageId());

      TSExplicit creationTime = new TSExplicit();
      creationTime.setValue(DateUtils.convertToCDATime(new Date()));
      query.setCreationTime(creationTime);

      query.setInteractionId(setInteractionId(FIND_PATIENTS_INTERACTION));

      query.setProcessingCode(setCSCode(PROCESSING_CODE));

      query.setProcessingModeCode(setCSCode(PROCESSING_MOOD_CODE));

      query.setAcceptAckCode(setCSCode(ACCEPT_ACK_CODE));

      // Set the receiver and sender
      query.getReceiver().add(createReceiver());
      query.setSender(createSender(request.getSenderId()));

      query.setControlActProcess(createControlActProcess(request.getPatientSearchPayload()));

      return query;
   }

   private PRPAIN201305UVQUQIMT021001UV01ControlActProcess createControlActProcess(PatientSearchPayloadType payload) {
      PRPAIN201305UVQUQIMT021001UV01ControlActProcess controlActProcess = new PRPAIN201305UVQUQIMT021001UV01ControlActProcess();

      controlActProcess.setMoodCode(XActMoodIntentEvent.EVN);
      controlActProcess.setClassCode(ActClassControlAct.CACT);

      CD triggerCode = new CD();
      triggerCode.setCode(FIND_PATIENTS_QUERY_TRIGGER);
      //triggerCode.setCodeSystem("2.16.840.1.113883.1.6");
      controlActProcess.setCode(triggerCode);

      CE priority = new CE();
      priority.setCode("R");
      controlActProcess.getPriorityCode().add(priority);

      controlActProcess.setQueryByParameter(factory.createPRPAIN201305UVQUQIMT021001UV01ControlActProcessQueryByParameter(createQueryParams(payload)));

      return controlActProcess;
   }

   private PRPAMT201306UVQueryByParameter createQueryParams(PatientSearchPayloadType payload) {
      PRPAMT201306UVQueryByParameter params = new PRPAMT201306UVQueryByParameter();

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

      PRPAMT201306UVParameterList paramList = new PRPAMT201306UVParameterList();

      // set subject identifier
      if (payload.getId() != null && !payload.getId().getValue().isEmpty()) {
         PRPAMT201306UVLivingSubjectId subjectId = new PRPAMT201306UVLivingSubjectId();
         II idValue = new II();
         idValue.setExtension(payload.getId().getValue());
         idValue.setRoot(payload.getId().getCodeSystem());
         subjectId.getValue().add(idValue);
         paramList.getLivingSubjectId().add(subjectId);
      }

      // set subject name
      if (payload.getName() != null && !payload.getName().getFamilyName().isEmpty()) {
         PRPAMT201306UVLivingSubjectName name = new PRPAMT201306UVLivingSubjectName();
         ENExplicit nameValue = new ENExplicit();
         nameValue.getUse().add("L");

         EnExplicitFamily lastname = new EnExplicitFamily();
         lastname.setContent(payload.getName().getFamilyName());
         nameValue.getContent().add(factory.createENExplicitFamily(lastname));

         if (payload.getName().getFirstName() != null && !payload.getName().getFirstName().isEmpty()) {
            EnExplicitGiven firstname = new EnExplicitGiven();
            firstname.setContent(payload.getName().getFirstName());
            nameValue.getContent().add(factory.createENExplicitGiven(firstname));
         }

         if (payload.getName().getMiddleName() != null && !payload.getName().getMiddleName().isEmpty()) {
            EnExplicitGiven middlename = new EnExplicitGiven();
            middlename.setContent(payload.getName().getMiddleName());
            nameValue.getContent().add(factory.createENExplicitGiven(middlename));
         }

         if (payload.getName().getSuffix() != null && !payload.getName().getSuffix().isEmpty()) {
            EnExplicitSuffix sfx = new EnExplicitSuffix();
            sfx.setContent(payload.getName().getPrefix());
            nameValue.getContent().add(factory.createENExplicitSuffix(sfx));
         }

         if (payload.getName().getPrefix() != null && !payload.getName().getPrefix().isEmpty()) {
            EnExplicitPrefix pfx = new EnExplicitPrefix();
            pfx.setContent(payload.getName().getPrefix());
            nameValue.getContent().add(factory.createENExplicitPrefix(pfx));
         }

         name.getValue().add(nameValue);
         paramList.getLivingSubjectName().add(name);
      }

      // set subject gender
      if (payload.getGender() != null && !payload.getGender().isEmpty()) {
         PRPAMT201306UVLivingSubjectAdministrativeGender gender = new PRPAMT201306UVLivingSubjectAdministrativeGender();
         CE genderCode = new CE();
         genderCode.setCode(payload.getGender());
         gender.getValue().add(genderCode);
         paramList.getLivingSubjectAdministrativeGender().add(gender);
      }

      // set subject date of birth
      if (payload.getDateOfBirth() != null && !payload.getDateOfBirth().isEmpty()) {
         PRPAMT201306UVLivingSubjectBirthTime dob = new PRPAMT201306UVLivingSubjectBirthTime();
         IVLTSExplicit dobValue = new IVLTSExplicit();
         dobValue.setValue(payload.getDateOfBirth());
         paramList.getLivingSubjectBirthTime().add(dob);
      }

      // set mother maiden name
      if (payload.getMotherMaidenName() != null && !payload.getMotherMaidenName().isEmpty()) {
         PRPAMT201306UVMothersMaidenName maidenName = new PRPAMT201306UVMothersMaidenName();
         PNExplicit maidenNameValue = new PNExplicit();
         maidenNameValue.getUse().add("L");

         EnExplicitFamily lastname = new EnExplicitFamily();
         lastname.setContent(payload.getMotherMaidenName());
         maidenNameValue.getContent().add(factory.createENExplicitFamily(lastname));
         paramList.getMothersMaidenName().add(maidenName);
      }

      params.setParameterList(paramList);

      return params;
   }
}
