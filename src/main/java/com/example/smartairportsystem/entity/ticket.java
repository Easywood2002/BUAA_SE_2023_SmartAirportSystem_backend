package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ticket {
    @Id
    private String ticketid;

    private String flightid;
    private String tickettype;
    private Double price;
    private Integer amount;

    public ticket(String ticketid,String flightid,String tickettype,Double price,Integer amount){
        this.ticketid = ticketid;
        this.flightid = flightid;
        this.tickettype = tickettype;
        this.price = price;
        this.amount = amount;
    }

    public String getTicketid() {
        return ticketid;
    }

    public void setTicketid(String ticketid) {
        this.ticketid = ticketid;
    }

    public String getFlightid() {
        return flightid;
    }

    public void setFlightid(String flightid) {
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
