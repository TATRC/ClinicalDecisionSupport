package gov.hhs.fha.nhinc.alertmanager.model;

import java.util.Set;

/**
 * Parameter object for ticket queries
 * 
 * @author cmatser
 */
public class TicketQueryParams
{
    private String ticketUniqueId;
    private Integer escalationPeriodGT;
    private String action;
    private String actionUserId;
    private String patientId;
    private String type;
    private boolean archive;
    private boolean folder;
    private Set<AlertContact> providerList;

    /**
     * @return the folder whether it belongs to starred folder or not
     */
    public boolean isFolder() {
        return folder;
    }

    /**
     * @param folder to set
     */

    public void setFolder(boolean folder) {
        this.folder = folder;
    }

    /**
     * @return the ticketUniqueId
     */
    public boolean isArchive() {
        return archive;
    }

    /**
     * @param archive to set
     */
    public void setArchive(boolean archive) {
        this.archive = archive;
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
     * @return the escalationPeriod
     */
    public Integer getEscalationPeriodGT() {
        return escalationPeriodGT;
    }

    /**
     * @param escalationPeriod the escalationPeriod to set
     */
    public void setEscalationPeriodGT(Integer escalationPeriodGT) {
        this.escalationPeriodGT = escalationPeriodGT;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the actionUserId
     */
    public String getActionUserId() {
        return actionUserId;
    }

    /**
     * @param actionUserId the actionUserId to set
     */
    public void setActionUserId(String actionUserId) {
        this.actionUserId = actionUserId;
    }

    /**
     * @return the patientId
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * @param patientId the patientId to set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
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

    public Set<AlertContact> getProviderList() {
        return providerList;
    }

    public void setProviderList(Set<AlertContact> providerList) {
        this.providerList = providerList;
    }

}