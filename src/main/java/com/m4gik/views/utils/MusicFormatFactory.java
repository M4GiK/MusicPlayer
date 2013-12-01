/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Nov 11, 2013.
 */
package com.m4gik.views.utils;

/**
 * Abstract Factory for audio formats. Gets proper format for audio object.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public interface MusicFormatFactory {

    public AudiFormat initialize();

}
