package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class commodityorder {
    @Id
    private Integer orderid;

    private Integer counts;
    private Integer touristid;
    private Integer commodityid;
    private Integer terminal;
    private String depaturegate;
    private String arrivetime;
    private String email;

    public commodityorder(Integer orderid,Integer counts,Integer touristid,Integer commodityid,Integer terminal,String depaturegate,String arrivetime,String email){
        this.orderid = orderid;
        this.touristid = touristid;
        this.counts = counts;
        this.commodityid = commodityid;
        this.terminal = terminal;
        this.depaturegate = depaturegate;
        this.arrivetime = arrivetime;
        this.email = email;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepaturegate() {
        return depaturegate;
    }

    public void setDepaturegate(String depaturegate) {
        this.depaturegate = depaturegate;
    }

    public String getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(String arrivetime) {
        this.arrivetime = arrivetime;
    }

    public Integer getTouristid() {
        return touristid;
    }

    public void setTouristid(Integer touristid) {
        this.touristid = touristid;
    }
}
