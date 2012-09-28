/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Task message for task handler processing.
 *
 * @author cmatser
 */
public class TaskRuleMessage extends TaskMessage
        implements java.io.Serializable {
    private String subject;
    private String message;
    private String priority;
    private List<TaskContact> providers;
    private List<TaskContact> escalationProviders;
    private Integer escalationMinutes;
    private Date deliveryDate;
    private Date completionDate;
    private String patientUnitNumber;
    private String patientName;
    private String patientSex;
    private String patientFMPSSN;
    private String factNCID;
    private String factName;
    private String factValue;
    private String factType;
    private String actionID;
    private String actionType;
    private String ruleID;
    private String ruleDesc;
    private String ruleName;

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * @return the providers
     */
    public List<TaskContact> getProviders() {
        if (providers == null) {
            providers = new LinkedList<TaskContact>();
        }

        return providers;
    }

    /**
     * @param providers the providers to set
     */
    public void setProviders(List<TaskContact> providers) {
        this.providers = providers;
    }

    /**
     * @return the escalationProviders
     */
    public List<TaskContact> getEscalationProviders() {
        if (escalationProviders == null) {
            escalationProviders = new LinkedList<TaskContact>();
        }

        return escalationProviders;
    }

    /**
     * @param escalationProviders the escalationProviders to set
     */
    public void setEscalationProviders(List<TaskContact> escalationProviders) {
        this.escalationProviders = escalationProviders;
    }

    /**
     * @return the escalationMinutes
     */
    public Integer getEscalationMinutes() {
        return escalationMinutes;
    }

    /**
     * @param escalationMinutes the escalationMinutes to set
     */
    public void setEscalationMinutes(Integer escalationMinutes) {
        this.escalationMinutes = escalationMinutes;
    }

    /**
     * @return the deliveryDate
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * @return the completionDate
     */
    public Date getCompletionDate() {
        return completionDate;
    }

    /**
     * @param completionDate the completionDate to set
     */
    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * @return the patientUnitNumber
     */
    public String getPatientUnitNumber() {
        return patientUnitNumber;
    }

    /**
     * @param patientUnitNumber the patientUnitNumber to set
     */
    public void setPatientUnitNumber(String patientUnitNumber) {
        this.patientUnitNumber = patientUnitNumber;
    }

    /**
     * @return the patientName
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * @param patientName the patientName to set
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * @return the patientSex
     */
    public String getPatientSex() {
        return patientSex;
    }

    /**
     * @param patientSex the patientSex to set
     */
    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    /**
     * @return the patientFMPSSN
     */
    public String getPatientFMPSSN() {
        return patientFMPSSN;
    }

    /**
     * @param patientFMPSSN the patientFMPSSN to set
     */
    public void setPatientFMPSSN(String patientFMPSSN) {
        this.patientFMPSSN = patientFMPSSN;
    }

    /**
     * @return the factNCID
     */
    public String getFactNCID() {
        return factNCID;
    }

    /**
     * @param factNCID the factNCID to set
     */
    public void setFactNCID(String factNCID) {
        this.factNCID = factNCID;
    }

    /**
     * @return the factName
     */
    public String getFactName() {
        return factName;
    }

    /**
     * @param factName the factName to set
     */
    public void setFactName(String factName) {
        this.factName = factName;
    }

    /**
     * @return the factValue
     */
    public String getFactValue() {
        return factValue;
    }

    /**
     * @param factValue the factValue to set
     */
    public void setFactValue(String factValue) {
        this.factValue = factValue;
    }

    /**
     * @return the factType
     */
    public String getFactType() {
        return factType;
    }

    /**
     * @param factType the factType to set
     */
    public void setFactType(String factType) {
        this.factType = factType;
    }

    /**
     * @return the actionID
     */
    public String getActionID() {
        return actionID;
    }

    /**
     * @param actionID the actionID to set
     */
    public void setActionID(String actionID) {
        this.actionID = actionID;
    }

    /**
     * @return the actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @param actionType the actionType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * @return the ruleID
     */
    public String getRuleID() {
        return ruleID;
    }

    /**
     * @param ruleID the ruleID to set
     */
    public void setRuleID(String ruleID) {
        this.ruleID = ruleID;
    }

    /**
     * @return the ruleDesc
     */
    public String getRuleDesc() {
        return ruleDesc;
    }

    /**
     * @param ruleDesc the ruleDesc to set
     */
    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    /**
     * @return the ruleName
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * @param ruleName the ruleName to set
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

}