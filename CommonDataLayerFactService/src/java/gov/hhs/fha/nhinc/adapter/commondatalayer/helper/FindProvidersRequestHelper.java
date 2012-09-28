/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.commondatalayer.helper;

import gov.hhs.fha.nhinc.adapter.commondatalayer.client.InvalidParameterException;
import gov.hhs.fha.nhinc.adapter.commondatalayer.util.Utils;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.ProviderSearchPayloadType;
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
import org.hl7.v3.II;
import org.hl7.v3.PRPMIN306010UV01MCCIMT000100UV01Message;
import org.hl7.v3.PRPMIN306010UV01QUQIMT021001UV01ControlActProcess;
import org.hl7.v3.PRPMMT306010UV01ProviderID;
import org.hl7.v3.PRPMMT306010UV01ProviderName;
import org.hl7.v3.PRPMMT306010UV01QueryByParameterPayload;
import org.hl7.v3.PRPMMT306010UV01RoleClass;
import org.hl7.v3.PRPMMT306010UV01RoleType;
import org.hl7.v3.PRPMMT306010UV01SdlcId;
import org.hl7.v3.PRPMMT306010UV01SdlcType;
import org.hl7.v3.ProviderDetailsPRPMIN306010UV1RequestType;
import org.hl7.v3.XActMoodIntentEvent;
import org.hl7.v3.ObjectFactory;

/**
 *
 * @author kim
 */
public class FindProvidersRequestHelper extends RequestHelper {

   public final static String FIND_PROVIDERS_INTERACTION = "PRPM_IN306010UV01";
   public final static String FIND_PROVIDERS_QUERY_TRIGGER = "PRPM_TE306010UV01";

   public FindProvidersRequestHelper(Properties properties, ObjectFactory hl7Factory) {
      super(properties, hl7Factory);
   }

   public ProviderDetailsPRPMIN306010UV1RequestType createRequest(FactQueryRequestType request) throws InvalidParameterException {

      // check interaction id
      if (request.getInteractionId() == null || request.getInteractionId().isEmpty() ||
              !request.getInteractionId().equalsIgnoreCase(FIND_PROVIDERS_INTERACTION)) {
         throw new InvalidParameterException("Interaction ID is invalid, expected " + FIND_PROVIDERS_INTERACTION + ", received " + request.getInteractionId());
      }

      // check interaction id
      if (request.getTriggerEventCode() == null || request.getTriggerEventCode().isEmpty() ||
              !request.getTriggerEventCode().equalsIgnoreCase(FIND_PROVIDERS_QUERY_TRIGGER)) {
         throw new InvalidParameterException("Trigger Evenet is invalid, expected " + FIND_PROVIDERS_QUERY_TRIGGER + ", received " + request.getTriggerEventCode());
      }

      if (request.getProviderSearchPayload() == null) {
         throw new InvalidParameterException("Missing required payload!");
      }

      ProviderDetailsPRPMIN306010UV1RequestType msg = new ProviderDetailsPRPMIN306010UV1RequestType();

      msg.setQuery(buildPRPMIN306010UV1(request));

      return msg;
   }

   public PRPMIN306010UV01MCCIMT000100UV01Message buildPRPMIN306010UV1(FactQueryRequestType request) {
      PRPMIN306010UV01MCCIMT000100UV01Message query = new PRPMIN306010UV01MCCIMT000100UV01Message();

      query.setId(setMessageId());

      query.setCreationTime(setMessageTimestamp(new Date()));

      query.setInteractionId(setInteractionId(FIND_PROVIDERS_INTERACTION));

      query.setProcessingCode(setCSCode(PROCESSING_CODE));

      query.setProcessingModeCode(setCSCode(PROCESSING_MOOD_CODE));

      query.setAcceptAckCode(setCSCode(ACCEPT_ACK_CODE));

      // Set the receiver and sender
      query.getReceiver().add(createReceiver());
      query.setSender(createSender(request.getSenderId()));

      query.setControlActProcess(createControlActProcess(request.getProviderSearchPayload()));

      return query;
   }

   private PRPMIN306010UV01QUQIMT021001UV01ControlActProcess createControlActProcess(ProviderSearchPayloadType payload) {
      PRPMIN306010UV01QUQIMT021001UV01ControlActProcess controlActProcess = new PRPMIN306010UV01QUQIMT021001UV01ControlActProcess();

      controlActProcess.setMoodCode(XActMoodIntentEvent.EVN);
      controlActProcess.setClassCode(ActClassControlAct.CACT);

      CD triggerCode = new CD();
      triggerCode.setCode(FIND_PROVIDERS_QUERY_TRIGGER);
      //triggerCode.setCodeSystem("2.16.840.1.113883.1.6");
      controlActProcess.setCode(triggerCode);

      CE priority = new CE();
      priority.setCode("R");
      controlActProcess.getPriorityCode().add(priority);

      controlActProcess.setQueryByParameterPayload(
         factory.createPRPMIN306010UV01QUQIMT021001UV01ControlActProcessQueryByParameterPayload(createQueryParams(payload)));

      return controlActProcess;
   }

