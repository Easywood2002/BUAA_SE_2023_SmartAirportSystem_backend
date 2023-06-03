package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class parkingorder {
    @Id
    private Integer orderid;

    private Integer touristid;
    private Double parktime;
    private Double price;
    private Integer parkingspaceid;

    public parkingorder(Integer orderid,Integer touristid,Double parktime,Double price,Integer parkingspaceid){
        this.orderid = orderid;
        this.touristid = touristid;
        this.parktime = parktime;
        this.price = price;
        this.parkingspaceid = parkingspaceid;
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

    public Integer getParkingspaceid() {
        return parkingspaceid;
    }

    public void setParkingspaceid(Integer parkingspaceid) {
        this.parkingspaceid = parkingspaceid;
    }
}
