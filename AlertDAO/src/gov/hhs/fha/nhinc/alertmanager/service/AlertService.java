/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.service;

import gov.hhs.fha.nhinc.alertmanager.dao.AlertActionDao;
import gov.hhs.fha.nhinc.alertmanager.dao.AlertContactDao;
import gov.hhs.fha.nhinc.alertmanager.dao.AlertServiceRefDao;
import gov.hhs.fha.nhinc.alertmanager.dao.AlertSpecDao;
import gov.hhs.fha.nhinc.alertmanager.dao.AlertStatusDao;
import gov.hhs.fha.nhinc.alertmanager.dao.AlertTicketDao;
import gov.hhs.fha.nhinc.alertmanager.dao.AlertTypeDao;
import gov.hhs.fha.nhinc.alertmanager.model.AlertAction;
import gov.hhs.fha.nhinc.alertmanager.model.AlertContact;
import gov.hhs.fha.nhinc.alertmanager.model.AlertServiceRef;
import gov.hhs.fha.nhinc.alertmanager.model.AlertSpec;
import gov.hhs.fha.nhinc.alertmanager.model.AlertStatus;
import gov.hhs.fha.nhinc.alertmanager.model.AlertTicket;
import gov.hhs.fha.nhinc.alertmanager.model.AlertType;
import gov.hhs.fha.nhinc.alertmanager.model.TicketQueryParams;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * @author cmatser
 */
public class AlertService {

    private static Log log = LogFactory.getLog(AlertService.class);

    private static List<String> validActions;

    /**
     * Returns a list of valid actions.
     *
     * @return
     */
    public static List<String> getValidActions() {

        if (validActions == null) {
            validActions = new ArrayList<String>();
            validActions.add(ActionConstants.ACTION_ACCEPT);
            validActions.add(ActionConstants.ACTION_ACKNOWLEDGE);
            validActions.add(ActionConstants.ACTION_DISCARD);
            validActions.add(ActionConstants.ACTION_ESCALATE);
            validActions.add(ActionConstants.ACTION_MANUAL_ESCALATE);
            validActions.add(ActionConstants.ACTION_MODIFY);
            validActions.add(ActionConstants.ACTION_HOLD);
            validActions.add(ActionConstants.ACTION_READ);
            validActions.add(ActionConstants.ACTION_REJECT);
        }

        return validActions;
    }

    /**
     * Save a alert record.
     *
     * @param alert Alert object to save.
     */
    public void saveAlert(AlertType alert)
    {
        log.debug("Saving a alert");

        if (alert != null)
        {
            if (alert.getAlertTypeId() != null)
            {
                if(log.isDebugEnabled())
                {
                    log.debug("Performing an update for alert: " + alert.getAlertTypeId());
                }

                AlertType eAlert = getAlert(alert.getAlertTypeId());
                if (eAlert != null)
                {
                    // Delete existing specifications
                    Set<AlertSpec> specs = eAlert.getSpecifications();
                    if ((specs != null) && !specs.isEmpty())
                    {
                        AlertSpecDao specDao = new AlertSpecDao();
                        for (AlertSpec spec : specs)
                        {
                            specDao.delete(spec);
                            spec.setSpecificationId(null);
                        }
                    }

                    // Reset specification identifiers
                    specs = alert.getSpecifications();
                    if ((specs != null) && !specs.isEmpty())
                    {
                        for (AlertSpec spec : specs)
                        {
                                spec.setSpecificationId(null);
                        }
                    }
                }
            }
            else
            {
                log.debug("Performing an insert");
            }

        }

        AlertTypeDao dao = new AlertTypeDao();
        dao.save(alert);
    }

    /**
     * Delete a alert
     *
     * @param alert AlertType to delete
     * @throws AlertServiceException
     */
    public void deleteAlert(AlertType alert) throws AlertServiceException
    {
        log.debug("Deleting a alert");
        AlertTypeDao dao = new AlertTypeDao();

        if (alert == null)
        {
            throw new AlertServiceException("Alert to delete was null");
        }

        dao.delete(alert);
    }

    /**
     * Retrieve a alert by identifier
     *
     * @param alertTypeId Alert identifier
     * @return Retrieved alert
     */
    public AlertType getAlert(Long alertTypeId)
    {
        AlertTypeDao dao = new AlertTypeDao();
        return dao.findById(alertTypeId);
    }

    /**
     * Retrieves all alerts
     *
     * @return All alert records
     */
    public List<AlertType> getAllAlerts()
    {
        AlertTypeDao dao = new AlertTypeDao();
        return dao.findAll();
    }

    public List<AlertTicket> getTicketsByParams(TicketQueryParams params) {
        AlertTicketDao dao = new AlertTicketDao();
        return dao.findTickets(params);
    }

