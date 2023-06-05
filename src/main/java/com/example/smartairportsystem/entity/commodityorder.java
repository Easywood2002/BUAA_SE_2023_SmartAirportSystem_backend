package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class commodityorder {
    @Id
    private Integer orderid;

    private Integer counts;
    private Integer commodityid;
    private Integer touristid;

    public commodityorder(Integer orderid,Integer counts,Integer commodityid,Integer touristid){
        this.orderid =orderid;
        this.counts = counts;
        this.commodityid = commodityid;
        this.touristid = touristid;
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

    public Integer getTouristid() {
        return touristid;
    }

    public void setTouristid(Integer touristid) {
        this.touristid = touristid;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }
}
