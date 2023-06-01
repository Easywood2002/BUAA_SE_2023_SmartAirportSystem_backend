package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class commoditylist {
    @Id
    private Integer commodityid;

    private String name;
    private Integer merchantid;
    private Integer counts;
    private Double price;

    public commoditylist(Integer commodityid,String name,Integer merchantid,Integer counts,Double price){
        this.commodityid =commodityid;
        this.name = name;
        this.merchantid = merchantid;
        this.counts = counts;
        this.price = price;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Integer merchantid) {
        this.merchantid = merchantid;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
