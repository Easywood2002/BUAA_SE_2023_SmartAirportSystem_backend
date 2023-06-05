package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.bowl.mycommodityorder;
import com.example.smartairportsystem.entity.commodityorder;

import java.util.List;

public interface commodityorderservice {
    public void addNewOrder(commodityorder neworder);
    public void removeOldOrder(Integer orderid);
    public commodityorder getOrderByID(Integer orderid);
    public List<mycommodityorder> listOrderByTouristid(Integer touristid);
}
