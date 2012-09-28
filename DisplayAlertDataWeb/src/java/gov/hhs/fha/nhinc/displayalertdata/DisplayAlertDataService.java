/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.displayalertdata;

import gov.hhs.fha.nhinc.common.dda.GetMessagesRequestType;
import gov.hhs.fha.nhinc.common.dda.GetMessagesResponseType;
import javax.jws.WebService;

/**
 *
 * @author Sushma
 */
@WebService(serviceName = "DisplayDataComponent", portName = "DisplayDataComponentPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.aggregator.DisplayDataComponentPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:aggregator", wsdlLocation = "WEB-INF/wsdl/DisplayAlertDataService/DisplayDataComponent.wsdl")
public class DisplayAlertDataService {

    public gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType getDataSourceName(gov.hhs.fha.nhinc.common.dda.GetDataSourceNameRequestType getDataSourceNameRequest) {
        return new DisplayAlertDataImpl().getDataSourceName(getDataSourceNameRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType updateComponentInboxStatus(gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType updateComponentInboxStatusRequest) {
        return new DisplayAlertDataImpl().updateComponentInboxStatus(updateComponentInboxStatusRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType getComponentDetailDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType getComponentDetailDataForUserRequest) {
        return new DisplayAlertDataImpl().getComponentDetailDataForUser(getComponentDetailDataForUserRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType getComponentSummaryDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType getComponentSummaryDataForUserRequest) {
        return new DisplayAlertDataImpl().getComponentSummaryDataForUser(getComponentSummaryDataForUserRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.SetMessageResponseType setMessage(gov.hhs.fha.nhinc.common.dda.SetMessageRequestType setMessageRequestType) {
        return new DisplayAlertDataImpl().setMessage(setMessageRequestType);
    }

    public GetMessagesResponseType getMessages(GetMessagesRequestType request) {
        return new DisplayAlertDataImpl().getMessages(request);
    }
}
