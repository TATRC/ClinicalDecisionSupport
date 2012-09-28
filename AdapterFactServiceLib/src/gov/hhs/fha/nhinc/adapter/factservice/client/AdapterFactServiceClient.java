/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.factservice.client;

import gov.hhs.fha.nhinc.adapter.fact.AllergyFactType;
import gov.hhs.fha.nhinc.adapter.fact.CareRecordPayloadType;
import gov.hhs.fha.nhinc.adapter.fact.CodeLabelPair;
import gov.hhs.fha.nhinc.adapter.fact.EncounterSearchPayloadType;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType;
import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.ProblemFactType;
import gov.hhs.fha.nhinc.adapter.factservice.AdapterFactService;
import gov.hhs.fha.nhinc.adapter.factservice.AdapterFactService_Service;
import gov.hhs.fha.nhinc.adapter.factservice.FaultMessage;
import gov.hhs.fha.nhinc.kmr.util.CommonUtil;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author kim
 */
public class AdapterFactServiceClient {

   private static Log log = LogFactory.getLog(AdapterFactServiceClient.class);
   public static final String FACT_SERVICE_QNAME = "urn:gov:hhs:fha:nhinc:adapter:factservice";
   /**  Handle to Common Data Access Layer web services  */
   private AdapterFactService_Service factService = null;
   /**  Facts web services URL  */
   private String serviceEndpoint = null;
   /** CDA date formater  */
   private static DateFormat cdaDateFormat = null;

   public AdapterFactServiceClient(String serviceEndpoint) {
      initService(serviceEndpoint);
      cdaDateFormat = new SimpleDateFormat("yyyyMMddHHmmssZ"); // YYYYMMDDHHmmss-0000
   }

   private void initService(String serviceEndpoint) {
      if (serviceEndpoint != null && !serviceEndpoint.isEmpty()) {
         this.serviceEndpoint = serviceEndpoint;
         URL baseUrl = gov.hhs.fha.nhinc.adapter.factservice.AdapterFactService_Service.class.getResource(".");
         try {
            URL url = new URL(baseUrl, serviceEndpoint);
            factService = new AdapterFactService_Service(url, new QName(FACT_SERVICE_QNAME, "AdapterFactService"));
         } catch (MalformedURLException e) {
            log.error("Failed to create URL for the wsdl Location: " + serviceEndpoint);
         }
      } else {
         log.error("Failed to create URL for the wsdl Location: " + serviceEndpoint);
      }
   }

