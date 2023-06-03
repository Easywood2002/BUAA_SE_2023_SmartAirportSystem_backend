package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class parkingspace {
    @Id
    private Integer parkingpostid;

    private String location;
    private Double price;

    public parkingspace(Integer parkingpostid,String location,Double price){
        this.parkingpostid = parkingpostid;
        this.location = location;
        this.price = price;
    }

    public Integer getParkingpostid() {
        return parkingpostid;
    }

    public void setParkingpostid(Integer parkingpostid) {
        this.parkingpostid = parkingpostid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
