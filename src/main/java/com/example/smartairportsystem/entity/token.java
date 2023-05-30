package com.example.smartairportsystem.entity;

import javax.persistence.Entity;

@Entity
public class token {
    private Integer id;
    private String token;

    public token(Integer id, String token){
        this.token = token;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
