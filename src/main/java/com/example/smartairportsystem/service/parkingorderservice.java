package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.parkingorder;

import java.util.List;

public interface parkingorderservice {
    public void addNewOrder(parkingorder newrorder);
    public void removeOldOrder(Integer orderid);
    public void selectParkingForOrderid(Integer orderid,Integer parkingspaceid);
    public parkingorder getOrderByID(Integer orderid);
    public parkingorder getOrderBySpaceid(Integer parkingspaceid);
}