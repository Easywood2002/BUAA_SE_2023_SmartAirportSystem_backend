package com.example.smartairportsystem.entity;

import javax.persistence.Entity;

@Entity
public class runway {
    private Integer flightid;
    private String time;
    private Integer runway;

    public runway(Integer flightid,String time,Integer runway){
        this.flightid = flightid;
        this.runway = runway;
        this.time = time;
    }

    public Integer getFlightid() {
        return flightid;
    }

    public void setFlightid(Integer flightid) {
        this.flightid = flightid;
    }

    public Integer getRunway() {
        return runway;
    }

    public void setRunway(Integer runway) {
        this.runway = runway;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
