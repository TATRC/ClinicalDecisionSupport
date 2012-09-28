/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displayptalertdata;

import gov.hhs.fha.nhinc.aggregator.DisplayDataComponentPortType;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author cmatser
 */
@WebService(serviceName = "DisplayDataComponent", portName = "DisplayDataComponentPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.aggregator.DisplayDataComponentPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:aggregator", wsdlLocation = "META-INF/wsdl/DisplayPtAlertDataService/DisplayDataComponent.wsdl")
@Stateless
public class DisplayPtAlertDataService implements DisplayDataComponentPortType {

    public gov.hhs.fha.nhinc.common.dda.GetDataSourceNameResponseType getDataSourceName(gov.hhs.fha.nhinc.common.dda.GetDataSourceNameRequestType getDataSourceNameRequest) {
        return new DisplayPtAlertDataImpl().getDataSourceName(getDataSourceNameRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusResponseType updateComponentInboxStatus(gov.hhs.fha.nhinc.common.dda.UpdateComponentInboxStatusRequestType updateComponentInboxStatusRequest) {
        return new DisplayPtAlertDataImpl().updateComponentInboxStatus(updateComponentInboxStatusRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataResponseType getComponentDetailDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentDetailDataForUserRequestType getComponentDetailDataForUserRequest) {
        return new DisplayPtAlertDataImpl().getComponentDetailDataForUser(getComponentDetailDataForUserRequest);
    }

    public gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataResponseType getComponentSummaryDataForUser(gov.hhs.fha.nhinc.common.dda.GetComponentSummaryDataForUserRequestType getComponentSummaryDataForUserRequest) {
        return new DisplayPtAlertDataImpl().getComponentSummaryDataForUser(getComponentSummaryDataForUserRequest);
    }

}
