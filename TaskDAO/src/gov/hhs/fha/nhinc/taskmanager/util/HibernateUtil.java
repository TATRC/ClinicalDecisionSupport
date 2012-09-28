/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.taskmanager.util;


import gov.hhs.fha.nhinc.properties.HibernateAccessor;
import java.io.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author cmatser
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    private static Log log = LogFactory.getLog(HibernateUtil.class);
    private static final String HIBERNATE_TASK_REPOSITORY = "taskmanager.hibernate.cfg.xml";

    static {
        try {
            // Create the SessionFactory from supplied config file.
            sessionFactory = new Configuration().configure(getConfigFile()).buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static File getConfigFile(){
        File result = null;

        try
        {
            result = HibernateAccessor.getHibernateFile(HIBERNATE_TASK_REPOSITORY);
        }
        catch (Exception ex)
        {
            log.error("Unable to load " + HIBERNATE_TASK_REPOSITORY+ " " + ex.getMessage(), ex );
        }

        return result;
    }

}
