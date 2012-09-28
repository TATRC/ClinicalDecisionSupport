/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.helper;

import gov.hhs.fha.nhinc.adapter.commondatalayer.client.InvalidParameterException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.Utils;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.ScheduleSearchPayloadType;
import java.util.Date;
import java.util.Properties;
import org.hl7.v3.ActClassControlAct;
import org.hl7.v3.ActMoodAppointmentRequest;
import org.hl7.v3.ActMoodResourceSlot;
import org.hl7.v3.ActRelationshipHasComponent;
import org.hl7.v3.ActRelationshipHasSubject;
import org.hl7.v3.ActRelationshipRefersTo;
import org.hl7.v3.CD;
import org.hl7.v3.CDExplicit;
import org.hl7.v3.CE;
import org.hl7.v3.CS;
import org.hl7.v3.EDExplicit;
import org.hl7.v3.ENExplicit;
import org.hl7.v3.EntityClassPerson;
import org.hl7.v3.EntityClassPlace;
import org.hl7.v3.II;
import org.hl7.v3.XActMoodIntentEvent;
import org.hl7.v3.ObjectFactory;
import org.hl7.v3.PRSCIN030101UVMCAIMT700201UV01ControlActProcess;
import org.hl7.v3.PRSCIN030101UVMCAIMT700201UV01Subject2;
import org.hl7.v3.PRSCIN030101UVMCCIMT000100UV01Message;
import org.hl7.v3.PRSCMT050000UVActAppointmentRequest;
import org.hl7.v3.PRSCMT050000UVComponent2;
import org.hl7.v3.PRSCMT050000UVDirectTarget;
import org.hl7.v3.PRSCMT050000UVIdentifiedEntity;
import org.hl7.v3.PRSCMT050000UVPerson;
import org.hl7.v3.PRSCMT050000UVPlace;
import org.hl7.v3.PRSCMT050000UVReference;
import org.hl7.v3.PRSCMT050000UVResourceSlot;
import org.hl7.v3.PRSCMT050000UVSchedule;
import org.hl7.v3.RoleClassIdentifiedEntity;
import org.hl7.v3.SXCMTS;
import org.hl7.v3.SlotRequestPRSCIN030101UVMessageType;

/**
 * This
 * @author kim
 */
public class SlotQueryRequestHelper extends RequestHelper {

   public final static String SCHEDULE_QUERY_INTERACTION_ID = "PRSC_IN030101UV";
   public final static String SCHEDULE_QUERY_TRIGGER = "PRSC_TE030100UV";

   public SlotQueryRequestHelper(Properties properties, ObjectFactory hl7Factory) {
      super(properties, hl7Factory);
   }

   public SlotRequestPRSCIN030101UVMessageType createRequest(FactQueryRequestType request) throws InvalidParameterException {
      SlotRequestPRSCIN030101UVMessageType msg = new SlotRequestPRSCIN030101UVMessageType();

      // check interaction id
      if (request.getInteractionId() == null || request.getInteractionId().isEmpty() ||
              !request.getInteractionId().equalsIgnoreCase(SCHEDULE_QUERY_INTERACTION_ID)) {
         throw new InvalidParameterException("Interaction ID is invalid, expected " + SCHEDULE_QUERY_INTERACTION_ID + ", received " + request.getInteractionId());
      }

      // check interaction id
      if (request.getTriggerEventCode() == null || request.getTriggerEventCode().isEmpty() ||
              !request.getTriggerEventCode().equalsIgnoreCase(SCHEDULE_QUERY_TRIGGER)) {
         throw new InvalidParameterException("Trigger Evenet is invalid, expected " + SCHEDULE_QUERY_TRIGGER + ", received " + request.getTriggerEventCode());
      }

      if (request.getScheduleSearchPayload() == null) {
         throw new InvalidParameterException("Missing required payload!");
      }

      if (request.getScheduleSearchPayload().getSlotType() == null ||
              request.getScheduleSearchPayload().getSlotType().isEmpty()) {
         throw new InvalidParameterException("Missing required slotTyope parameter!");
      }

      if (request.getScheduleSearchPayload().getSdlcId() == null &&
          request.getScheduleSearchPayload().getSdlcId().isEmpty()) {
        throw new InvalidParameterException("Missing required service delivery location (sdlcId)!");
      }

      if (request.getScheduleSearchPayload().getScheduleStartDate() == null &&
          request.getScheduleSearchPayload().getScheduleEndDate() == null &&
          request.getScheduleSearchPayload().getScheduleStartDate().isEmpty() &&
          request.getScheduleSearchPayload().getScheduleEndDate().isEmpty()) {
        throw new InvalidParameterException("Mising date ranges for schedule search!");
      }
      
      msg.setRequest(buildPRSCIN030101(request.getSenderId(), request.getScheduleSearchPayload()));

      return msg;
   }

