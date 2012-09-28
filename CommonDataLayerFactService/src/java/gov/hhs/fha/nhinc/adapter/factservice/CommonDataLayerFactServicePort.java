package gov.hhs.fha.nhinc.adapter.factservice;

import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType;
import gov.hhs.fha.nhinc.adapter.fact.RecordOrderRequestType;
import gov.hhs.fha.nhinc.adapter.fact.RecordOrderResponseType;

public interface CommonDataLayerFactServicePort {

  /**
   * Returns allergies information
   * @param param0 with careRecordPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getAllergyFacts (FactQueryRequestType request) throws Exception;

  /**
   * Returns immunizations information
   * @param param0 with careRecordPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getImmunizationFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns problems information
   * @param param0 with careRecordPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getProblemFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns lab results information
   * @param param0 with careRecordPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getTestResultFacts(FactQueryRequestType request) throws Exception;

  /**
   *
   * @param param0
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getMedicationFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns patient demographics information
   * @param param0 with careRecordPayload for query payload (only patientId element is required)
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getDemographicsFact(FactQueryRequestType request) throws Exception;

  /**
   * Returns support contacts information of a patient
   * @param param0 with careRecordPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getSupportContactFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns orders information
   * @param param0 with careRecordPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getOrderFacts(FactQueryRequestType v) throws Exception;

  /**
   * Returns vitals information
   * @param param0 with careRecordPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getVitalsFacts(FactQueryRequestType request) throws Exception;

  /**
   *
   * @param param0
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.RecordOrderResponseType
   */
  public RecordOrderResponseType recordLabOrder(RecordOrderRequestType v) throws Exception;

  /**
   * Returns details of a lab event
   * @param param0 with resultEventPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getTestResultEventFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns procedures information
   * @param param0 with careRecordPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getProcedureFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns outpatient encounters matching the search criteria
   * @param param0 with encounterSearchPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getAppointmentFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns inaptient encounters matching the search criteria
   * @param param0 with encounterSearchPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getAdmissionFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns encounters matching the search criteria
   * @param param0 with encounterSearchPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getEncounterFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns details of an encounter
   * @param param0 with recordQueryPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getEncounterDetailFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns patients and their demographics that matched the search criteria
   * @param param0 with patientSearchPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getPatientFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns providers information
   * @param param0 with providerSearchPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getProviderFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns insurances information
   * @param param0 with careRecordPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getInsuranceFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns registry profiles information
   * @param param0 with careRecordPayload for query payload (only patientId element is required)
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getRegistryFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns diagnostic imaging information
   * @param param0 with careRecordPayload for query payload and careProvisionCode = DICAT
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getImagingResultFacts(FactQueryRequestType request) throws Exception;

  /**
   * Returns appointment slots schedule information
   * @param param0 with scheduleSearchPayload for query payload
   * @return
   *     returns gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType
   */
  public FactQueryResponseType getAppointmentScheduleFacts(FactQueryRequestType request) throws Exception;
}
