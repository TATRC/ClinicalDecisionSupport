/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager;

import gov.hhs.fha.nhinc.taskmanagerclient.TaskHandlerMessage;

/**
 * Message for handling MESSAGE type alerts.  This is a class to "mark"
 * the message as for MESSAGE type alerts.  There is no other difference
 * between these alerts and ALERT type alerts.
 *
 * @author cmatser
 */
public class MsgTypeAlertMessage extends TaskHandlerMessage
        implements java.io.Serializable {

    public MsgTypeAlertMessage(TaskHandlerMessage msg) {
        //Would be nice to check if TaskHandlerMessage has changed
        //  because this class needs to change with it
        setTaskTicket(msg.getTaskTicket());
        setTaskAttributes(msg.getTaskAttributes());
        setTaskID(msg.getTaskID());
        setTaskDescription(msg.getTaskDescription());
        setSubject(msg.getSubject());
        setMessage(msg.getMessage());
        setPriority(msg.getPriority());
        getProviders().addAll(msg.getProviders());
        getEscalationProviders().addAll(msg.getEscalationProviders());
        setEscalationMinutes(msg.getEscalationMinutes());
        setDeliveryDate(msg.getDeliveryDate());
        setCompletionDate(msg.getCompletionDate());
        setPatientUnitNumber(msg.getPatientUnitNumber());
        setPatientName(msg.getPatientName());
        setPatientSex(msg.getPatientSex());
        setPatientFMPSSN(msg.getPatientFMPSSN());
        setFactNCID(msg.getFactNCID());
        setFactName(msg.getFactName());
        setFactValue(msg.getFactValue());
        setFactType(msg.getFactType());
        setActionID(msg.getActionID());
        setActionType(msg.getActionType());
        setRuleID(msg.getRuleID());
        setRuleDesc(msg.getRuleDesc());
        setRuleName(msg.getRuleName());
    }
}
