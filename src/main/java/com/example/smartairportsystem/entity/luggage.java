package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class luggage {
    @Id
    private String luggageid;

    private Integer touristid;
    private String flightid;
    private String state;
    private String location;

    public luggage(String luggageid,Integer touristid,String flightid,String state,String location){
        this.luggageid = luggageid;
        this.touristid = touristid;
        this.flightid = flightid;
        this.state = state;
        this.location = location;
    }

    public String getLuggageid() {
        return luggageid;
    }

    public void setLuggageid(String luggageid) {
        this.luggageid = luggageid;
    }

    public Integer getTouristid() {
        return touristid;
    }

    public void setTouristid(Integer touristid) {
        this.touristid = touristid;
    }

    public String getFlightid() {
        return flightid;
    }

    public void setFlightid(String flightid) {
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
