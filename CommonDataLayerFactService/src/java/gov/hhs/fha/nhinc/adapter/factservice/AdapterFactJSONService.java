/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.adapter.factservice;

import gov.hhs.fha.nhinc.adapter.fact.FactQueryRequestType;
import gov.hhs.fha.nhinc.adapter.fact.FactQueryResponseType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import org.jvnet.jax_ws_commons.json.JSONBindingID;

/**
 *
 * @author kim
 */
@WebService(name = "AdapterFactJSONService", serviceName = "AdapterFactJSONService")
@BindingType(JSONBindingID.JSON_BINDING)
public class AdapterFactJSONService {

  CommonDataLayerFactServicePort port = CommonDataLayerFactServiceImpl.getInstance();

  @WebMethod(action = "getAllergyFacts", operationName = "getAllergyFacts")
  public FactQueryResponseType getAllergyFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getAllergyFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getImmunizationFacts")
  public FactQueryResponseType getImmunizationFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getImmunizationFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getProblemFacts")
  public FactQueryResponseType getProblemFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getProblemFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getTestResultFacts")
  public FactQueryResponseType getTestResultFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getTestResultFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getMedicationFacts")
  public FactQueryResponseType getMedicationFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getMedicationFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getDemographicsFact")
  public FactQueryResponseType getDemographicsFact(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getDemographicsFact(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getVitalsFacts")
  public FactQueryResponseType getVitalsFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getVitalsFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getTestResultEventFacts")
  public FactQueryResponseType getTestResultEventFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getTestResultEventFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getSupportContactFacts")
  public FactQueryResponseType getSupportContactFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getSupportContactFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getProcedureFacts")
  public FactQueryResponseType getProcedureFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getProcedureFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getAppointmentFacts")
  public FactQueryResponseType getAppointmentFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getAppointmentFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getAdmissionFacts")
  public FactQueryResponseType getAdmissionFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getAdmissionFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getEncounterFacts")
  public FactQueryResponseType getEncounterFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    throw new FaultMessage(new Fault(Fault.UNKNOWN_ERROR, "getEncounterFacts not implemented yet!"));
  }

  @WebMethod(action = "getEncounterDetailFacts")
  public FactQueryResponseType getEncounterDetailFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getEncounterDetailFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getPatientFacts")
  public FactQueryResponseType getPatientFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getPatientFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getProviderFacts")
  public FactQueryResponseType getProviderFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getProviderFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getInsuranceFacts")
  public FactQueryResponseType getInsuranceFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    throw new FaultMessage(new Fault(Fault.UNKNOWN_ERROR, "getInsuranceFacts not implemented yet!"));
  }

  @WebMethod(action = "getOrderFacts")
  public FactQueryResponseType getOrderFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getOrderFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getRegistryFacts")
  public FactQueryResponseType getRegistryFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getRegistryFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getImagingResultFacts")
  public FactQueryResponseType getImagingResultFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getImagingResultFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }

  @WebMethod(action = "getAppointmentScheduleFacts")
  public FactQueryResponseType getAppointmentScheduleFacts(@WebParam(name = "criteria") FactQueryRequestType criteria) throws FaultMessage {
    try {
      return port.getAppointmentScheduleFacts(criteria);
    } catch (Exception e) {
      throw new FaultMessage(new Fault(Fault.getErrorCode(e), e.getMessage()));
    }
  }
}
