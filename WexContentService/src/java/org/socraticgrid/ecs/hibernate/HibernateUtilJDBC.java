/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.socraticgrid.ecs.hibernate;


import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author Jerry Goodnough
 */
public class HibernateUtilJDBC {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate_jdbc.cfg
            // config file.
            sessionFactory = new AnnotationConfiguration().configure("hibernate_jdbc.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

      public static void shutdown() {
      // Close caches and connection pools
      getSessionFactory().close();
  }

}
