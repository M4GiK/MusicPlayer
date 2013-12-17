/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 3, 2013.
 */
package com.m4gik.views.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.m4gik.database.MusicPlayer;
import com.m4gik.persistence.Persiste;
import com.vaadin.server.ExternalResource;

/**
 * Class represents collection for audio files.
 * 
 * Standard operation:
 * 
 * Session session = Persiste.getSession(); session.beginTransaction();
 * MusicPlayer musicPlayer = (MusicPlayer) session.get(MusicPlayer.class, new
 * Integer(1));
 * 
 * or
 * 
 * MusicPlayer musicplayer = new MusicPlayer(); musicplayer.setTitle("Jestem");
 * session.save(musicplayer); session.getTransaction().commit();
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class AudioCollection {

    /**
     * List contains all audio collection.
     */
    private ArrayList<AudioFile> audioCollection;

    /**
     * Constructor, gets data from database using Hibernate session.
     */
    public AudioCollection() {
        Session session = Persiste.getSession();

        List<MusicPlayer> musicPlayerList = session.createCriteria(
                MusicPlayer.class).list();

        audioCollection = new ArrayList<AudioFile>();
        for (MusicPlayer musicPlayer : musicPlayerList) {
            AudioFile audioFile = new AudioFile();
            audioFile.setTitle(musicPlayer.getTitle());
            audioFile.setAlbum(musicPlayer.getAlbum());
            audioFile.setPictureUrl(musicPlayer.getPictureUrl());
            audioFile.setArtist(musicPlayer.getArtist());
            audioFile.setTrackUrl(musicPlayer.getTrackUrl());
            audioFile.setAbout(musicPlayer.getAbout());
            audioFile.setPrice(musicPlayer.getPrice());
            audioFile.setLyrics(musicPlayer.getLyrics());
            audioFile.setGenre(musicPlayer.getGenre());
            if (!(musicPlayer.getPictureUrl() == null)) {
                audioFile.setCover(new ExternalResource(musicPlayer
                        .getPictureUrl()));
            }

            audioCollection.add(audioFile);
        }
    }

    /**
     * @return the audioCollection
     */
    public ArrayList<AudioFile> getAudioCollection() {
        return audioCollection;
    }

    /**
     * @param filter
     *            The filter to extract need music files.
     * @return The filtered audio files.
     */
    public ArrayList<AudioFile> getAudioCollection(String filter) {
        // TODO Create proper extracting method to get needs audio collection.
        return getAudioCollection();
    }

    /**
     * @param audioCollection
     *            the audioCollection to set
     */
    public void setAudioCollection(ArrayList<AudioFile> audioCollection) {
        this.audioCollection = audioCollection;
    }
}
