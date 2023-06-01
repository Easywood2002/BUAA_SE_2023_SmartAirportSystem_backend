package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class luggage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer luggageid;

    private Integer touristid;
    private Integer flightid;
    private String state;
    private String location;

    public luggage(Integer touristid,Integer flightid,String state,String location){
        this.touristid = touristid;
        this.flightid = flightid;
        this.state = state;
        this.location = location;
    }

    public Integer getLuggageid() {
        return luggageid;
    }

    public void setLuggageid(Integer luggageid) {
        this.luggageid = luggageid;
    }

    public Integer getTouristid() {
        return touristid;
    }

    public void setTouristid(Integer touristid) {
        this.touristid = touristid;
    }

    public Integer getFlightid() {
        return flightid;
    }

    public void setFlightid(Integer flightid) {
        this.flightid = flightid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
