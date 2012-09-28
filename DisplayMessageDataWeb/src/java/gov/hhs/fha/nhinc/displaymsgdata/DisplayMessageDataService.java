/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaymsgdata;

import javax.jws.WebService;

/**
 *
 * @author jharby
 */
@WebService(serviceName = "DisplayDataComponent", portName = "DisplayDataComponentPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.aggregator.DisplayDataComponentPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:aggregator", wsdlLocation = "WEB-INF/wsdl/DisplayMessageDataService/DisplayDataComponent.wsdl")
public class DisplayMessageDataService {

    public gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType getDataSourceName(gov.hhs.fha.nhinc.common.dda.GetDataSourceNameRequestType getDataSourceNameRequest) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType updateComponentInboxStatus(gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType updateComponentInboxStatusRequest) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType getComponentDetailDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType getComponentDetailDataForUserRequest) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType getComponentSummaryDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType getComponentSummaryDataForUserRequest) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public gov.hhs.fha.nhinc.common.dda.SetMessageResponseType setMessage(gov.hhs.fha.nhinc.common.dda.SetMessageRequestType setMessageRequestType) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public gov.hhs.fha.nhinc.common.dda.GetMessagesResponseType getMessages(gov.hhs.fha.nhinc.common.dda.GetMessagesRequestType getMessagesRequestType) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
