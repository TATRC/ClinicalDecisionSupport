/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaydataaggregator;

import gov.hhs.fha.nhinc.common.dda.GetMessagesResponseType;
import javax.jws.WebService;

/**
 *
 * @author jharby
 */
@WebService(serviceName = "DisplayDataAggregator", portName = "DisplayDataAggregatorPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.aggregator.DisplayDataAggregatorPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:aggregator", wsdlLocation = "WEB-INF/wsdl/DisplayDataAggregatorService/DisplayDataAggregator.wsdl")
public class DisplayDataAggregatorService {

    public gov.hhs.fha.nhinc.common.dda.UpdateInboxStatusResponseType updateInboxStatus(gov.hhs.fha.nhinc.common.dda.UpdateInboxStatusRequestType updateInboxStatusRequest) {
        return new DisplayDataAggregatorImpl().updateInboxStatus(updateInboxStatusRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetAvailableSourcesResponseType getAvailableSources(gov.hhs.fha.nhinc.common.dda.GetAvailableSourcesRequestType getAvailableSourcesRequest) {
        return new DisplayDataAggregatorImpl().getAvailableSources(getAvailableSourcesRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetDetailDataResponseType getDetailData(gov.hhs.fha.nhinc.common.dda.GetDetailDataRequestType getDetailDataRequest) {
        return new DisplayDataAggregatorImpl().getDetailData(getDetailDataRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetSummaryDataResponseType getSummaryData(gov.hhs.fha.nhinc.common.dda.GetSummaryDataRequestType getSummaryDataRequest) {
        return new DisplayDataAggregatorImpl().getSummaryData(getSummaryDataRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetDetailDataResponseType getDetailDataForUser(gov.hhs.fha.nhinc.common.dda.GetDetailDataForUserRequestType getDetailDataForUserRequest) {
        return new DisplayDataAggregatorImpl().getDetailDataForUser(getDetailDataForUserRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetSummaryDataResponseType getSummaryDataForUser(gov.hhs.fha.nhinc.common.dda.GetSummaryDataForUserRequestType getSummaryDataForUserRequest) {
        return new DisplayDataAggregatorImpl().getSummaryDataForUser(getSummaryDataForUserRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.SetMessageDataResponseType setMessage(gov.hhs.fha.nhinc.common.dda.SetMessageDataRequestType setMessageDataRequestType) {
        return new DisplayDataAggregatorImpl().setMessage(setMessageDataRequestType);
    }

    public gov.hhs.fha.nhinc.common.dda.GetMessageDetailResponseType getMessageDetail(gov.hhs.fha.nhinc.common.dda.GetMessageDetailRequestType getMessageDetailRequest) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public gov.hhs.fha.nhinc.common.dda.GetMessagesResponseType getMessages(gov.hhs.fha.nhinc.common.dda.GetMessagesRequestType getMessagesRequest) {
        GetMessagesResponseType responseType = new GetMessagesResponseType();
        try {
            responseType = new DisplayDataAggregatorImpl().getMessages(getMessagesRequest);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return responseType;
    }

}
