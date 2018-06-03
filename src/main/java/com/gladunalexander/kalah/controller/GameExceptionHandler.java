package com.gladunalexander.kalah.controller;

import com.gladunalexander.kalah.dto.ExceptionDTO;
import com.gladunalexander.kalah.exception.GameNotFoundException;
import com.gladunalexander.kalah.exception.GameTerminatedException;
import com.gladunalexander.kalah.exception.IllegalPitNumberException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Converts exceptions to Http statuses.
 * {@link HttpStatus}
 * {@link ExceptionDTO} represents error.
 *
 * Created by Alexander Gladun on 01/06/2018.
 */

@RestControllerAdvice
public class GameExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDTO handleNotFound(Exception ex) {
        return new ExceptionDTO(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalPitNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO handleBadRequest(Exception ex) {
        return new ExceptionDTO(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GameTerminatedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionDTO handleConflict(GameTerminatedException ex) {
        return new ExceptionDTO(ex.getMessage(), HttpStatus.CONFLICT, ex.getStatus());
    }
}
