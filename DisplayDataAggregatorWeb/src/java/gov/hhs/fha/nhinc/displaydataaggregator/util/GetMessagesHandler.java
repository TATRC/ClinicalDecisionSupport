/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.displaydataaggregator.util;

import gov.hhs.fha.nhinc.common.dda.GetMessagesRequestType;
import gov.hhs.fha.nhinc.common.dda.GetMessagesResponseType;
import java.util.Map;

/**
 * Called by DisplayDataAggregatorImpl to handle the different types of GetMessages
 * requests
 *
 * @author jharby
 */
public class GetMessagesHandler {

    /**
     * Get all messages for all types, URLs from displayAggregator.properties file
     * passed in a Map.
     *
     * @param urlMap
     * @param request
     * @return
     */
    public GetMessagesResponseType getAllMessages(Map<String, String> urlMap, GetMessagesRequestType request) {
        GetMessagesResponseType response = new GetMessagesResponseType();
        response.getGetMessageResponse().
                addAll(getEmailMessages(urlMap.get("Email"), request).getGetMessageResponse());
        response.getGetMessageResponse().
                addAll(getAlertMessages(urlMap.get("Alerts"), request).getGetMessageResponse());
        response.getGetMessageResponse().
                addAll(getDocumentMessages(urlMap.get("Documents"), request).getGetMessageResponse());
        response.getGetMessageResponse().
                addAll(getArchiveMessages(urlMap.get("Archive"), request).getGetMessageResponse());
        return response;

    }

    /**
     * Get messages from alert tables in KMR schema
     *
     * @param dataSourceUrl
     * @param request
     * @return
     */
    public GetMessagesResponseType getAlertMessages(String dataSourceUrl, GetMessagesRequestType request) {

        gov.hhs.fha.nhinc.aggregator.DisplayDataComponent service =
                new gov.hhs.fha.nhinc.aggregator.DisplayDataComponent();

        gov.hhs.fha.nhinc.aggregator.DisplayDataComponentPortType port =
                service.getDisplayDataComponentPortSoap11();

        ((javax.xml.ws.BindingProvider) port).getRequestContext().
                put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dataSourceUrl);

        GetMessagesRequestType componentRequest = new GetMessagesRequestType();
        componentRequest.setMessageType(request.getMessageType());
        componentRequest.setPatientId(request.getPatientId());
        componentRequest.setProviderId(request.getProviderId());
        return port.getMessages(componentRequest);
    }


    /**
     * Make WS call to get email messages from Zimbra. Uses SSL connection.
     *
     * @param dataSourceUrl
     * @param request
     * @return
     */
    public GetMessagesResponseType getEmailMessages(String dataSourceUrl, GetMessagesRequestType request) {

        gov.hhs.fha.nhinc.aggregator.DisplayMailDataComponent service =
                new gov.hhs.fha.nhinc.aggregator.DisplayMailDataComponent();

        gov.hhs.fha.nhinc.aggregator.DisplayMailDataComponentPortType port =
                service.getDisplayMailDataComponentPortSoap11();

        ((javax.xml.ws.BindingProvider) port).getRequestContext().
                put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY, dataSourceUrl);

        GetMessagesRequestType componentRequest = new GetMessagesRequestType();
        componentRequest.setMessageType(request.getMessageType());
        componentRequest.setPatientId(request.getPatientId());
        componentRequest.setProviderId(request.getProviderId());
        return port.getMessages(componentRequest);
    }

    /**
     * TBD - returns stub currently
     *
     * @param dataSourceUrl
     * @param request
     * @return
     */
    public GetMessagesResponseType getDocumentMessages(String dataSourceUrl, GetMessagesRequestType request) {
        GetMessagesResponseType response = new GetMessagesResponseType();
        GetMessagesResponseType.GetMessageResponse gmr = new GetMessagesResponseType.GetMessageResponse();
        gmr.setMessageType("Document");
        gmr.setFrom("TESTDOC");
        gmr.setTasksCount(2);
        gmr.setTasksComplete(2);
        gmr.setStatusMessage("DOCUMENT SUCCESS");
        gmr.setTitle("DOCUMENT MESSAGE");
        response.getGetMessageResponse().add(gmr);
        return response;
    }

    /**
     * TBD - returns stub currently
     * 
     * @param dataSourceUrl
     * @param request
     * @return
     */
    public GetMessagesResponseType getArchiveMessages(String dataSourceUrl, GetMessagesRequestType request) {
        GetMessagesResponseType response = new GetMessagesResponseType();
        GetMessagesResponseType.GetMessageResponse gmr = new GetMessagesResponseType.GetMessageResponse();
        gmr.setMessageType("Archive");
        gmr.setFrom("TESTARCHIVE");
        gmr.setTasksCount(2);
        gmr.setTasksComplete(2);
        gmr.setStatusMessage("ARCHIVE SUCCESS");
        gmr.setTitle("ARCHIVE MESSAGE");
        response.getGetMessageResponse().add(gmr);
        return response;
    }
}
