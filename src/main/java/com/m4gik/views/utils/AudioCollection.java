/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 3, 2013.
 */
package com.m4gik.views.utils;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.m4gik.persistence.Persiste;

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
     * 
     */
    public AudioCollection() {
        Session session = Persiste.getSession();
        SQLQuery query = session.createSQLQuery("SELECT * FROM MusicStore");
        List musicPlayerList = query.list();

        for (Iterator iter = musicPlayerList.iterator(); iter.hasNext();) {
            System.out.println("Kilka razy");
        }
    }
}
