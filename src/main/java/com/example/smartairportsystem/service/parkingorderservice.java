package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.bowl.myparkingorder;
import com.example.smartairportsystem.entity.parkingorder;

public interface parkingorderservice {
    public void addNewOrder(parkingorder neworder);
    public void removeOldOrder(Integer orderid);
    public parkingorder getOrderByID(Integer orderid);
    public parkingorder getOrderBySpaceid(Integer parkingspaceid);
    public myparkingorder getOrderByTouristid(Integer touristid);
}