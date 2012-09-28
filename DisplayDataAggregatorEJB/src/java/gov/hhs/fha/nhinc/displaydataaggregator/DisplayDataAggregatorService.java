/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaydataaggregator;

import gov.hhs.fha.nhinc.aggregator.DisplayDataAggregatorPortType;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author cmatser
 */
@WebService(serviceName = "DisplayDataAggregator", portName = "DisplayDataAggregatorPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.aggregator.DisplayDataAggregatorPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:aggregator", wsdlLocation = "META-INF/wsdl/DisplayDataAggregatorService/DisplayDataAggregator.wsdl")
@Stateless
public class DisplayDataAggregatorService implements DisplayDataAggregatorPortType {

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

}
