package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class luggage {
    @Id
    private Integer luggageid;

    private Integer personid;
    private Integer ticketid;
    private String state;
    private String location;

    public luggage(Integer luggageid,Integer personid,Integer ticketid,String state,String location){
        this.luggageid = luggageid;
        this.personid = personid;
        this.ticketid = ticketid;
        this.state = state;
        this.location = location;
    }

    public Integer getLuggageid() {
        return luggageid;
    }

    public void setLuggageid(Integer luggageid) {
        this.luggageid = luggageid;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
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