   public List<FactType> getDemographicsFact(String patientId) {
      log.debug("getDemographicsFact: params patientId[=" + patientId + "]");

      if (patientId != null && !patientId.isEmpty()) {
         try {
            // build payload
            CareRecordPayloadType payload = new CareRecordPayloadType();
            payload.setPatientId(patientId);
            //request.setRoot(homeCommunityId);
            //request.setAssigningAuthorityName(homeCommunityName);

            // call web services
            if (factService == null) {
               initService(serviceEndpoint);
            }

            AdapterFactService port = factService.getCommonDataLayerFactPort();
            FactQueryResponseType resp =
                    port.getDemographicsFact(createCareRecordQuery("PRPA_IN201307UV02", "PRPA_TE201307UV02", payload));

            return resp.getProblemFactOrMedicationFactOrAllergyFact();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      return new ArrayList<FactType>();
   }

   public List<FactType> getAllergyFacts(String patientId, String startDate, String endDate) {
      log.debug("getAllergyFacts: params patientId[=" + patientId + "] startDate=[" +
              startDate + "] endDate=[" + endDate + "]");

      if (patientId != null && !patientId.isEmpty()) {
         try {
            // build payload
            CareRecordPayloadType payload = new CareRecordPayloadType();
            payload.setPatientId(patientId);

            // set care provision code
            payload.setCareProvisionCode("INTOLIST");

            // set start and end date
            if (startDate != null && !startDate.isEmpty()) {
               payload.setCareRecordStartTimePeriod(startDate);
            }

            if (endDate != null && !endDate.isEmpty()) {
               payload.setCareRecordEndTimePeriod(endDate);
            } else {
               payload.setCareRecordEndTimePeriod(this.convertToCDATime(new Date()));
            }

            // call web services
            if (factService == null) {
               initService(serviceEndpoint);
            }

            AdapterFactService port = factService.getCommonDataLayerFactPort();
            FactQueryResponseType resp =
                    port.getAllergyFacts(createCareRecordQuery("QUPC_IN043100UV", "QUPC_TE043100UV01", payload));

            return resp.getProblemFactOrMedicationFactOrAllergyFact();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return new ArrayList<FactType>();
   }

   public List<FactType> getMedicationFacts(String patientId, String startDate, String endDate) {
      log.debug("getMedicationFacts: params patientId[=" + patientId + "] startDate=[" +
              startDate + "] endDate=[" + endDate + "]");

      if (patientId != null && !patientId.isEmpty()) {
         try {
            // build payload
            CareRecordPayloadType payload = new CareRecordPayloadType();
            payload.setPatientId(patientId);

            // set care provision code
            payload.setCareProvisionCode("HISTMEDLIST");

            // set start and end date
            if (startDate != null && !startDate.isEmpty()) {
               payload.setCareRecordStartTimePeriod(startDate);
            }

            if (endDate != null && !endDate.isEmpty()) {
               payload.setCareRecordEndTimePeriod(endDate);
            } else {
               payload.setCareRecordEndTimePeriod(this.convertToCDATime(new Date()));
            }

            // call web services
            if (factService == null) {
               initService(serviceEndpoint);
            }

            AdapterFactService port = factService.getCommonDataLayerFactPort();
            FactQueryResponseType resp =
                    port.getMedicationFacts(createCareRecordQuery("QUPC_IN043100UV", "QUPC_TE043100UV01", payload));

            return resp.getProblemFactOrMedicationFactOrAllergyFact();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return new ArrayList<FactType>();
   }

   /**
    * Retrieve problems of a patient within a specific date ranges.
    * @param patientId
    * @param startDate
    * @param endDate
    * @return
    */
   public List<FactType> getProblemFacts(String patientId, String startDate, String endDate) {
      log.debug("getProblemFacts: params patientId[=" + patientId + "] startDate=[" +
              startDate + "] endDate=[" + endDate + "]");

      if (patientId != null && !patientId.isEmpty()) {
         try {
            // build payload
            CareRecordPayloadType payload = new CareRecordPayloadType();
            payload.setPatientId(patientId);

            // set care provision code
            payload.setCareProvisionCode("PROBLIST");

            // set start and end date
            if (startDate != null && !startDate.isEmpty()) {
               payload.setCareRecordStartTimePeriod(startDate);
            }

            if (endDate != null && !endDate.isEmpty()) {
               payload.setCareRecordEndTimePeriod(endDate);
            } else {
               payload.setCareRecordEndTimePeriod(this.convertToCDATime(new Date()));
            }

            // call web services
            if (factService == null) {
               initService(serviceEndpoint);
            }

            AdapterFactService port = factService.getCommonDataLayerFactPort();
            FactQueryResponseType resp =
                    port.getProblemFacts(createCareRecordQuery("QUPC_IN043100UV", "QUPC_TE043100UV01", payload));

            return resp.getProblemFactOrMedicationFactOrAllergyFact();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return new ArrayList<FactType>();
   }

   public List<FactType> getTestResultFacts(String patientId, String startDate, String endDate) {
      log.debug("getTestResultFacts: params patientId[=" + patientId + "] startDate=[" +
              startDate + "] endDate=[" + endDate + "]");

      if (patientId != null && !patientId.isEmpty()) {
         try {
            // build payload
            CareRecordPayloadType payload = new CareRecordPayloadType();
            payload.setPatientId(patientId);

            // set care provision code
            payload.setCareProvisionCode("LABCAT");

            // set start and end date
            if (startDate != null && !startDate.isEmpty()) {
               payload.setCareRecordStartTimePeriod(startDate);
            }

            if (endDate != null && !endDate.isEmpty()) {
               payload.setCareRecordEndTimePeriod(endDate);
            } else {
               payload.setCareRecordEndTimePeriod(this.convertToCDATime(new Date()));
            }

            // call web services
            if (factService == null) {
               initService(serviceEndpoint);
            }

            AdapterFactService port = factService.getCommonDataLayerFactPort();
            FactQueryResponseType resp =
                    port.getTestResultFacts(createCareRecordQuery("QUPC_IN043100UV", "QUPC_TE043100UV01", payload));

            return resp.getProblemFactOrMedicationFactOrAllergyFact();
         } catch (FaultMessage fm) {
            log.error("getTestResultFacts: fault message=[" + fm.getMessage() + "]");
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return new ArrayList<FactType>();
   }

   /**
    * Retrieve procedures of a patient within a specific date ranges.
    * @param patientId
    * @param startDate
    * @param endDate
    * @return
    */
   public List<FactType> getProcedureFacts(String patientId, String startDate, String endDate) {
      log.debug("getProcedureFacts: params patientId=[" + patientId + "] startDate=[" +
              startDate + "] endDate=[" + endDate + "]");

      if (patientId != null && !patientId.isEmpty()) {
         try {
            // build payload
            CareRecordPayloadType payload = new CareRecordPayloadType();
            payload.setPatientId(patientId);

            // set care provision code
            payload.setCareProvisionCode("PSVCCAT");

            // set start and end date
            if (startDate != null && !startDate.isEmpty()) {
               payload.setCareRecordStartTimePeriod(startDate);
            }

            if (endDate != null && !endDate.isEmpty()) {
               payload.setCareRecordEndTimePeriod(endDate);
            } else {
               payload.setCareRecordEndTimePeriod(this.convertToCDATime(new Date()));
            }

            // call web services
            if (factService == null) {
               initService(serviceEndpoint);
            }

            AdapterFactService port = factService.getCommonDataLayerFactPort();
            FactQueryResponseType resp =
                    port.getProcedureFacts(createCareRecordQuery("QUPC_IN043100UV", "QUPC_TE043100UV01", payload));

            return resp.getProblemFactOrMedicationFactOrAllergyFact();
         } catch (FaultMessage fm) {
            log.error("getProcedureFacts: fault message=[" + fm.getMessage() + "]");
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return new ArrayList<FactType>();
   }

   /**
    * Retrieve vital signs data of a patient.
    * @param patientId
    * @param startDate
    * @param endDate
    * @return
    */
   public List<FactType> getVitalFacts(String patientId, String startDate, String endDate) {
      log.debug("getVitalFacts: params patientId[=" + patientId + "] startDate=[" +
              startDate + "] endDate=[" + endDate + "]");

      if (patientId != null && !patientId.isEmpty()) {
         try {
            // build payload
            CareRecordPayloadType payload = new CareRecordPayloadType();
            payload.setPatientId(patientId);

            // set care provision code
            payload.setCareProvisionCode("COBSCAT");

            // set start and end date
            if (startDate != null && !startDate.isEmpty()) {
               payload.setCareRecordStartTimePeriod(startDate);
            }

            if (endDate != null && !endDate.isEmpty()) {
               payload.setCareRecordEndTimePeriod(endDate);
            } else {
               payload.setCareRecordEndTimePeriod(this.convertToCDATime(new Date()));
            }

            // call web services
            if (factService == null) {
               initService(serviceEndpoint);
            }

            AdapterFactService port = factService.getCommonDataLayerFactPort();
            FactQueryResponseType resp =
                    port.getVitalsFacts(createCareRecordQuery("QUPC_IN043100UV", "QUPC_TE043100UV01", payload));

            return resp.getProblemFactOrMedicationFactOrAllergyFact();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return new ArrayList<FactType>();
   }

   public List<FactType> getImmunizationFacts(String patientId, String startDate, String endDate) {
      log.debug("getImmunizationFacts: params patientId[=" + patientId + "] startDate=[" +
              startDate + "] endDate=[" + endDate + "]");

      if (patientId != null && !patientId.isEmpty()) {
         try {
            // build payload
            CareRecordPayloadType payload = new CareRecordPayloadType();
            payload.setPatientId(patientId);

            // set care provision code
            payload.setCareProvisionCode("IMMUCAT");

            // set start and end date
            if (startDate != null && !startDate.isEmpty()) {
               payload.setCareRecordStartTimePeriod(startDate);
            }

            if (endDate != null && !endDate.isEmpty()) {
               payload.setCareRecordEndTimePeriod(endDate);
            } else {
               payload.setCareRecordEndTimePeriod(this.convertToCDATime(new Date()));
            }

            // call web services
            if (factService == null) {
               initService(serviceEndpoint);
            }

            AdapterFactService port = factService.getCommonDataLayerFactPort();
            FactQueryResponseType resp =
                    port.getImmunizationFacts(createCareRecordQuery("QUPC_IN043100UV", "QUPC_TE043100UV01", payload));

            return resp.getProblemFactOrMedicationFactOrAllergyFact();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return new ArrayList<FactType>();
   }

   /**
    * Rerieve appointment facts of a patient.
    * @param patientId
    * @param startDate
    * @param endDate
    * @param statusList
    * @return
    */
   public List<FactType> getAppointmentFacts(String patientId, String startDate, String endDate, List<String> statusList) {
      log.debug("getAppointmentFacts: params patientId=[" + patientId + "] startDate=[" +
              startDate + "] endDate=[" + endDate + "]");

      if (patientId != null && !patientId.isEmpty()) {
         try {
            // build payload
            EncounterSearchPayloadType payload = new EncounterSearchPayloadType();

            // set patient
            payload.setPatientId(patientId);

            // set type of encounter to outpatient
            payload.setTypeOfEncounter("AMB");

            // set start and end date
            if (startDate != null && !startDate.isEmpty()) {
               payload.setEncounterStartTimeFrame(startDate);
            }

            if (endDate != null && !endDate.isEmpty()) {
               payload.setEncounterEndTimeFrame(endDate);
            } else {
               payload.setEncounterEndTimeFrame(this.convertToCDATime(new Date()));
            }

            // set status: "active", "cancelled", "completed", "new"
            if (statusList != null && !statusList.isEmpty()) {
               for (String status : statusList) {
                  payload.getEncounterStatus().add(status);
               }
            } else {
               payload.getEncounterStatus().add("active");
            }

            // call web services
            if (factService == null) {
               initService(serviceEndpoint);
            }

            AdapterFactService port = factService.getCommonDataLayerFactPort();
            FactQueryResponseType resp =
                    port.getAppointmentFacts(createEncounterSearchQuery("REPC_IN040100UV", "REPC_TE040100UV", payload));

            return resp.getProblemFactOrMedicationFactOrAllergyFact();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return new ArrayList<FactType>();
   }

   /**
    * Rerieve appointment facts of a patient.
    * @param patientId
    * @param startDate
    * @param endDate
    * @param statusList
    * @return
    */
   public List<FactType> getAdmissionFacts(String patientId, String startDate, String endDate, List<String> statusList) {
      log.debug("getAdmissionFacts: params patientId=[" + patientId + "] startDate=[" +
              startDate + "] endDate=[" + endDate + "]");

      if (patientId != null && !patientId.isEmpty()) {
         try {
            // build payload
            EncounterSearchPayloadType payload = new EncounterSearchPayloadType();

            // set patient
            payload.setPatientId(patientId);

            // set type of encounter to outpatient
            payload.setTypeOfEncounter("IMP");

            // set start and end date
            if (startDate != null && !startDate.isEmpty()) {
               payload.setEncounterStartTimeFrame(startDate);
            }

            if (endDate != null && !endDate.isEmpty()) {
               payload.setEncounterEndTimeFrame(endDate);
            } else {
               payload.setEncounterEndTimeFrame(this.convertToCDATime(new Date()));
            }

            // set status: "active", "cancelled", "completed", "new"
            if (statusList != null && !statusList.isEmpty()) {
               for (String status : statusList) {
                  payload.getEncounterStatus().add(status);
               }
            } else {
               payload.getEncounterStatus().add("active");
            }

            // call web services
            if (factService == null) {
               initService(serviceEndpoint);
            }

            AdapterFactService port = factService.getCommonDataLayerFactPort();
            FactQueryResponseType resp =
                    port.getAdmissionFacts(createEncounterSearchQuery("REPC_IN040100UV", "REPC_TE040100UV", payload));

            return resp.getProblemFactOrMedicationFactOrAllergyFact();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return new ArrayList<FactType>();
   }

   private FactQueryRequestType createCareRecordQuery(String interaction, String triggerEvent, CareRecordPayloadType payload) {
      FactQueryRequestType request = new FactQueryRequestType();
      request.setQueryId(CommonUtil.generateId());
      request.setSenderId(this.getClass().getName());
      request.setInteractionId(interaction);
      request.setTriggerEventCode(triggerEvent);
      request.setCareRecordPayload(payload);

      return request;
   }

   /**
    * Construct an encounter search query request.
    * @param interaction
    * @param triggerEvent
    * @param payload
    * @return
    */
   private FactQueryRequestType createEncounterSearchQuery(String interaction, String triggerEvent, EncounterSearchPayloadType payload) {
      FactQueryRequestType request = new FactQueryRequestType();
      request.setQueryId(CommonUtil.generateId());
      request.setSenderId(this.getClass().getName());
      request.setInteractionId(interaction);
      request.setTriggerEventCode(triggerEvent);
      request.setEncounterSearchPayload(payload);

      return request;
   }

   private String convertToCDATime(Date date) {
      return cdaDateFormat.format(date);
   }

   public static String toString(FactType obj) {
      StringBuffer str = new StringBuffer();

      if (obj instanceof gov.hhs.fha.nhinc.adapter.fact.AllergyFactType) {
         AllergyFactType fact = (AllergyFactType) obj;
         str.append("type[AllergyFact] id=[" + (!fact.getId().isEmpty() ? fact.getId().get(0).getValue() : "") + "] patientId=[" +
                 fact.getPatientId().getValue() + "] adverseEventDate=[" + fact.getAdverseEventDate() + "]adverseEventType=[" +
                 (fact.getAdverseEventType() != null ? (fact.getAdverseEventType().getCode() + "|" + fact.getAdverseEventType().getLabel()) : "") +
                 "] codedProduct=[" + (fact.getCodedProduct() != null ? (fact.getCodedProduct().getCode() + "|" + fact.getCodedProduct().getLabel()) : "") +
                 "] freeTextProduct=[" + fact.getFreeTextProduct() + "] #reactions=[" + fact.getReaction().size() + "]");
         for (int i = 0; i < fact.getReaction().size(); i++) {
            CodeLabelPair reaction = fact.getReaction().get(i).getCodedReaction();
            str.append(" reaction " + i + "=[" + (reaction != null ? reaction.getCode() : fact.getReaction().get(i).getFreeTextReaction()) + "] ");
         }
      } else if (obj instanceof gov.hhs.fha.nhinc.adapter.fact.ProblemFactType) {
         ProblemFactType fact = (ProblemFactType) obj;
         str.append("type[ProblemFact] id=[" + (!fact.getId().isEmpty() ? fact.getId().get(0).getValue() : "") + "] patientId=[" +
                 fact.getPatientId().getValue() + "] problemType=[" +
                 (fact.getProblemType() != null ? (fact.getProblemType().getCode() + "|" + fact.getProblemType().getLabel()) : "") +
                 "] problemDate=[" + fact.getProblemDate() + ",resolutionDate=" + fact.getResolutionDate() + "] codedProblem=[" +
                 (fact.getCodedProblem() != null ? (fact.getCodedProblem().getCode() + "|" + fact.getCodedProblem().getLabel()) : "") +
                 "] freeTextProblem=[" + fact.getFreeTextProblem() + "] codedProblemStatus=[" +
                 (fact.getCodedProblemStatus() != null ? fact.getCodedProblemStatus().getCode() : "") + "] #treatingProvider=[" +
                 fact.getTreatingProvider().size() + "]" + System.getProperty("line.separator") + "\t");
         for (int i = 0; i < fact.getTreatingProvider().size(); i++) {
            str.append("provider=[" + fact.getTreatingProvider().get(i).getPrefix() +
                    fact.getTreatingProvider().get(i).getFirstName() + fact.getTreatingProvider().get(i).getMiddleName() +
                    fact.getTreatingProvider().get(i).getFamilyName() + fact.getTreatingProvider().get(i).getSuffix() + "] ");
         }
         str.append(System.getProperty("line.separator"));
      }

      return str.toString();
   }
}
