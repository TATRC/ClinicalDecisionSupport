/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.hhs.fha.nhinc.account.dao;

import gov.hhs.fha.nhinc.account.model.PatientProviderQueryParams;
import gov.hhs.fha.nhinc.account.model.PatientProviderItem;
import gov.hhs.fha.nhinc.account.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Sushma
 */
public class PatientProviderItemDao {

    ObjectDao<PatientProviderItem> objectDao = new ObjectDao();
    Log log = LogFactory.getLog(PatientProviderItemDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param item Patient ProviderItem object to save.
     */
    public void save(PatientProviderItem item) {
        log.debug("Performing  Patient_Provider item save");

        try {
            //Update date
            item.setLastUpdated(new Date());

            objectDao.save(item);
        } catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed patient provider item save");
    }

    /**
     * Delete a patient provider item
     *
     * @param item Patient Provider Item to delete
     */
    public void delete(PatientProviderItem item) {
        log.debug("Performing patient provider item delete");

        try {
            objectDao.delete(item);
        } catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed patient provider item delete");
    }

    /**
     * Retrieve a Patient Provider item by identifier
     *
     * @param itemId Patient ProviderItem identifier
     * @return Retrieved Patient Provider item
     */
    public PatientProviderItem findById(Long patientProviderId) {
        PatientProviderItem item = null;

        log.debug("Performing patient provider item retrieve using id: " + patientProviderId);

        try {
            item = objectDao.findById(patientProviderId, PatientProviderItem.class);
        } catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed patient provider item retrieve by id");

        return item;
    }

    /**
     * Retrieves all patient provider item
     *
     * @return All patient provider item records
     */
    @SuppressWarnings("unchecked")
    public List<PatientProviderItem> findAll() {
        List<PatientProviderItem> items = null;

        log.debug("Performing retrieve of all patient Provider item");

        try {
            items = objectDao.findAll(PatientProviderItem.class);
        } catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed patient Provider item retrieve all");

        return items;
    }

    /**
     * Perform a query for patient provider item
     *
     * @param params Query parameters
     * @return Query results
     */
    @SuppressWarnings("unchecked")
    public List<PatientProviderItem> findPatient(PatientProviderQueryParams params) {
        log.debug("Beginning patient provider item query");

        Long patientId = null;
        String providerId = "";

        if (params != null) {
            patientId = params.getPatientId();
            providerId = params.getProviderId();
        }

        List<PatientProviderItem> items = null;
        Session sess = null;
        try {
            SessionFactory fact = HibernateUtil.getSessionFactory();
            if (fact != null) {
                sess = fact.openSession();
                if (sess != null) {
                    Criteria criteria = sess.createCriteria(PatientProviderItem.class);

                    if (patientId != null) {
                        if (log.isDebugEnabled()) {
                            log.debug("Patient Provider item query - patient provider item user id: " + patientId);
                        }
                        criteria.add(Restrictions.eq("patientId", patientId));
                    }

                    if (providerId != null) {
                        if (log.isDebugEnabled()) {
                            log.debug("Patient Provider item query - Patient Provider item provider id: " + providerId);
                        }
                        criteria.add(Restrictions.eq("providerId", providerId));
                    }

                    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                    items = criteria.list();
                } else {
                    log.error("Failed to obtain a session from the sessionFactory");
                }
            } else {
                log.error("Session factory was null");
            }
            if (log.isDebugEnabled()) {
                log.debug("Completed retrieve of Patient Provider item query. " + ((items == null) ? "0" : Integer.toString(items.size())) + " results returned.");
            }

        }
        finally {
            if (sess != null) {
                try {
                    sess.close();
                } catch (Throwable t) {
                    log.error("Failed to close session: " + t.getMessage(), t);
                }
            }
        }
        return items;
    }
}
