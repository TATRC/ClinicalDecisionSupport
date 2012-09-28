/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.dao;

import gov.hhs.fha.nhinc.alertmanager.model.AlertStatus;
import gov.hhs.fha.nhinc.alertmanager.model.AlertTicket;
import gov.hhs.fha.nhinc.alertmanager.model.TicketQueryParams;
import gov.hhs.fha.nhinc.alertmanager.util.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author cmatser
 */
public class AlertTicketDao {

    ObjectDao<AlertTicket> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(AlertTicketDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param ticket AlertTicket object to save.
     */
    public void save(AlertTicket ticket) {
        log.debug("Performing ticket save");

        try {
            objectDao.save(ticket);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed ticket save");
    }

    /**
     * Delete a ticket
     *
     * @param ticket AlertTicket to delete
     */
    public void delete(AlertTicket ticket) {
        log.debug("Performing ticket delete");

        try {
            objectDao.delete(ticket);
        }
        catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed ticket delete");
    }

    /**
     * Retrieve a ticket by identifier
     *
     * @param ticketId AlertTicket identifier
     * @return Retrieved ticket
     */
    public AlertTicket findById(Long ticketId) {
        AlertTicket ticket = null;

        log.debug("Performing ticket retrieve using id: " + ticketId);

        try {
            ticket = objectDao.findById(ticketId, AlertTicket.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed ticket retrieve by id");

        return ticket;
    }

    /**
     * Retrieves all tickets
     *
     * @return All ticket records
     */
    @SuppressWarnings("unchecked")
    public List<AlertTicket> findAll() {
        List<AlertTicket> tickets = null;

        log.debug("Performing retrieve of all tickets");

        try {
            tickets = objectDao.findAll(AlertTicket.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed ticket retrieve all");

        return tickets;
    }

    /**
     * Perform a query for tickets
     *
     * @param params Query parameters
     * @return Query results
     */
    @SuppressWarnings("unchecked")
    public List<AlertTicket> findTickets(TicketQueryParams params) {
        log.debug("Beginning ticket query");

        String ticketUniqueId = null;
        Integer escalationPeriodGT = null;
        String action = null;
        String actionUserId = null;
        String patientId = null;
        String type = null;
        Boolean archive = null;

        if (params != null) {
            ticketUniqueId = params.getTicketUniqueId();
            escalationPeriodGT = params.getEscalationPeriodGT();
            action = params.getAction();
            actionUserId = params.getActionUserId();
            patientId = params.getPatientId();
            type = params.getType();
            archive = params.isArchive();
        }

        List<AlertTicket> tickets = null;
        Session sess = null;
        try {
            SessionFactory fact = HibernateUtil.getSessionFactory();
            if (fact != null)
            {
                sess = fact.openSession();
                if (sess != null)
                {
                    Criteria criteria = sess.createCriteria(AlertTicket.class);

                    if (ticketUniqueId != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("Ticket query - ticket unique id: " + ticketUniqueId);
                        }
                        criteria.add(Restrictions.eq("ticketUniqueId", ticketUniqueId));
                    }

                    if (escalationPeriodGT != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("Ticket query - escalationPeriod greater than: " + escalationPeriodGT);
                        }
                        criteria.add(Restrictions.gt("escalationPeriod", escalationPeriodGT));
                    }

                    if (action != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("Ticket query - action: " + action);
                        }

                        criteria.createCriteria("actionHistory")
                            .add(Restrictions.eq("actionName", action));
                    }

                    if (actionUserId != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("Ticket query - action by user: " + actionUserId);
                        }

                        criteria.createCriteria("actionHistory")
                            .add(Restrictions.eq("userId", actionUserId));
                    }

                    if (patientId != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("Ticket query - patientId: " + patientId);
                        }

                        criteria.add(Restrictions.eq("patientUnitNumber", patientId));
                    }

                    if (type != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("Ticket query - type: " + type);
                        }

                        criteria.add(Restrictions.eq("type", type));
                    }

                    if (archive != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("Ticket Archive: " + archive.booleanValue());
                        }

                        criteria.createCriteria("status").add(Restrictions.and(Restrictions.eq("archive", archive),Restrictions.eq("userId", actionUserId)));

                    }

                    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                    tickets = criteria.list();
                }
                else
                {
                    log.error("Failed to obtain a session from the sessionFactory");
                }
            }
            else
            {
                log.error("Session factory was null");
            }
            if (log.isDebugEnabled())
            {
                log.debug("Completed retrieve of tickets query. " + ((tickets == null) ? "0" : Integer.toString(tickets.size())) + " results returned.");
            }
        }
        finally
        {
            if (sess != null)
            {
                try
                {
                    sess.close();
                }
                catch (Throwable t)
                {
                    log.error("Failed to close session: " + t.getMessage(), t);
                }
            }
        }
        return tickets;
    }

}

