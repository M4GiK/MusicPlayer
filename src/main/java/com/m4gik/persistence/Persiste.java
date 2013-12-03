/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 3, 2013.
 */
package com.m4gik.persistence;

import org.hibernate.Session;

/**
 * Singleton class keeping the session for Hibernate.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class Persiste {

    private static volatile Persiste instance = null;

    private static Session session;

    /**
     * Double-checked locking Singleton. Private Constructor.
     * 
     * @return the instance
     */
    public static Persiste getInstance(Session session) {
        if (instance == null) {
            synchronized (Persiste.class) {
                // Double check
                if (instance == null) {
                    instance = new Persiste(session);
                }
            }
        }
        return instance;
    }

    /**
     * @return the session
     */
    public static Session getSession() {
        return session;
    }

    /**
     * Private constructor to avoid the automatic creation of a default public
     * constructor.
     */
    @SuppressWarnings("unused")
    private Persiste() {
    }

    /**
     * @param session
     */
    public Persiste(Session session) {
        setSession(session);
    }

    /**
     * @param session
     *            the session to set
     */
    public void setSession(Session session) {
        Persiste.session = session;
    }
}
