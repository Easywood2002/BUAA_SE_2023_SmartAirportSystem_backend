package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class notifyaudience {
    @Id
    private Integer audienceid;

    private Integer touristid;
    private Integer ticketid;

    public notifyaudience(Integer audienceid,Integer touristid,Integer ticketid){
        this.audienceid = audienceid;
        this.ticketid = ticketid;
        this.touristid = touristid;
    }

    public Integer getAudienceid() {
        return audienceid;
    }

    public void setAudienceid(Integer audienceid) {
        this.audienceid = audienceid;
    }

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public Integer getTouristid() {
        return touristid;
    }

    public void setTouristid(Integer touristid) {
        this.touristid = touristid;
    }

}
