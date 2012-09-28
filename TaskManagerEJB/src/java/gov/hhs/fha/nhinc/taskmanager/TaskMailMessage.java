/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager;

/**
 *
 * @author cmatser
 */
public class TaskMailMessage extends TaskMessage
        implements java.io.Serializable {

    private String fromUser;
    private boolean fromUserProvider;
    private String toUser;
    private boolean toUserProvider;
    private String subject;
    private String message;

    /**
     * @return the fromUser
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * @param fromUser the fromUser to set
     */
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public boolean isFromUserProvider() {
        return fromUserProvider;
    }

    public void setFromUserProvider(boolean fromUserProvider) {
        this.fromUserProvider = fromUserProvider;
    }

    /**
     * @return the toUser
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * @param toUser the toUser to set
     */
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public boolean isToUserProvider() {
        return toUserProvider;
    }

    public void setToUserProvider(boolean toUserProvider) {
        this.toUserProvider = toUserProvider;
    }

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
}
