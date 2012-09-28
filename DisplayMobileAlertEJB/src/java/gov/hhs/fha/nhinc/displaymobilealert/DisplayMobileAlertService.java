/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaymobilealert;

import gov.hhs.fha.nhinc.aggregator.DisplayDataComponentPortType;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author cmatser
 */
@WebService(serviceName = "DisplayDataComponent", portName = "DisplayDataComponentPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.aggregator.DisplayDataComponentPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:aggregator", wsdlLocation = "META-INF/wsdl/DisplayMobileAlertService/DisplayDataComponent.wsdl")
@Stateless
public class DisplayMobileAlertService implements DisplayDataComponentPortType {

    public gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType getDataSourceName(gov.hhs.fha.nhinc.common.dda.GetDataSourceNameRequestType getDataSourceNameRequest) {
        return new DisplayMobileAlertImpl().getDataSourceName(getDataSourceNameRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType updateComponentInboxStatus(gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType updateComponentInboxStatusRequest) {
        return new DisplayMobileAlertImpl().updateComponentInboxStatus(updateComponentInboxStatusRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType getComponentDetailDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType getComponentDetailDataForUserRequest) {
        return new DisplayMobileAlertImpl().getComponentDetailDataForUser(getComponentDetailDataForUserRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType getComponentSummaryDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType getComponentSummaryDataForUserRequest) {
        return new DisplayMobileAlertImpl().getComponentSummaryDataForUser(getComponentSummaryDataForUserRequest);
    }

}
