/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaymaildata;

import gov.hhs.fha.nhinc.common.dda.GetMessagesRequestType;
import gov.hhs.fha.nhinc.common.dda.GetMessagesResponseType;
import javax.jws.WebService;

/**
 *
 * @author jharby
 */
@WebService(serviceName = "DisplayMailDataComponent", portName = "DisplayMailDataComponentPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.aggregator.DisplayMailDataComponentPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:aggregator", wsdlLocation = "WEB-INF/wsdl/DisplayMailDataService/DisplayMailDataComponent.wsdl")
public class DisplayMailDataService {

     public gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType getDataSourceName(gov.hhs.fha.nhinc.common.dda.GetDataSourceNameRequestType getDataSourceNameRequest) {
        return new DisplayMailDataImpl().getDataSourceName(getDataSourceNameRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType updateComponentInboxStatus(gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType updateComponentInboxStatusRequest) {
        return new DisplayMailDataImpl().updateComponentInboxStatus(updateComponentInboxStatusRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType getComponentDetailDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType getComponentDetailDataForUserRequest) {
        return new DisplayMailDataImpl().getComponentDetailDataForUser(getComponentDetailDataForUserRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType getComponentSummaryDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType getComponentSummaryDataForUserRequest) {
        return new DisplayMailDataImpl().getComponentSummaryDataForUser(getComponentSummaryDataForUserRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.SetMessageResponseType setMessage(gov.hhs.fha.nhinc.common.dda.SetMessageRequestType setMessageRequestType) {
        return new DisplayMailDataImpl().setMessage(setMessageRequestType);
    }

    public GetMessagesResponseType getMessages(GetMessagesRequestType request) {
        return new DisplayMailDataImpl().getMessages(request);
    }
}
