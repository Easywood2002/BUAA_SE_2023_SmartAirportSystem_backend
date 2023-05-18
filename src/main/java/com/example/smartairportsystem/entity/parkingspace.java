package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class parkingspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parkingpostid;

    private String location;
    private Double price;
    private Boolean available;

    public parkingspace(String location,Double price,Boolean available){
        this.location = location;
        this.price = price;
        this.available = available;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
