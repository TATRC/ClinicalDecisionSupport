/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.displaymanager.dao;

import gov.hhs.fha.nhinc.displaymanager.model.InboxStatus;
import gov.hhs.fha.nhinc.displaymanager.model.InboxStatusQueryParams;
import gov.hhs.fha.nhinc.displaymanager.util.HibernateUtil;
import java.util.List;
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
public class InboxStatusDao {

    ObjectDao<InboxStatus> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(InboxStatusDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param service InboxStatus object to save.
     */
    public void save(InboxStatus service) {
        log.debug("Performing save");

        try {
            objectDao.save(service);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed save");
    }

    /**
     * Delete a service
     *
     * @param service InboxStatus to delete
     */
    public void delete(InboxStatus service) {
        log.debug("Performing delete");

        try {
            objectDao.delete(service);
        }
        catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed delete");
    }

    /**
     * Retrieve a service by identifier
     *
     * @param alertServiceRefId InboxStatus identifier
     * @return Retrieved service
     */
    public InboxStatus findById(Long alertServiceRefId) {
        InboxStatus service = null;

        log.debug("Performing retrieve using id: " + alertServiceRefId);

        try {
            service = objectDao.findById(alertServiceRefId, InboxStatus.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed retrieve by id");

        return service;
    }

    /**
     * Retrieves all service
     *
     * @return All service records
     */
    @SuppressWarnings("unchecked")
    public List<InboxStatus> findAll() {
        List<InboxStatus> services = null;

        log.debug("Performing retrieve of all objects");

        try {
            services = objectDao.findAll(InboxStatus.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed retrieve all");

        return services;
    }

    /**
     * Perform a query
     *
     * @param params Query parameters
     * @return Query results
     */
    @SuppressWarnings("unchecked")
    public List<InboxStatus> findInboxStatus(InboxStatusQueryParams params) {
        log.debug("Beginning query");

        String user = null;
        String source = null;
        String item = null;

        if (params != null) {
            user = params.getUser();
            source = params.getSource();
            item = params.getItem();
        }

        List<InboxStatus> statusList = null;
        Session sess = null;
        try {
            SessionFactory fact = HibernateUtil.getSessionFactory();
            if (fact != null)
            {
                sess = fact.openSession();
                if (sess != null)
                {
                    Criteria criteria = sess.createCriteria(InboxStatus.class);

                    if (user != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("InboxStatus query - user: " + user);
                        }
                        criteria.add(Restrictions.eq("user", user));
                    }

                    if (source != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("InboxStatus query - source: " + source);
                        }
                        criteria.add(Restrictions.eq("source", source));
                    }

                    if (item != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("InboxStatus query - item: " + item);
                        }
                        criteria.add(Restrictions.eq("item", item));
                    }

                    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                    statusList = criteria.list();
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
                log.debug("Completed retrieve of inboxstatus query. " + ((statusList == null) ? "0" : Integer.toString(statusList.size())) + " results returned.");
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
        return statusList;
    }

}
