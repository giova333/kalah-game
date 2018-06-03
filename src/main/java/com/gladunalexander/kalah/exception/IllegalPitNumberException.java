package com.gladunalexander.kalah.exception;

/**
 * Thrown to indicate that provided pit number is invalid.
 *
 * Created by Alexander Gladun on 01/06/2018.
 */
public class IllegalPitNumberException extends RuntimeException {

    public IllegalPitNumberException(String message) {
        super(message);
    }
}
