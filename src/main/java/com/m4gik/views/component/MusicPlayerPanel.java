/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 17, 2013.
 */
package com.m4gik.views.component;

import java.util.Observable;
import java.util.Observer;

import com.m4gik.views.utils.AudioFile;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Audio;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

/**
 * Singleton class, for create only one instance of player music.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class MusicPlayerPanel implements Observer {

    private static Audio audio = null;

    private static AudioFile audioFile = null;

    private static volatile MusicPlayerPanel instance = null;

    private static VerticalLayout playerLayout;

    /**
     * This method creates image for cover.
     * 
     * @param cover
     *            The external cover for current track.
     * @return The image with set resource.
     */
    private static Image createImageCover(ExternalResource cover) {
        Image image = new Image(null, cover);
        image.setHeight("120px");
        image.setWidth("120px");

        return image;
    }

    /**
     * This method create panel to play
     */
    public static void createPanel() {
        getPlayerLayout().removeAllComponents();

        HorizontalLayout layout = new HorizontalLayout();
        layout.addComponent(createImageCover(audioFile.getCover()));

        Label space = new Label("");
        space.setWidth("20px");
        layout.addComponent(space);

        VerticalLayout vertLayout = new VerticalLayout();
        layout.addComponent(vertLayout);
        audio = new Audio();
        audio.setSource(new ExternalResource(audioFile.getTrackUrl()));

        Label title = new Label(audioFile.getTitle());
        title.addStyleName(Runo.LABEL_H1);
        title.setSizeFull();

        Label artist = new Label(audioFile.getArtist());
        artist.addStyleName(Runo.LABEL_H2);
        artist.setSizeFull();

        vertLayout.addComponent(title);
        vertLayout.addComponent(artist);
        vertLayout.addComponent(audio);

        // layout.addComponent(createPlayButton(audio));
        // layout.addComponent(createPauseButton(audio));

        getPlayerLayout().addComponent(layout);
        getPlayerLayout().setMargin(true);
    }

    /**
     * This method creates button to pause music also with action pause.
     * 
     * @param audio
     * @return button with set click lister.
     */
    private static Component createPauseButton(final Audio audio) {
        Button pause = new Button("Pause audio");
        pause.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                audio.pause();
            }
        });

        return pause;
    }

    /**
     * This method creates button to play music also with action play.
     * 
     * @param audio
     * @return button with set click lister.
     */
    private static Component createPlayButton(final Audio audio) {
        Button play = new Button("Play audio");
        play.addClickListener(new ClickListener() {

            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(ClickEvent event) {
                audio.play();
            }
        });

        return play;
    }

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
            createPanel();
        }
    }

    /**
     * @param audioFile
     */
    public static void setAudio(AudioFile audioFile) {

        if (MusicPlayerPanel.audioFile != audioFile) {
            MusicPlayerPanel.audioFile = audioFile;
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

    /**
     * This method updates play button. This method overrides an existing
     * method.
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable obj, Object arg) {
        if (arg.equals(true)) {
            audio.play();
        } else {
            audio.pause();
        }
    }

}
