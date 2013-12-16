/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 3, 2013.
 */
package com.m4gik.views.utils;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Audio;

/**
 * Class represents the Audio component translates into an HTML5 element and as
 * such is only supported in browsers that support HTML5 media markup.
 * 
 * Multiple sources can be specified. Which of the sources is used is selected
 * by the browser depending on which file formats it supports. See <a
 * href="http://en.wikipedia.org/wiki/HTML5_video#Table">wikipedia</a> for a
 * table of formats supported by different browsers.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class AudioFile extends Audio implements AudioFormat {

    /**
     * Code represents MP3 format extension.
     */
    public static final String MP3 = "mp3";

    /**
     * Code represents OGG format extension.
     */
    public static final String OGG = "ogg";

    /**
     * Auto generated serial version UID.
     */
    private static final long serialVersionUID = 4636779334776641321L;

    /**
     * Code represents WMV format extension.
     */
    public static final String WMV = "wmv";

    /**
     * Field contains information about track.
     */
    private String about;

    /**
     * Field contains name for album.
     */
    private String album;

    /**
     * Field contains name for artist.
     */
    private String artist;

    /**
     * Field contains external resource to picture of track/album.
     */
    private ExternalResource cover;

    /**
     * Field contains genre for track.
     */
    private String genre;

    /**
     * Field contains lyrics for track.
     */
    private String lyrics;

    /**
     * Field contains url for picture of track/album.
     */
    private String pictureUrl;

    /**
     * Field contains price for track.
     */
    private Float price;

    /**
     * Field contains title for track.
     */
    private String title;

    /**
     * Field contains url for audio of track.
     */
    private String trackUrl;

    /**
     * Method convert Audio to needed version.
     * 
     * @param trackUrl
     * @return Converted Audio.
     */
    private AudioFormat convertFile(String trackUrl, String exceptedFormat) {
        // TODO: To implement converting audio sources.
        return null;
    }

    /**
     * @return the about
     */
    public String getAbout() {
        return about;
    }

    /**
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * @return the cover
     */
    public ExternalResource getCover() {
        return cover;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @return the lyrics
     */
    public String getLyrics() {
        return lyrics;
    }

    /**
     * @return the pictureUrl
     */
    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the trackUrl
     */
    public String getTrackUrl() {
        return trackUrl;
    }

    /**
     * @see com.m4gik.views.utils.AudioFormat#initMP3()
     */
    @Override
    public MP3Format initMP3() {
        MP3Format mp3File = new MP3Format() {

            @Override
            public Audio getMP3Extension() {
                Audio audioFile = new Audio();

                String[] fileName = getTrackUrl().split(".");

                if (fileName[fileName.length].equalsIgnoreCase(MP3)) {
                    audioFile.setSource(new ExternalResource(getTrackUrl()));
                } else {
                    convertFile(getTrackUrl(), MP3);
                }

                return audioFile;
            }
        };
        return mp3File;
    }

    /**
     * @see com.m4gik.views.utils.AudioFormat#initOGG()
     */
    @Override
    public OGGFormat initOGG() {
        OGGFormat oggFile = new OGGFormat() {

            @Override
            public Audio getOGGExtension() {
                Audio audioFile = new Audio();

                String[] fileName = getTrackUrl().split(".");

                if (fileName[fileName.length].equalsIgnoreCase(OGG)) {
                    audioFile.setSource(new ExternalResource(getTrackUrl()));
                } else {
                    convertFile(getTrackUrl(), OGG);
                }

                return audioFile;
            }
        };

        return oggFile;
    }

    /**
     * @see com.m4gik.views.utils.AudioFormat#initWMV()
     */
    @Override
    public WMVFormat initWMV() {
        WMVFormat wmvFile = new WMVFormat() {

            @Override
            public Audio getWMVExtension() {
                Audio audioFile = new Audio();

                String[] fileName = getTrackUrl().split(".");

                if (fileName[fileName.length].equalsIgnoreCase(WMV)) {
                    audioFile.setSource(new ExternalResource(getTrackUrl()));
                } else {
                    convertFile(getTrackUrl(), WMV);
                }

                return audioFile;
            }
        };
        return wmvFile;
    }

    /**
     * @param about
     *            the about to set
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     * @param album
     *            the album to set
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * @param artist
     *            the artist to set
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * @param cover
     *            the cover to set
     */
    public void setCover(ExternalResource cover) {
        this.cover = cover;
    }

    /**
     * @param genre
     *            the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @param lyrics
     *            the lyrics to set
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    /**
     * @param pictureUrl
     *            the pictureUrl to set
     */
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param trackUrl
     *            the trackUrl to set
     */
    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }

}
