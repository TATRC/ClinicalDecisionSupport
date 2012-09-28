/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.alertmanager.dao;

import gov.hhs.fha.nhinc.alertmanager.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author cmatser
 */
public class ObjectDao<E> {

    /**
     * Save a record to the database.
     * Insert if id is null. Update otherwise.
     *
     * @param object object to save.
     */
    void save(E entity)
            throws Exception
    {
        Session sess = null;
        Transaction trans = null;
        try
        {
            SessionFactory fact = HibernateUtil.getSessionFactory();
            if (fact != null)
            {
                sess = fact.openSession();
                if (sess != null)
                {
                    trans = sess.beginTransaction();
                    sess.saveOrUpdate(entity);
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
            if (trans != null)
            {
                try
                {
                    trans.commit();
                }
                catch (Throwable t)
                {
                    throw new Exception("Failed to commit transaction: " + t.getMessage(), t);
                }
            }
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

    }

    /**
     * Delete an object
     *
     * @param object object to delete
     */
    void delete(E entity)
            throws Exception
    {
        Session sess = null;
        Transaction trans = null;
        try
        {
            SessionFactory fact = HibernateUtil.getSessionFactory();
            if (fact != null)
            {
                sess = fact.openSession();
                if (sess != null)
                {
                    trans = sess.beginTransaction();
                    sess.delete(entity);
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
            if (trans != null)
            {
                try
                {
                    trans.commit();
                }
                catch (Throwable t)
                {
                    throw new Exception("Failed to commit transaction: " + t.getMessage(), t);
                }
            }
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

    }

    /**
     * Retrieve a object by identifier
     *
     * @param entityId Entity identifier
     * @return Retrieved object
     */
    E findById(Long entityId, Class entityClass)
            throws Exception
    {
        E entity = null;
        Session sess = null;
        try
        {
            SessionFactory fact = HibernateUtil.getSessionFactory();
            if (fact != null)
            {
                sess = fact.openSession();
                if (sess != null)
                {
                    entity = (E) sess.get(entityClass, entityId);
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

        return entity;
    }

    /**
     * Retrieves all objects
     *
     * @return All object records
     */
    @SuppressWarnings("unchecked")
    List<E> findAll(Class entityClass)
            throws Exception
    {
        List<E> entities = null;
        Session sess = null;
        try
        {
            SessionFactory fact = HibernateUtil.getSessionFactory();
            if (fact != null)
            {
                sess = fact.openSession();
                if (sess != null)
                {
                    Criteria criteria = sess.createCriteria(entityClass);
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