   private PRPMMT306010UV01QueryByParameterPayload createQueryParams(ProviderSearchPayloadType payload) {
      PRPMMT306010UV01QueryByParameterPayload params = new PRPMMT306010UV01QueryByParameterPayload();

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

      // set subject identifier
      if (payload.getId() != null && !payload.getId().isEmpty()) {
         PRPMMT306010UV01ProviderID providerId = new PRPMMT306010UV01ProviderID();
         II idValue = new II();
         idValue.setExtension(payload.getId());
         idValue.setRoot(homeOid);
         providerId.setValue(idValue);
         params.getProviderID().add(providerId);
      }

      // set subject name
      if (payload.getName() != null && !payload.getName().getFamilyName().isEmpty()) {
         PRPMMT306010UV01ProviderName name = new PRPMMT306010UV01ProviderName();
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

         name.setValue(nameValue);
         params.getProviderName().add(name);
      }

      // set role class
      if (payload.getRoleClass() != null && !payload.getRoleClass().isEmpty()) {
         PRPMMT306010UV01RoleClass roleClass = new PRPMMT306010UV01RoleClass();
         CS roleClassValue = new CS();
         roleClassValue.setCode(payload.getRoleClass());
         roleClass.setValue(statusCode);
         params.setRoleClass(factory.createPRPMMT306010UV01QueryByParameterPayloadRoleClass(roleClass));
      }

      // set role type - Shall be coded with Healthcare Provider Taxonomy codes when using this element
      if (payload.getRoleType() != null &&
          payload.getRoleType().getCode() != null && !payload.getRoleType().getCode().isEmpty()) {
         PRPMMT306010UV01RoleType roleType = new PRPMMT306010UV01RoleType();
         CD roleTypeValue = new CD();
         roleTypeValue.setCode(payload.getRoleType().getCode());
         if (payload.getRoleType().getLabel() != null && !payload.getRoleType().getLabel().isEmpty())
            roleTypeValue.setDisplayName(payload.getRoleType().getLabel());
         if (payload.getRoleType().getCodeSystem() != null && !payload.getRoleType().getCodeSystem().isEmpty())
            roleTypeValue.setCodeSystem(payload.getRoleType().getCodeSystem());
         if (payload.getRoleType().getCodeSystemName() != null && !payload.getRoleType().getCodeSystemName().isEmpty())
            roleTypeValue.setCodeSystemName(payload.getRoleType().getCodeSystemName());
         roleType.setValue(roleTypeValue);
         params.getRoleType().add(roleType);
      }

      // set service location id
      if (payload.getServiceDeliveryLocld() != null && !payload.getServiceDeliveryLocld().isEmpty()) {
         PRPMMT306010UV01SdlcId sdlcId = new PRPMMT306010UV01SdlcId();
         II sdlcIdValue = new II();
         sdlcIdValue.setExtension(payload.getServiceDeliveryLocld());
         sdlcId.setValue(sdlcIdValue);
         params.getSdlcId().add(sdlcId);
      }

      // set service location name
      if (payload.getServiceDeliveryLocType() != null &&
          payload.getServiceDeliveryLocType().getCode() != null && !payload.getServiceDeliveryLocType().getCode().isEmpty()) {
         PRPMMT306010UV01SdlcType sdlcType = new PRPMMT306010UV01SdlcType();
         CD sdlcTypeValue = new CD();
         sdlcTypeValue.setCode(payload.getServiceDeliveryLocType().getCode());
         if (payload.getServiceDeliveryLocType().getLabel() != null && !payload.getServiceDeliveryLocType().getLabel().isEmpty())
            sdlcTypeValue.setDisplayName(payload.getRoleType().getLabel());
         if (payload.getServiceDeliveryLocType().getCodeSystem() != null && !payload.getServiceDeliveryLocType().getCodeSystem().isEmpty())
            sdlcTypeValue.setCodeSystem(payload.getRoleType().getCodeSystem());
         if (payload.getServiceDeliveryLocType().getCodeSystemName() != null && !payload.getServiceDeliveryLocType().getCodeSystemName().isEmpty())
            sdlcTypeValue.setCodeSystemName(payload.getRoleType().getCodeSystemName());
         sdlcType.setValue(sdlcTypeValue);
         params.getSdlcType().add(sdlcType);
      }

      return params;
   }
}
