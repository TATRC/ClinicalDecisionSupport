/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaymanager.model;

/**
 *
 * @author cmatser
 */
public class InboxStatus {
    private Long statusId;
    private String user;
    private String source;
    private String item;
    private boolean read;

    /**
     * @return the statusId
     */
    public Long getStatusId() {
        return statusId;
    }

    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the read
     */
    public boolean isRead() {
        return read;
    }

    /**
     * @param read the read to set
     */
    public void setRead(boolean read) {
        this.read = read;
    }

}