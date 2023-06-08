package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.bowl.myparkingorder;
import com.example.smartairportsystem.entity.parkingorder;

import java.util.List;

public interface parkingorderservice {
    public void addNewOrder(parkingorder neworder);
    public void removeOldOrder(Integer orderid);
    public parkingorder getOrderByID(Integer orderid);
    public List<parkingorder> listAllOrder();
    public List<parkingorder> listOrderBySpaceid(Integer parkingspaceid);
    public List<myparkingorder> listOrderByTouristid(Integer touristid);
}