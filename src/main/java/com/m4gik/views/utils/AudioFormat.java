/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Nov 11, 2013.
 */
package com.m4gik.views.utils;

/**
 * Abstract Factory for music formats. Gets proper format for audio object.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public interface AudioFormat {

    /**
     * Method creates MP3 file.
     * 
     * @return The MP3 with proper extension.
     */
    public MP3Format initMP3();

    /**
     * Method creates OGG file.
     * 
     * @return The OGG with proper extension.
     */
    public OGGFormat initOGG();

    /**
     * Method creates WMV file.
     * 
     * @return The WMV with proper extension.
     */
    public WMVFormat initWMV();

}
