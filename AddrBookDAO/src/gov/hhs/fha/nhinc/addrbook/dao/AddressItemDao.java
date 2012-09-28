/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.addrbook.dao;

import gov.hhs.fha.nhinc.addrbook.model.AddressQueryParams;
import gov.hhs.fha.nhinc.addrbook.model.AddressItem;
import gov.hhs.fha.nhinc.addrbook.util.HibernateUtil;
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
 * @author cmatser
 */
public class AddressItemDao {

    ObjectDao<AddressItem> objectDao = new ObjectDao();

    Log log = LogFactory.getLog(AddressItemDao.class);

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param item AddressItem object to save.
     */
    public void save(AddressItem item) {
        log.debug("Performing address item save");

        try {
            //Update date
            item.setLastUpdated(new Date());

            objectDao.save(item);
        }
        catch (Throwable t) {
            log.error("Failure during object save.", t);
        }

        log.debug("Completed address item save");
    }

    /**
     * Delete a address item
     *
     * @param item AddressItem to delete
     */
    public void delete(AddressItem item) {
        log.debug("Performing address item delete");

        try {
            objectDao.delete(item);
        }
        catch (Throwable t) {
            log.error("Failure during object delete.", t);
        }

        log.debug("Completed address item delete");
    }

    /**
     * Retrieve a address item by identifier
     *
     * @param itemId AddressItem identifier
     * @return Retrieved address item
     */
    public AddressItem findById(Long itemId) {
        AddressItem item = null;

        log.debug("Performing address item retrieve using id: " + itemId);

        try {
            item = objectDao.findById(itemId, AddressItem.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findById", t);
        }

        log.debug("Completed address item retrieve by id");

        return item;
    }

    /**
     * Retrieves all address item
     *
     * @return All address item records
     */
    @SuppressWarnings("unchecked")
    public List<AddressItem> findAll() {
        List<AddressItem> items = null;

        log.debug("Performing retrieve of all address item");

        try {
            items = objectDao.findAll(AddressItem.class);
        }
        catch (Throwable t) {
            log.error("Failure during object findAll", t);
        }

        log.debug("Completed address item retrieve all");

        return items;
    }

    /**
     * Perform a query for address item
     *
     * @param params Query parameters
     * @return Query results
     */
    @SuppressWarnings("unchecked")
    public List<AddressItem> findAddresses(AddressQueryParams params) {
        log.debug("Beginning address item query");

        String userId = null;
        String classId = null;

        if (params != null) {
            userId = params.getUserId();
            classId = params.getClassId();
        }

        List<AddressItem> items = null;
        Session sess = null;
        try {
            SessionFactory fact = HibernateUtil.getSessionFactory();
            if (fact != null)
            {
                sess = fact.openSession();
                if (sess != null)
                {
                    Criteria criteria = sess.createCriteria(AddressItem.class);

                    if (userId != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("Address item query - address item user id: " + userId);
                        }
                        criteria.add(Restrictions.eq("userId", userId));
                    }

                    if (classId != null)
                    {
                        if (log.isDebugEnabled())
                        {
                            log.debug("Address item query - address item class id: " + classId);
                        }
                        criteria.add(Restrictions.eq("classId", classId));
                    }

                    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
                    items = criteria.list();
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
                log.debug("Completed retrieve of address item query. " + ((items == null) ? "0" : Integer.toString(items.size())) + " results returned.");
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
        return items;
    }

}

