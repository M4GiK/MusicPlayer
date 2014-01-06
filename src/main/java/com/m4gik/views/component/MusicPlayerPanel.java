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
     * This method checks if {@link MusicPlayerPanel} is already running.
     * 
     * @return true if is running, or false if is not
     */
    public static Boolean isRunning() {
        return instance != null ? true : false;
    }

    /**
     * This method run default settings for music player.
     */
    public static void runDefaultSetup() {
        if (instance != null) {
            MusicPlayerPanel.playerLayout.setHeight("200px");
        }
    }

    /**
     * @param playerLayout
     *            the playerLayout to set
     */
    public static void setPlayerLayout(VerticalLayout playerLayout) {
        MusicPlayerPanel.playerLayout = playerLayout;
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

}