   private PRSCIN030101UVMCCIMT000100UV01Message buildPRSCIN030101(String senderId, ScheduleSearchPayloadType payload) throws InvalidParameterException {
      PRSCIN030101UVMCCIMT000100UV01Message query = new PRSCIN030101UVMCCIMT000100UV01Message();

      query.setId(setMessageId());

      query.setCreationTime(setMessageTimestamp(new Date()));

      query.setInteractionId(setInteractionId(SCHEDULE_QUERY_INTERACTION_ID));

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

   private PRSCIN030101UVMCAIMT700201UV01ControlActProcess createControlActProcess(ScheduleSearchPayloadType payload) throws InvalidParameterException {
      PRSCIN030101UVMCAIMT700201UV01ControlActProcess controlActProcess =
              new PRSCIN030101UVMCAIMT700201UV01ControlActProcess();

      controlActProcess.setMoodCode(XActMoodIntentEvent.EVN);
      controlActProcess.setClassCode(ActClassControlAct.CACT);

      II id = new II();
      id.setRoot(homeOid);
      id.setExtension(Utils.generateDocumentId());
      controlActProcess.getId().add(id);

      CD triggerCode = new CD();
      triggerCode.setCode(SCHEDULE_QUERY_TRIGGER);
      controlActProcess.setCode(triggerCode);

      CE priority = new CE();
      priority.setCode("R");
      controlActProcess.getPriorityCode().add(priority);

      PRSCIN030101UVMCAIMT700201UV01Subject2 subject = new PRSCIN030101UVMCAIMT700201UV01Subject2();
      subject.setTypeCode(ActRelationshipHasSubject.SUBJ);
      subject.setAppointmentRequest(createAppointmentRequest(payload));
      
      controlActProcess.getSubject().add(subject);          

      return controlActProcess;
   }

   private PRSCMT050000UVActAppointmentRequest createAppointmentRequest(ScheduleSearchPayloadType payload) throws InvalidParameterException {
      PRSCMT050000UVActAppointmentRequest apptRequest = new PRSCMT050000UVActAppointmentRequest();
      apptRequest.getClassCode().add("ACT");
      apptRequest.setMoodCode(ActMoodAppointmentRequest.ARQ);

      II id = new II();
      id.setExtension(Utils.generateDocumentId());
      id.setRoot(homeOid);
      apptRequest.setId(id);

      // search criteria:  appointment type
      CDExplicit apptTypeCode = new CDExplicit();
      if (payload.getAppointmentType() != null && !payload.getAppointmentType().isEmpty()) {
        apptTypeCode.setCode(payload.getAppointmentType());
      }
      else {
        apptTypeCode.setCode("UNK");
        EDExplicit apptTypeED = new EDExplicit();
        apptTypeED.getContent().add(payload.getAppointmentType());
        apptTypeCode.setOriginalText(apptTypeED);
      }
      apptRequest.setCode(apptTypeCode);

      // search criteria:  schedule info
      PRSCMT050000UVReference reference = new PRSCMT050000UVReference();
      reference.setTypeCode(ActRelationshipRefersTo.REFR);
      
      PRSCMT050000UVSchedule refSchedule = new PRSCMT050000UVSchedule();
      refSchedule.getClassCode().add("ACT");
      refSchedule.setMoodCode(ActMoodResourceSlot.SLOT);

      // have identifier for requesting schedule
      if (payload.getScheduleId() != null && !payload.getScheduleId().isEmpty()) {
        II scheduleId = new II();
        scheduleId.setExtension(payload.getScheduleId());
        refSchedule.setId(scheduleId);
      }

      // slot type
      CD slotTypeCode = new CD();
      slotTypeCode.setCode(payload.getSlotType());

      // set schedule date ranges
      SXCMTS scheduleStartDTM = new SXCMTS();
      scheduleStartDTM.setValue(payload.getScheduleStartDate());
      refSchedule.getEffectiveTime().add(scheduleStartDTM);
      SXCMTS scheduleEndDTM = new SXCMTS();
      scheduleEndDTM.setValue(payload.getScheduleEndDate());
      refSchedule.getEffectiveTime().add(scheduleEndDTM);

      // set service delivery location info
      if (payload.getSdlcId() != null && !payload.getSdlcId().isEmpty()) {
        refSchedule.getComponent().add(
            createResource(
                createServiceDeliveryLocationResource(payload.getSdlcId(), payload.getSdlcName())));
      }
      else {
        throw new InvalidParameterException("Missing required service delivery location (sdlcId)!");
      }

      // set provider info if provided
      if (payload.getPerformerId() != null && !payload.getPerformerId().isEmpty()) {
        refSchedule.getComponent().add(
            createResource(
                createProviderResource(payload.getPerformerId(), payload.getPerformerName())));
      }
      
      reference.setSchedule(refSchedule);
      apptRequest.getReference().add(reference);

      return apptRequest;
   }

   private PRSCMT050000UVComponent2 createResource(PRSCMT050000UVIdentifiedEntity resource) {
      PRSCMT050000UVComponent2 component = new PRSCMT050000UVComponent2();
      component.setTypeCode(ActRelationshipHasComponent.COMP);

      PRSCMT050000UVResourceSlot resourceSlot = new PRSCMT050000UVResourceSlot();
      resourceSlot.getClassCode().add("ACT");
      resourceSlot.setMoodCode(ActMoodResourceSlot.SLOT);

      CS statusCode = new CS();
      statusCode.setCode("normal");
      resourceSlot.setStatusCode(statusCode);

      PRSCMT050000UVDirectTarget directResource = new PRSCMT050000UVDirectTarget();
      directResource.setTypeCode("DIR");
      directResource.setIdentifiedEntity(resource);

      resourceSlot.getDirectTarget().add(directResource);
      component.setResourceSlot(resourceSlot);
      
      return component;
   }

   /**
    * Construct a service delivery location resource object.
    * @param id   unique identifer of service delivery location
    * @param name name of service delivery location
    * @return PRSCMT050000UVIdentifiedEntity  service delivery location resource object
    */
   private PRSCMT050000UVIdentifiedEntity createServiceDeliveryLocationResource(String id, String name) {
    PRSCMT050000UVIdentifiedEntity resourceEntity = new PRSCMT050000UVIdentifiedEntity();
    resourceEntity.setClassCode(RoleClassIdentifiedEntity.IDENT);

    PRSCMT050000UVPlace place = new PRSCMT050000UVPlace();
    place.setClassCode(EntityClassPlace.PLC);

    II placeId = new II();
    placeId.setExtension(id);
    place.getId().add(placeId);

    if (name != null && !name.isEmpty()) {
      ENExplicit placeName = new ENExplicit();
      placeName.getContent().add(name);
      place.getName().add(placeName);
    }

    resourceEntity.setIdentifiedPlace(factory.createPRSCMT050000UVIdentifiedEntityIdentifiedPlace(place));

    return resourceEntity;
   }


   /**
    * Construct a service delivery location resource object.
    * @param id   unique identifer of service delivery location
    * @param name name of service delivery location
    * @return PRSCMT050000UVIdentifiedEntity  service delivery location resource object
    */
   private PRSCMT050000UVIdentifiedEntity createProviderResource(String id, String name) {
    PRSCMT050000UVIdentifiedEntity resourceEntity = new PRSCMT050000UVIdentifiedEntity();
    resourceEntity.setClassCode(RoleClassIdentifiedEntity.IDENT);

    PRSCMT050000UVPerson person = new PRSCMT050000UVPerson();
    person.setClassCode(EntityClassPerson.PSN);

    II personId = new II();
    personId.setExtension(id);
    person.getId().add(personId);

    if (name != null && !name.isEmpty()) {
      ENExplicit personName = new ENExplicit();
      personName.getContent().add(name);
      person.getName().add(personName);
    }

    resourceEntity.setIdentifiedPerson(factory.createPRSCMT050000UVIdentifiedEntityIdentifiedPerson(person));

    return resourceEntity;
   }
}
