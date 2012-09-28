/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.dao;

import gov.hhs.fha.nhinc.alertmanager.model.AlertContact;
import gov.hhs.fha.nhinc.alertmanager.util.HibernateUtil;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jharby
 */
public class AlertContactDao {
    
    ObjectDao<AlertContact> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(AlertContactDao.class);

    /**
     * Save a record to the AlertContact table in the database.
     * Insert if id is null. Update otherwise.
     *
     * @param alertContact AlertContact object to save.
     */
    public void save(AlertContact alertContact) {
        log.debug("Performing alertContact save");

        try {
            objectDao.save(alertContact);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed alertContact save");
    }

    /**
     * Retrieves all alertContacts
     *
     * @return All alertContact records
     */
    @SuppressWarnings("unchecked")
    public List<AlertContact> findAll() {
        List<AlertContact> alertContact = null;

        log.debug("Performing retrieve of all AlertContacts");

        try {
            alertContact = objectDao.findAll(AlertContact.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed AlertContact retrieve all");

        return alertContact;
    }


        /**
     * Retrieves all alertContacts
     *
     * @return All alertContact records
     */
    @SuppressWarnings("unchecked")
    public List<AlertContact> findAllByProvId(String providerId) {
        List<AlertContact> alertContacts = null;

        log.debug("Performing retrieve of all AlertContacts");

        try {
            alertContacts = findProviders(providerId);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed AlertContact retrieve all");

        return alertContacts;
    }

    private List<AlertContact> findProviders(String providerId) throws Exception {

        List<AlertContact> entities = null;
        Session sess = null;
        try
        {
            SessionFactory fact = HibernateUtil.getSessionFactory();
            if (fact != null)
            {
                sess = fact.openSession();
                if (sess != null)
                {
                    Criteria criteria = sess.createCriteria(AlertContact.class);
                    criteria.add( Restrictions.eq("providerId", providerId ) );
                    entities = criteria.list();
                }
                else
                {
                    throw new Exception("Failed to obtain a session from the sessionFactory");
                }
            }
            else
            {
                throw new Exception("Session factory was null");
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
                    throw new Exception("Failed to close session: " + t.getMessage(), t);
                }
            }
        }
        return entities;
    }

}
