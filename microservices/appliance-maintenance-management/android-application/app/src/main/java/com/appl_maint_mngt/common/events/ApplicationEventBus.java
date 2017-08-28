package com.appl_maint_mngt.common.events;

import java.util.Observable;

/**
 * Created by Kyle on 07/04/2017.
 */

public class ApplicationEventBus extends Observable implements IEventBus {
    private static final ApplicationEventBus ourInstance = new ApplicationEventBus();

    public static ApplicationEventBus getInstance() {
        return ourInstance;
    }

    private ApplicationEventBus() {
    }

    public void sendEvent(String event) {
        updateObservers(event);
    }
    private void updateObservers(String updateType) {
        setChanged();
        notifyObservers(updateType);
        hasChanged();
    }
}
