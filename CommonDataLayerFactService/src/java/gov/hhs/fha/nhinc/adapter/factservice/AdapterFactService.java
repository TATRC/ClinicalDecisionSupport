/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.factservice;

import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType;
import gov.hhs.fha.nhinc.adapter.fact.RecordOrderRequestType;
import gov.hhs.fha.nhinc.adapter.fact.RecordOrderResponseType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 *
 * @author kim
 */
@WebService(name = "AdapterFactService", serviceName = "AdapterFactService", portName = "CommonDataLayerFactPort", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice")
public class AdapterFactService {

  CommonDataLayerFactServicePort port = CommonDataLayerFactServiceImpl.getInstance();

  @WebMethod(action = "urn:GetAllergyFacts", operationName = "GetAllergyFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetAllergyFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetAllergyFacts")
  @ResponseWrapper(localName = "GetAllergyFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetAllergyFactsResponse")
  public FactQueryResponseType getAllergyFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getAllergyFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetImmunizationFacts", action = "urn:GetImmunizationFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetImmunizationFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetImmunizationFacts")
  @ResponseWrapper(localName = "GetImmunizationFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetImmunizationFactsResponse")
  public FactQueryResponseType getImmunizationFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getImmunizationFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetProblemFacts", action = "urn:GetProblemFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetProblemFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetProblemFacts")
  @ResponseWrapper(localName = "GetProblemFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetProblemFactsResponse")
  public FactQueryResponseType getProblemFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getProblemFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetDemographicsFact", action = "urn:GetDemographicsFact")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetDemographicsFact", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetDemographicsFact")
  @ResponseWrapper(localName = "GetDemographicsFactResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetDemographicsFactResponse")
  public FactQueryResponseType getDemographicsFact(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getDemographicsFact(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetTestResultFacts", action = "urn:GetTestResultFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetTestResultFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetTestResultFacts")
  @ResponseWrapper(localName = "GetTestResultFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetTestResultFactsResponse")
  public FactQueryResponseType getTestResultFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getTestResultFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetMedicationFacts", action = "urn:GetMedicationFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetMedicationFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetMedicationFacts")
  @ResponseWrapper(localName = "GetMedicationFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetMedicationFactsResponse")
  public FactQueryResponseType getMedicationFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getMedicationFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetSupportContactFacts", action = "urn:GetSupportContactFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetSupportContactFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetSupportContactFacts")
  @ResponseWrapper(localName = "GetSupportContactFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetSupportContactFactsResponse")
  public FactQueryResponseType getSupportContactFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getSupportContactFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetOrderFacts", action = "urn:GetOrderFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  @RequestWrapper(localName = "GetOrderFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetOrderFacts")
  @ResponseWrapper(localName = "GetOrderFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetOrderFactsResponse")
  public FactQueryResponseType getOrderFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getOrderFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetVitalsFacts", action = "urn:GetVitalsFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetVitalsFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetVitalsFacts")
  @ResponseWrapper(localName = "GetVitalsFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetVitalsFactsResponse")
  public FactQueryResponseType getVitalsFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getVitalsFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetTestResultEventFacts", action = "urn:GetTestResultEventFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetTestResultEventFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetTestResultEventFacts")
  @ResponseWrapper(localName = "GetTestResultEventFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetTestResultEventFactsResponse")
  public FactQueryResponseType getTestResultEventFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getTestResultEventFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "RecordLabOrder", action = "urn:RecordLabOrder")
  @WebResult(name = "RecordOrderResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact")
  @RequestWrapper(localName = "RecordLabOrder", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.RecordLabOrderRequest")
  @ResponseWrapper(localName = "RecordLabOrderResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.RecordLabOrderResponse")
  public RecordOrderResponseType recordLabOrder(
          @WebParam(name = "RecordOrderRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact") RecordOrderRequestType recordOrderRequest) throws FaultMessage {
    throw new UnsupportedOperationException("recordLabOrder not implemented yet.");
  }

  @WebMethod(operationName = "GetProcedureFacts", action = "urn:GetProcedureFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetProcedureFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetProcedureFacts")
  @ResponseWrapper(localName = "GetProcedureFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetProcedureFactsResponse")
  public FactQueryResponseType getProcedureFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getProcedureFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetAppointmentFacts", action = "urn:GetAppointmentFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetAppointmentFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetAppointmentFacts")
  @ResponseWrapper(localName = "GetAppointmentFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetAppointmentFactsResponse")
  public FactQueryResponseType getAppointmentFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getAppointmentFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetAdmissionFacts", action = "urn:GetAdmissionFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetAdmissionFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetAdmissionFacts")
  @ResponseWrapper(localName = "GetAdmissionFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetAdmissionFactsResponse")
  public FactQueryResponseType getAdmissionFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getAdmissionFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetEncounterDetailFacts", action = "urn:GetEncounterDetailFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetEncounterDetailFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetEncounterDetailFacts")
  @ResponseWrapper(localName = "GetEncounterDetailFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetEncounterDetailFactsResponse")
  public FactQueryResponseType getEncounterDetailFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getEncounterDetailFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetEncounterFacts", action = "urn:GetEncounterFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetEncounterFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetEncounterFacts")
  @ResponseWrapper(localName = "GetEncounterFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetEncounterFactsResponse")
  public FactQueryResponseType getEncounterFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "param0") FactQueryRequestType param0) throws FaultMessage {
    System.out.println("GetEncounterFacts not implemented yet!!!");
    throw new UnsupportedOperationException("getEncounterFacts not implemented yet.");
  }

  @WebMethod(operationName = "GetPatientFacts", action = "urn:GetPatientFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetPatientFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetPatientFacts")
  @ResponseWrapper(localName = "GetPatientFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetPatientFactsResponse")
  public FactQueryResponseType getPatientFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getPatientFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetProviderFacts", action = "urn:GetProviderFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetProviderFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetProviderFacts")
  @ResponseWrapper(localName = "GetProviderFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetProviderFactsResponse")
  public FactQueryResponseType getProviderFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getProviderFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetInsuranceFacts", action = "urn:GetInsuranceFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetInsuranceFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetInsuranceFacts")
  @ResponseWrapper(localName = "GetInsuranceFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetInsuranceFactsResponse")
  public FactQueryResponseType getInsuranceFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    throw new UnsupportedOperationException("getEncounterFacts not implemented yet.");
  }

  @WebMethod(operationName = "GetRegistryFacts", action = "urn:GetRegistryFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetRegistryFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetRegistryFacts")
  @ResponseWrapper(localName = "GetRegistryFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetRegistryFactsResponse")
  public FactQueryResponseType getRegistryFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getRegistryFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetImagingResultFacts", action = "urn:GetImagingResultFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetImagingResultFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetImagingResultFacts")
  @ResponseWrapper(localName = "GetImagingResultFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetImagingResultFactsResponse")
  public FactQueryResponseType getImagingResultFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getImagingResultFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(operationName = "GetAppointmentScheduleFacts", action = "urn:GetAppointmentScheduleFacts")
  @WebResult(name = "FactQueryResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "output")
  @RequestWrapper(localName = "GetAppointmentScheduleFacts", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetAppointmentScheduleFacts")
  @ResponseWrapper(localName = "GetAppointmentScheduleFactsResponse", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:factservice", className = "gov.hhs.fha.nhinc.adapter.factservice.GetAppointmentScheduleFactsResponse")
  public FactQueryResponseType getAppointmentScheduleFacts(
          @WebParam(name = "FactsQueryRequest", targetNamespace = "urn:gov:hhs:fha:nhinc:adapter:fact", partName = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getAppointmentScheduleFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }
}
