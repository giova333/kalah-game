package com.gladunalexander.kalah.dto;

import com.gladunalexander.kalah.domain.enums.Status;
import org.springframework.http.HttpStatus;

/**
 * Created by Alexander Gladun on 01/06/2018.
 */
public class ExceptionDTO {

    private String message;

    private HttpStatus httpStatus;

    private Status gameStatus;

    public ExceptionDTO() { }

    public ExceptionDTO(String message, HttpStatus status) {
        this(message, status, null);
    }

    public ExceptionDTO(String message, HttpStatus httpStatus, Status gameStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.gameStatus = gameStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Status getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(Status gameStatus) {
        this.gameStatus = gameStatus;
    }
}
