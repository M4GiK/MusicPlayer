/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 2, 2013.
 */
package com.m4gik.database;

import java.io.Serializable;

/**
 * Model class for MusicPlayer
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class MusicPlayer implements Serializable {

    /**
     * Auto generated serial version UID.
     */
    private static final long serialVersionUID = -6073079985877219290L;

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
     * Field contains genre for track.
     */
    private String genre;

    /**
     * The identity for track.
     */
    private Integer id;

    /**
     * Field contains lyrics for track.
     */
    private String lyrics;

    /**
     * Field contains url for picture of track.
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
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
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
     * @param genre
     *            the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
