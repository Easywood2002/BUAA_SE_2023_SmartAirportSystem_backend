package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.parkingorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface parkingordermapper {
    public void addNewOrder(parkingorder neworder);
    public void removeOldOrder(Integer orderid);
    public void selectParkingForOrderid(@Param("orderid") Integer orderid,@Param("parkingspaceid") Integer parkingspaceid);
    public parkingorder getOrderByID(Integer orderid);
    public parkingorder getOrderBySpaceid(@Param("parkingspaceid") Integer parkingspaceid);
}
