package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.parkingorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface parkingordermapper {
    public void addNewOrder(parkingorder newrorder);
    public void removeOldOrder(Integer orderid);
    public parkingorder getOrderByID(Integer orderid);
    public parkingorder getOrderBySpaceid(@Param("parkingspaceid") Integer parkingspaceid);
    public parkingorder getOrderByTouristid(Integer touristid);
}
