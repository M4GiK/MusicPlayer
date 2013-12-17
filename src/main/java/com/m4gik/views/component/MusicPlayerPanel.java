/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 17, 2013.
 */
package com.m4gik.views.component;

import com.vaadin.ui.VerticalLayout;

/**
 * Singleton class, for create only one instance of player music.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class MusicPlayerPanel {

    private static volatile MusicPlayerPanel instance = null;

    private static VerticalLayout playerLayout;

    /**
     * Double-checked locking Singleton. Private Constructor.
     * 
     * @return the instance
     */
    public static MusicPlayerPanel getInstance(VerticalLayout playerLayout) {
        if (instance == null) {
            synchronized (MusicPlayerPanel.class) {
                // Double check
                if (instance == null) {
                    instance = new MusicPlayerPanel(playerLayout);
                }
            }
        }
        return instance;
    }

    /**
     * @return the playerLayout
     */
    public static VerticalLayout getPlayerLayout() {
        return playerLayout;
    }

    /**
     * Private constructor to avoid the automatic creation of a default public
     * constructor.
     */
    @SuppressWarnings("unused")
    private MusicPlayerPanel() {
    }

    /**
     * @param session
     */
    public MusicPlayerPanel(VerticalLayout playerLayout) {
        setPlayerLayout(playerLayout);
    }

    /**
     * @param playerLayout
     *            the playerLayout to set
     */
    public void setPlayerLayout(VerticalLayout playerLayout) {
        MusicPlayerPanel.playerLayout = playerLayout;
    }

}
