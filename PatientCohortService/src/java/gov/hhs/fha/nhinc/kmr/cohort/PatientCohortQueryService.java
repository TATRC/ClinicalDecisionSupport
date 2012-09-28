/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.kmr.cohort;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Jerry Goodnough
 */
@WebService(serviceName = "PatientCohortQuery", portName = "PatientCohortQueryPortSoap", endpointInterface = "gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohortQueryPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:kmr:patientcohort", wsdlLocation = "META-INF/wsdl/PatientCohortQueryService/PatientCohortQuery.wsdl")
@Stateless
public class PatientCohortQueryService {

    public gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohort patientCohortQuery(gov.hhs.fha.nhinc.kmr.patientcohort.PatientCohortQueryRequestType cohortQueryRequest) {
        return PatientCohortQueryServiceImpl.getSinglton().patientCohortQuery(cohortQueryRequest);
    }

}
