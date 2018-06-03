package com.gladunalexander.kalah.configuration;

/**
 * Utility class which holds basic game parameters.
 *
 * Created by Alexander Gladun on 01/06/2018.
 */
public class Settings {

    public static final int INITIAL_STONES_QUANTITY = 6;

    public static final int FIRST_PIT_INDEX = 1;

    public static final int LAST_PIT_INDEX = 14;

    private Settings() {
        throw new AssertionError("Current class can not be instantiated");
    }
}
