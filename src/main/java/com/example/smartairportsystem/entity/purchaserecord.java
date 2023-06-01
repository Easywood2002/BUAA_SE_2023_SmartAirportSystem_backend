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

    private Integer personid;
    private Integer ticketid;
    private String purchasetime;
    private String seatinfo;

    public purchaserecord(Integer personid,Integer ticketid,String purchasetime,String seatinfo){
        this.personid = personid;
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
