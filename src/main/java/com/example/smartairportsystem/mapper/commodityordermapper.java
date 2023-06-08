package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.bowl.mycommodityorder;
import com.example.smartairportsystem.entity.commodityorder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface commodityordermapper {
    public void addNewOrder(commodityorder neworder);
    public void removeOldOrder(Integer orderid);
    public commodityorder getOrderByID(Integer orderid);
    public List<commodityorder> listAllOrder();
    public List<mycommodityorder> listOrderByTouristid(Integer touristid);
    public List<mycommodityorder> listOrderByMerchantid(Integer merchantid);
}
