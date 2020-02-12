package com.example.kotlindemo.slide;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Сидоров Максим on 2020-01-22.
 */
public class StringWrapper {

    public static List<Runnable> listeners = new ArrayList<>();

    private final String value;

    public StringWrapper(String value) {
        this.value = value;
    }

    @NonNull
    public String substring(int count) {
        return value.substring(count);
    }

    public static void addListener(Runnable r) {
        listeners.add(r);
    }

    public static void removeListener(Runnable r) {
        listeners.remove(r);
    }

    public static String[] getStringArray() {
        return new String[]{"1", "2", "3"};
    }
}

