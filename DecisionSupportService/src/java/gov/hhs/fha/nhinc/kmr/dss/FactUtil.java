/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.dss;

import javax.jws.WebService;

import gov.hhs.fha.nhinc.adapter.fact.FactType;
import gov.hhs.fha.nhinc.adapter.fact.AllergyFactType;
import gov.hhs.fha.nhinc.adapter.fact.MedicationFactType;
import gov.hhs.fha.nhinc.adapter.fact.PersonFactType;
import gov.hhs.fha.nhinc.adapter.fact.PreConditionFactType;
import gov.hhs.fha.nhinc.adapter.fact.ProblemFactType;
import gov.hhs.fha.nhinc.adapter.fact.ResultFactType;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import gov.hhs.fha.nhinc.kmr.VirtualMedicalRecord;


/**
 *
 * @author Steven Clark (from fact handler by Duane DeCouteau)
 */
@WebService()
public class FactUtil {
    private FactHelper factHelper = null;

    @Override
    protected void finalize() throws Throwable
    {
        factHelper = null;
    }

    private FactHelper getFactHelper() {
        if (factHelper == null)
        {
            factHelper = new FactHelper();
        }
        return factHelper;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public Boolean isPatientBeingProcessed(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().isPatientBeingProcessed(patientId);
    }

    /**
     * Web service operation
     */
    @WebMethod
    public FactType getDemographicFactEvent(@WebParam(name = "patientId") String patientId) {
        FactType factEvent = null;
        List<FactType> lDemographics = getFactHelper().getFactEvents(patientId, PersonFactType.class);
        if (lDemographics != null && lDemographics.size() > 0)
        {
            factEvent = lDemographics.get(0);
        }
        return factEvent;
    }

    /**
     * Web service operation
     */
    @WebMethod
    public List<FactType> getHistoricDemographicFacts(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().getHistoricDemographicFacts(patientId);
    }

     /**
     * Web service operation
     */
    @WebMethod
    public List<FactType> getAllergyFactEvents(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().getFactEvents(patientId, AllergyFactType.class);
    }

     /**
     * Web service operation
     */
    @WebMethod
    public List<FactType> getHistoricAllergyFacts(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().getHistoricAllergyFacts(patientId);
    }

     /**
     * Web service operation
     */
    @WebMethod
    public List<FactType> getMedicationFactEvents(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().getFactEvents(patientId, MedicationFactType.class);
    }

     /**
     * Web service operation
     */
    @WebMethod
    public List<FactType> getHistoricMedicationFacts(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().getHistoricMedicationFacts(patientId);
    }

     /**
     * Web service operation
     */
    @WebMethod
    public List<FactType> getPreConditionFactEvents(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().getFactEvents(patientId, PreConditionFactType.class);
    }

     /**
     * Web service operation
     */
//    @WebMethod
//    public List<FactType> getHistoricPreConditionFacts(@WebParam(name = "patientId") String patientId) {
//        return getFactHelper().getHistoricPreConditionFacts(patientId);
//    }

     /**
     * Web service operation
     */
    @WebMethod
    public List<FactType> getProblemFactEvents(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().getFactEvents(patientId, ProblemFactType.class);
    }

     /**
     * Web service operation
     */
    @WebMethod
    public List<FactType> getHistoricProblemFacts(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().getHistoricProblemFacts(patientId);
    }

     /**
     * Web service operation
     */
    @WebMethod
    public List<FactType> getResultFactEvents(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().getFactEvents(patientId, ResultFactType.class);
    }

     /**
     * Web service operation
     */
    @WebMethod
    public List<FactType> getHistoricResultFacts(@WebParam(name = "patientId") String patientId) {
        return getFactHelper().getHistoricResultFacts(patientId);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getVirtualMedicalRecord")
    public VirtualMedicalRecord getVirtualMedicalRecord(@WebParam(name = "patientId")
    String patientId) {
        return getFactHelper().getVirtualMedicalRecord(patientId);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createVirtualMedicalRecord")
    public VirtualMedicalRecord createVirtualMedicalRecord(@WebParam(name = "patientId")
    String patientId) {
        return getFactHelper().createVirtualMedicalRecord(patientId);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteVirtualMedicalRecord")
    public Boolean deleteVirtualMedicalRecord(@WebParam(name = "patientId")
    String patientId) {
        return getFactHelper().deleteVirtualMedicalRecord(patientId);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deletePatientCohort")
    public Boolean deletePatientCohort(@WebParam(name = "patientId")
    String patientId) {
        return getFactHelper().deletePatientCohort(patientId);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteAllPatientEvents")
    public Boolean deleteAllPatientEvents(@WebParam(name = "patientId")
    String patientId) {
        return getFactHelper().deleteAllPatientEvents(patientId);
    }

}
