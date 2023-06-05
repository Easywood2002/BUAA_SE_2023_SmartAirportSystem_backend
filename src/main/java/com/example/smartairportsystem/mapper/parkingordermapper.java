package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.bowl.myparkingorder;
import com.example.smartairportsystem.entity.parkingorder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface parkingordermapper {
    public void addNewOrder(parkingorder neworder);
    public void removeOldOrder(Integer orderid);
    public parkingorder getOrderByID(Integer orderid);
    public parkingorder getOrderBySpaceid(Integer parkingspaceid);
    public myparkingorder getOrderByTouristid(Integer touristid);
}
