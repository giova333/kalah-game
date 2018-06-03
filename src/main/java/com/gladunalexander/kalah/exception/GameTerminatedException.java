package com.gladunalexander.kalah.exception;

import com.gladunalexander.kalah.domain.enums.Status;

/**
 * Thrown to indicate that requested game
 * has been already terminated.
 *
 * Created by Alexander Gladun on 31/05/2018.
 */
public class GameTerminatedException extends RuntimeException {

    private final Status status;

    public GameTerminatedException(String message, Status status) {
        super(message);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
