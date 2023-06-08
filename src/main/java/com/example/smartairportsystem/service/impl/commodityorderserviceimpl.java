package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.bowl.mycommodityorder;
import com.example.smartairportsystem.entity.commodityorder;
import com.example.smartairportsystem.mapper.commodityordermapper;
import com.example.smartairportsystem.service.commodityorderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commodityorderservice")
public class commodityorderserviceimpl implements commodityorderservice {
    @Autowired
    commodityordermapper commodityorderMapper;

    public void addNewOrder(commodityorder neworder){commodityorderMapper.addNewOrder(neworder);}
    public void removeOldOrder(Integer orderid){commodityorderMapper.removeOldOrder(orderid);}
    public commodityorder getOrderByID(Integer orderid){return commodityorderMapper.getOrderByID(orderid);}
    public List<commodityorder> listAllOrder(){return commodityorderMapper.listAllOrder();}
    public List<mycommodityorder> listOrderByTouristid(Integer touristid){return commodityorderMapper.listOrderByTouristid(touristid);}
    public List<mycommodityorder> listOrderByMerchantid(Integer merchantid){return commodityorderMapper.listOrderByMerchantid(merchantid);}
}
