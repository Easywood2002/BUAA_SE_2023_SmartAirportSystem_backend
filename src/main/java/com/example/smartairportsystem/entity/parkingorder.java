package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class parkingorder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderid;

    private Integer touristid;
    private Double parktime;
    private Double price;
    private String location;

    public parkingorder(Integer touristid,Double parktime,Double price,String location){
        this.touristid = touristid;
        this.parktime = parktime;
        this.price = price;
        this.location = location;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getTouristid() {
        return touristid;
    }

    public void setTouristid(Integer touristid) {
        this.touristid = touristid;
    }

    public Double getParktime() {
        return parktime;
    }

    public void setParktime(Double parktime) {
        this.parktime = parktime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
