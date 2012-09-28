/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 *
* @author cmatser
 */
public class AlertTicket {

    private Long ticketId;
    private String ticketUniqueId;
    private Date alertTimestamp;
    private Integer escalationPeriod;
    private Set<AlertContact> providers;
    private Set<AlertEscalationContact> escalationProviders;
    private Set<AlertStatus> status;
    private Long alertId;
    private String type;
    private String description;
    private String alertOriginator;
    private String payload;
    private String priority;
    private String patientUnitNumber;
    private String patientName;
    private String patientSex;
    private String patientFMPSSN;
    private String factNCID;
    private String factName;
    private String factValue;
    private String factType;
    private String actionId;
    private String actionType;
    private String ruleId;
    private String ruleDesc;
    private String ruleName;
    private Set<AlertAction> actionHistory;

    /**
     * @return the ticketId
     */
    public Long getTicketId() {
        return ticketId;
    }

    /**
     * @param ticketId the ticketId to set
     */
    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * @return the ticketUniqueId
     */
    public String getTicketUniqueId() {
        return ticketUniqueId;
    }

    /**
     * @param ticketUniqueId the ticketUniqueId to set
     */
    public void setTicketUniqueId(String ticketUniqueId) {
        this.ticketUniqueId = ticketUniqueId;
    }

    /**
     * @return the alertTimestamp
     */
    public Date getAlertTimestamp() {
        return alertTimestamp;
    }

    /**
     * @param alertTimestamp the alertTimestamp to set
     */
    public void setAlertTimestamp(Date alertTimestamp) {
        this.alertTimestamp = alertTimestamp;
    }

    /**
     * @return the escalationPeriod
     */
    public Integer getEscalationPeriod() {
        return escalationPeriod;
    }

    /**
     * @param escalationPeriod the escalationPeriod to set
     */
    public void setEscalationPeriod(Integer escalationPeriod) {
        this.escalationPeriod = escalationPeriod;
    }

    /**
     * @return the providers
     */
    public Set<AlertContact> getProviders() {
        if (providers == null) {
            providers = new HashSet<AlertContact>();
        }

        return providers;
    }

    /**
     * @param providers the providers to set
     */
    public void setProviders(Set<AlertContact> providers) {
        this.providers = providers;
    }

    /**
     * @return the status
     */
    public Set<AlertStatus> getStatus() {
        if (status == null) {
            status = new HashSet<AlertStatus>();
        }

        return status;
    }

    /**
     * @param providers the status to set
     */
    public void setStatus(Set<AlertStatus> status) {
        this.status = status;
    }


    /**
     * @return the escalationProviders
     */
    public Set<AlertEscalationContact> getEscalationProviders() {
        if (escalationProviders == null) {
            escalationProviders = new HashSet<AlertEscalationContact>();
        }

        return escalationProviders;
    }

    /**
     * @param escalationProviders the escalationProviders to set
     */
    public void setEscalationProviders(Set<AlertEscalationContact> escalationProviders) {
        this.escalationProviders = escalationProviders;
    }

    /**
     * @return the alertId
     */
    public Long getAlertId() {
        return alertId;
    }

    /**
     * @param alertId the alertId to set
     */
    public void setAlertId(Long alertId) {
        this.alertId = alertId;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the payload
     */
    public String getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the alertOriginator
     */
    public String getAlertOriginator() {
        return alertOriginator;
    }

    /**
     * @param alertOriginator the alertOriginator to set
     */
    public void setAlertOriginator(String alertOriginator) {
        this.alertOriginator = alertOriginator;
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
     * @return the actionId
     */
    public String getActionId() {
        return actionId;
    }

    /**
     * @param actionId the actionId to set
     */
    public void setActionId(String actionId) {
        this.actionId = actionId;
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
     * @return the ruleId
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * @param ruleId the ruleId to set
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
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

    /**
     * @return the actionHistory
     */
    public Set<AlertAction> getActionHistory() {

        if (actionHistory == null) {
            actionHistory = new TreeSet<AlertAction>();
        }

        return actionHistory;
    }

    /**
     * @param actionHistory the actionHistory to set
     */
    public void setActionHistory(Set<AlertAction> actionHistory) {
        this.actionHistory = actionHistory;
    }

}
