/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 2, 2013.
 */
package com.m4gik.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil class to take care of Hibernate start up and retrieve the
 * session easily.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = new Configuration()
            .configure().buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
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
