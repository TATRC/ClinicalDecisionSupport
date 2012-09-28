/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager;

/**
 *
 * @author cmatser
 */
public class EscalationMessage
        implements java.io.Serializable {

    private String ticket;
    private boolean manual= false;
    private String actionMessage;
    private String userId;
    private String userName;
    private boolean userProvider;

    /**
     * @return the ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * @return the manual
     */
    public boolean isManual() {
        return manual;
    }

    /**
     * @param manual the manual to set
     */
    public void setManual(boolean manual) {
        this.manual = manual;
    }

    /**
     * @return the actionMessage
     */
    public String getActionMessage() {
        return actionMessage;
    }

    /**
     * @param actionMessage the actionMessage to set
     */
    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
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
    public boolean isUserProvider() {
        return userProvider;
    }

    /**
     * @param userProvider the userProvider to set
     */
    public void setUserProvider(boolean userProvider) {
        this.userProvider = userProvider;
    }

}