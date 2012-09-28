/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author cmatser
 */
@WebService(serviceName = "TaskManager", portName = "TaskManagerPortSoap11", endpointInterface = "gov.hhs.fha.nhinc.taskmanager.TaskManagerPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:taskmanager", wsdlLocation = "META-INF/wsdl/TaskManagerService/TaskManager.wsdl")
@Stateless
public class TaskManagerService implements TaskManagerPortType {

    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType startTaskFromRule(gov.hhs.fha.nhinc.common.task.StartTaskFromRuleRequestType startTaskFromRuleRequest) {
        return new TaskManagerImpl().startTaskFromRule(startTaskFromRuleRequest);
    }

    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType sendMailTask(gov.hhs.fha.nhinc.common.task.SendMailTaskRequestType sendMailTaskRequest) {
        return new TaskManagerImpl().sendMailTask(sendMailTaskRequest);
    }

    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType registerPersonDisease(gov.hhs.fha.nhinc.common.task.RegisterPersonDiseaseRequestType registerPersonDiseaseRequest) {
        return new TaskManagerImpl().registerPersonDisease(registerPersonDiseaseRequest);
    }

    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType unRegisterPersonDisease(gov.hhs.fha.nhinc.common.task.UnRegisterPersonDiseaseRequestType unRegisterPersonDiseaseRequest) {
        return new TaskManagerImpl().unRegisterPersonDisease(unRegisterPersonDiseaseRequest);
    }

    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType createLabOrder(gov.hhs.fha.nhinc.common.task.CreateLabOrderRequestType createLabOrderRequest) {
        return new TaskManagerImpl().createLabOrder(createLabOrderRequest);
    }

    public gov.hhs.fha.nhinc.common.task.TaskManagerResponseType scheduleAppt(gov.hhs.fha.nhinc.common.task.ScheduleApptRequestType scheduleApptRequest) {
        return new TaskManagerImpl().scheduleAppt(scheduleApptRequest);
    }

}
