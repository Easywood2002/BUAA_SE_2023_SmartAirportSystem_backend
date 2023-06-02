package com.example.smartairportsystem.entity.bowl;

import javax.persistence.Entity;

@Entity
public class seat {
    private Integer seatid;

    private String occupied;

    public seat(Integer seatid,String occupied){
        this.seatid = seatid;
        this.occupied = occupied;
    }

    public Integer getSeatid() {
        return seatid;
    }

    public void setSeatid(Integer seatid) {
        this.seatid = seatid;
    }

    public String getOccupied() {
        return occupied;
    }

    public void setOccupied(String occupied) {
        this.occupied = occupied;
    }
}
