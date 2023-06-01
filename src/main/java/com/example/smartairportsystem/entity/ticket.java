package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ticket {
    @Id
    private Integer ticketid;

    private Integer flightid;
    private String tickettype;
    private Double price;
    private Integer amount;

    public ticket(Integer ticketid,Integer flightid,String tickettype,Double price,Integer amount){
        this.ticketid = ticketid;
        this.flightid = flightid;
        this.tickettype = tickettype;
        this.price = price;
        this.amount = amount;
    }

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public Integer getFlightid() {
        return flightid;
    }

    public void setFlightid(Integer flightid) {
        this.flightid = flightid;
    }

    public String getTickettype() {
        return tickettype;
    }

    public void setTickettype(String tickettype) {
        this.tickettype = tickettype;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
