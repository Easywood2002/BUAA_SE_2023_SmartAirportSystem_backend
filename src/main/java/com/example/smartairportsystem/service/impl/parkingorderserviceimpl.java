package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.bowl.myparkingorder;
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

    public void addNewOrder(parkingorder neworder){parkingorderMapper.addNewOrder(neworder);}
    public void removeOldOrder(Integer orderid){parkingorderMapper.removeOldOrder(orderid);}
    public parkingorder getOrderByID(Integer orderid){return parkingorderMapper.getOrderByID(orderid);}
    public List<parkingorder> listAllOrder(){return parkingorderMapper.listAllOrder();}
    public List<parkingorder> listOrderBySpaceid(Integer parkingspaceid){return parkingorderMapper.listOrderBySpaceid(parkingspaceid);}
    public List<myparkingorder> listOrderByTouristid(Integer touristid){return parkingorderMapper.listOrderByTouristid(touristid);}
}
