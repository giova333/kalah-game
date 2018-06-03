package com.gladunalexander.kalah.dto;

/**
 * Created by Alexander Gladun on 31/05/2018.
 */
public class NewGameDTO {

    private int id;

    private String uri;

    public NewGameDTO() { }

    public NewGameDTO(int id, String uri) {
        this.id = id;
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
