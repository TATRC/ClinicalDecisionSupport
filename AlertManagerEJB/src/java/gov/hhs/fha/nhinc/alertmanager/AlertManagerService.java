/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author cmatser
 */
@WebService(serviceName = "AlertManager", portName = "AlertManagerPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.alertmanager.AlertManagerPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:alertmanager", wsdlLocation = "META-INF/wsdl/AlertManagerService/AlertManager.wsdl")
@Stateless
public class AlertManagerService {

    public gov.hhs.fha.nhinc.common.task.UpdateAlertResponseType updateAlert(gov.hhs.fha.nhinc.common.task.UpdateAlertRequestType updateAlertRequest) {
        return new AlertManagerImpl().updateAlert(updateAlertRequest);
    }

    public gov.hhs.fha.nhinc.common.task.GetAlertFactsResponseType getAlertFacts(gov.hhs.fha.nhinc.common.task.GetAlertFactsRequestType getAlertFactsRequest) {
        return new AlertManagerImpl().getAlertFacts(getAlertFactsRequest);
    }

}
