/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaydocdata;

import javax.jws.WebService;

/**
 *
 * @author Sushma
 */
@WebService(serviceName = "DisplayDataComponent", portName = "DisplayDataComponentPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.aggregator.DisplayDataComponentPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:aggregator", wsdlLocation = "WEB-INF/wsdl/DisplayDocDataService/DisplayDataComponent.wsdl")
public class DisplayDocDataService {

    public gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType getDataSourceName(gov.hhs.fha.nhinc.common.dda.GetDataSourceNameRequestType getDataSourceNameRequest) {
        return new DisplayDocDataImpl().getDataSourceName(getDataSourceNameRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType updateComponentInboxStatus(gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType updateComponentInboxStatusRequest) {
        return new DisplayDocDataImpl().updateComponentInboxStatus(updateComponentInboxStatusRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType getComponentDetailDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType getComponentDetailDataForUserRequest) {
        return new DisplayDocDataImpl().getComponentDetailDataForUser(getComponentDetailDataForUserRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType getComponentSummaryDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType getComponentSummaryDataForUserRequest) {
        return new DisplayDocDataImpl().getComponentSummaryDataForUser(getComponentSummaryDataForUserRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.SetMessageResponseType setMessage(gov.hhs.fha.nhinc.common.dda.SetMessageRequestType setMessageRequestType) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
