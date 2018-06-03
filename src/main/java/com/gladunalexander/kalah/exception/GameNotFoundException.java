package com.gladunalexander.kalah.exception;

/**
 * Thrown to indicate that provided game identifier
 * is not associated with any record in database.
 *
 * Created by Alexander Gladun on 31/05/2018.
 */
public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(String message) {
        super(message);
    }
}
