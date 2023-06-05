package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.parkingorder;
import com.example.smartairportsystem.mapper.parkingordermapper;
import com.example.smartairportsystem.service.parkingorderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("parkingorderservice")
public class parkingorderserviceimpl implements parkingorderservice {
    @Autowired
    private parkingordermapper parkingorderMapper;

    public void addNewOrder(parkingorder newrorder){parkingorderMapper.addNewOrder(neworder);}
    public void removeOldOrder(Integer orderid){parkingorderMapper.removeOldOrder(orderid);}
    public parkingorder getOrderByID(Integer orderid){return parkingorderMapper.getOrderByID(orderid);}
    public parkingorder getOrderBySpaceid(Integer parkingspaceid){return parkingorderMapper.getOrderBySpaceid(parkingspaceid);}
    public parkingorder getOrderByTouristid(Integer touristid){return parkingorderMapper.listOrderByTouristid(touristid);}
}
