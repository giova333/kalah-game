package com.gladunalexander.kalah.dto;

import java.util.Map;

/**
 * Created by Alexander Gladun on 31/05/2018.
 */
public class GameDTO {

    private int id;

    private String url;

    private Map<Integer, Integer> status;

    public GameDTO() { }

    public GameDTO(int id, String url, Map<Integer, Integer> status) {
        this.id = id;
        this.url = url;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Map<Integer, Integer> getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setStatus(Map<Integer, Integer> status) {
        this.status = status;
    }
}