    /**
     * Save a alert service record.
     *
     * @param alertService Alert object to save.
     */
    public void saveServiceRef(AlertServiceRef serviceRef)
    {
        log.debug("Saving a service ref.");

        if (serviceRef != null)
        {
            if (serviceRef.getAlertServiceRefId() != null)
            {
                if(log.isDebugEnabled())
                {
                    log.debug("Performing an update for alert service ref: " + serviceRef.getAlertServiceRefId());
                }
            }
            else
            {
                log.debug("Performing an insert");
            }

        }

        AlertServiceRefDao dao = new AlertServiceRefDao();
        dao.save(serviceRef);
    }

    /**
     * Delete a alert service record
     *
     * @param serviceRef AlertServiceRef to delete
     * @throws AlertServiceException
     */
    public void deleteServiceRef(AlertServiceRef serviceRef) throws AlertServiceException
    {
        log.debug("Deleting a alert service ref");
        AlertServiceRefDao dao = new AlertServiceRefDao();

        if (serviceRef == null)
        {
            throw new AlertServiceException("Alert service ref to delete was null");
        }

        dao.delete(serviceRef);
    }

    /**
     * Retrieve a alert service ref by identifier
     *
     * @param alertServiceRefId Alert identifier
     * @return Retrieved alert service
     */
    public AlertServiceRef getServiceRef(Long alertServiceRefId)
    {
        AlertServiceRefDao dao = new AlertServiceRefDao();
        return dao.findById(alertServiceRefId);
    }

    /**
     * Retrieves all alert service refs
     *
     * @return All alert service ref records
     */
    public List<AlertServiceRef> getAllServiceRefs()
    {
        AlertServiceRefDao dao = new AlertServiceRefDao();
        return dao.findAll();
    }

    /**
     * Save a ticket record.
     *
     * @param ticket Ticket object to save.
     */
    public void saveTicket(AlertTicket ticket)
    {
        log.debug("Saving a ticket");

        if (ticket != null)
        {
            if (ticket.getTicketId() != null)
            {
                if(log.isDebugEnabled())
                {
                    log.debug("Performing an update for ticket: " + ticket.getTicketUniqueId());
                }

                AlertTicket eTicket = getTicket(ticket.getTicketId());
                if (eTicket != null)
                {
                    // Delete existing actions
                    Set<AlertAction> actions = eTicket.getActionHistory();
                    if ((actions != null) && !actions.isEmpty())
                    {
                        AlertActionDao actionDao = new AlertActionDao();
                        for (AlertAction action : actions)
                        {
                            actionDao.delete(action);
                            action.setActionId(null);
                        }
                    }

                    // Reset action identifiers
                    actions = ticket.getActionHistory();
                    if ((actions != null) && !actions.isEmpty())
                    {
                        for (AlertAction action : actions)
                        {
                                action.setActionId(null);
                        }
                    }

                    // Delete existing statuses
                    Set<AlertStatus> statuses = eTicket.getStatus();
                    if ((actions != null) && !actions.isEmpty())
                    {
                        AlertStatusDao statusDao = new AlertStatusDao();
                        for (AlertStatus status  : statuses)
                        {
                            statusDao.delete(status);
                            status.setStatusId(null);
                        }
                    }

                    // Reset status identifiers
                    statuses = ticket.getStatus();
                    if ((statuses != null) && !statuses.isEmpty())
                    {
                        for (AlertStatus status : statuses)
                        {
                                status.setStatusId(null);
                        }
                    }
                }
            }
            else
            {
                log.debug("Performing an insert");
            }

        }

        AlertTicketDao dao = new AlertTicketDao();
        dao.save(ticket);
    }

    /**
     * Delete a ticket
     *
     * @param ticket AlertTicket to delete
     * @throws AlertServiceException
     */
    public void deleteTicket(AlertTicket ticket) throws AlertServiceException
    {
        log.debug("Deleting a ticket");
        AlertTicketDao dao = new AlertTicketDao();

        if (ticket == null)
        {
            throw new AlertServiceException("Ticket to delete was null");
        }

        dao.delete(ticket);
    }

    /**
     * Retrieve a ticket by identifier
     *
     * @param ticketId Alert identifier
     * @return Retrieved ticket
     */
    public AlertTicket getTicket(Long ticketId)
    {
        AlertTicketDao dao = new AlertTicketDao();
        return dao.findById(ticketId);
    }

    /**
     * Retrieves all tickets
     *
     * @return All ticket records
     */
    public List<AlertTicket> getAllTickets()
    {
        AlertTicketDao dao = new AlertTicketDao();
        return dao.findAll();
    }

    /**
     * Ticket query
     *
     * @param params Ticket query parameters
     * @return Query results
     */
    public List<AlertTicket> ticketQuery(TicketQueryParams params)
    {
        AlertTicketDao dao = new AlertTicketDao();
        return dao.findTickets(params);
    }

    public List<AlertContact> getContactsByProvider(String providerId) {
        AlertContactDao dao = new AlertContactDao();
        List<AlertContact> contactList = dao.findAllByProvId(providerId);
        return contactList;
    }

}
