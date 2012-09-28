/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaymanager.service;

import gov.hhs.fha.nhinc.displaymanager.dao.InboxStatusDao;
import gov.hhs.fha.nhinc.displaymanager.model.InboxStatus;
import gov.hhs.fha.nhinc.displaymanager.model.InboxStatusQueryParams;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author cmatser
 */
public class DisplayStatusService {

    private static Log log = LogFactory.getLog(DisplayStatusService.class);

    /**
     * Save a status record.
     *
     * @param status Status object to save.
     */
    public void saveInboxStatus(InboxStatus status)
    {
        log.debug("Saving a status");

        if (status != null)
        {
            if (status.getStatusId() != null)
            {
                log.debug("Performing an update for status: " + status.getStatusId());
            }
            else
            {
                log.debug("Performing an insert");
            }

        }

        InboxStatusDao dao = new InboxStatusDao();
        dao.save(status);
    }

    /**
     * Delete a status
     *
     * @param status InboxStatus to delete
     * @throws DisplayStatusException
     */
    public void deleteInboxStatus(InboxStatus status) throws DisplayStatusException
    {
        log.debug("Deleting a status");
        InboxStatusDao dao = new InboxStatusDao();

        if (status == null)
        {
            throw new DisplayStatusException("Status to delete was null");
        }

        dao.delete(status);
    }

    /**
     * Retrieve a status by identifier
     *
     * @param statusId Status identifier
     * @return Retrieved status
     */
    public InboxStatus getInboxStatus(Long statusId)
    {
        InboxStatusDao dao = new InboxStatusDao();
        return dao.findById(statusId);
    }

    /**
     * Retrieves all status
     *
     * @return All status records
     */
    public List<InboxStatus> getAllInboxStatus()
    {
        InboxStatusDao dao = new InboxStatusDao();
        return dao.findAll();
    }

    /**
     * Status query
     *
     * @param params Status query parameters
     * @return Query results
     */
    public List<InboxStatus> inboxStatusQuery(InboxStatusQueryParams params)
    {
        InboxStatusDao dao = new InboxStatusDao();
        return dao.findInboxStatus(params);
    }

}
