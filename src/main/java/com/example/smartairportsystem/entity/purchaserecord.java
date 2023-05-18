package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class purchaserecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderid;

    private Integer touristid;
    private String ticketid;
    private String purchasetime;
    private String seatinfo;

    public purchaserecord(Integer touristid,String ticketid,String purchasetime,String seatinfo){
        this.touristid = touristid;
        this.ticketid = ticketid;
        this.purchasetime = purchasetime;
        this.seatinfo = seatinfo;
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

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getPurchasetime() {
        return purchasetime;
    }

    public void setPurchasetime(String purchasetime) {
        this.purchasetime = purchasetime;
    }

    public String getSeatinfo() {
        return seatinfo;
    }

    public void setSeatinfo(String seatinfo) {
        this.seatinfo = seatinfo;
    }
}
