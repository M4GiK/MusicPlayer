/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Nov 11, 2013.
 */
package com.m4gik.views.utils;

import com.vaadin.ui.Audio;

/**
 * Interface to be implemented by components wishing to initialize WMV format.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public interface WMVFormat {

    /**
     * Methods to be implemented, initialize proper file with proper extension.
     * 
     * @return Prepared file with proper extension.
     */
    public Audio getWMVExtension();
}
