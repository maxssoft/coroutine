package com.example.kotlindemo.slide;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Сидоров Максим on 2020-01-31.
 */
public class Listeners {

    private List<Listener> listeners = new ArrayList<>();

    public void add(Listener listener) {
        listeners.add(listener);
    }

    public void remove(Listener listener) {
        listeners.remove(listener);
    }
}