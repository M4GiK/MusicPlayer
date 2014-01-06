/**
 * Project Music Player.
 * Copyright Michał Szczygieł.
 * Created at Dec 2, 2013.
 */
package com.m4gik.views.utils;

import java.util.Observable;

/**
 * Class implements observer pattern.
 * 
 * @author m4gik <michal.szczygiel@wp.pl>
 * 
 */
public class ObservedObject extends Observable {

    private Boolean watchedValue;

    /**
     * Constructor of {@link ObservedObject}.
     * 
     * @param value
     */
    public ObservedObject(Boolean value) {
        this.watchedValue = value;
    }

    /**
     * This method adds the specified observer o to the set of observers for
     * this object if, the observer is not the same as some observer already in
     * the set.
     * 
     * @param value
     */
    public void setValue(Boolean value) {
        // if value has changed notify observers
        if (!watchedValue.equals(value)) {
            watchedValue = value;
        }
        setChanged(); // mark as value changed
        notifyObservers(value); // trigger notification
    }
}
