package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.parkingorder;

import java.util.List;

public interface parkingorderservice {
    public void addNewOrder(parkingorder neworder);
    public void removeOldOrder(Integer orderid);
    public parkingorder getOrderByID(Integer orderid);
    public parkingorder getOrderBySpaceid(Integer parkingspaceid);
    public parkingorder getOrderByTouristid(Integer touristid);
}