package com.piggymade.common;


import com.piggymade.model.DataEvent;

public interface PluginEvent<T> {
    void handleEvent(DataEvent<T> event);

}
