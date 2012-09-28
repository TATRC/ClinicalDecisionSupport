/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.model;

import java.util.Date;

/**
 *
 * @author Sushma
 */
public class AlertStatus {

    protected Long statusId;
    protected String userId;
    protected Date createTimeStamp;
    protected Boolean flagged;
    protected Boolean archive;
    protected AlertTicket ticket;

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public Date getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Date createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public AlertTicket getTicket() {
        return ticket;
    }

    public void setTicket(AlertTicket ticket) {
        this.ticket = ticket;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

}
