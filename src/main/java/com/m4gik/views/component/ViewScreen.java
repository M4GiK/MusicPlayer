/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 1, 2013.
 */
package com.m4gik.views.component;

import com.vaadin.ui.Layout;

/**
 * Interface for building the proper view.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public interface ViewScreen {

    /**
     * 
     * Method to build proper view.
     * 
     * @return The layout to build the view.
     */
    public Layout build();
}
