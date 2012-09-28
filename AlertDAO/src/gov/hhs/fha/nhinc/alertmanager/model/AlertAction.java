/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.model;

import java.util.Date;

/**
 *
 * @author cmatser
 */
public class AlertAction
        implements Comparable<AlertAction> {

    private Long actionId;
    private String actionName;
    private String userId;
    private String userName;
    private Boolean userProvider;
    private String message;
    private Date actionTimestamp;
    private AlertTicket ticket;

    /**
     * @return the actionId
     */
    public Long getActionId() {
        return actionId;
    }

    /**
     * @param actionId the actionId to set
     */
    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the userProvider
     */
    public Boolean getUserProvider() {
        return userProvider;
    }

    /**
     * @param userProvider the userProvider to set
     */
    public void setUserProvider(Boolean userProvider) {
        this.userProvider = userProvider;
    }

    /**
     * @return the actionName
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * @param actionName the actionName to set
     */
    public void setActionName(String actionName) {
        this.actionName = actionName;
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
     * @return the actionTimestamp
     */
    public Date getActionTimestamp() {
        return actionTimestamp;
    }

    /**
     * @param actionTimestamp the actionTimestamp to set
     */
    public void setActionTimestamp(Date actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }

    /**
     * @return the ticket
     */
    public AlertTicket getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(AlertTicket ticket) {
        this.ticket = ticket;
    }

    /**
     * 
     * @param that
     * @return
     * @exception NullPointerException if actionTimestamp or actionName is null.
     */
    public int compareTo(AlertAction that) {

        int compareTo = actionTimestamp.compareTo(that.getActionTimestamp());

        //If dates are equal, check action id (db generated incrementer)
        if (compareTo == 0) {
            return that.getActionId().compareTo(actionId);
        }

        return compareTo;
    }
}