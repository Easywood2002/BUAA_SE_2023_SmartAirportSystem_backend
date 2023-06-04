package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.commoditylist;
import com.example.smartairportsystem.entity.merchant;
import com.example.smartairportsystem.mapper.commoditymapper;
import com.example.smartairportsystem.service.commodityservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commodityservice")
public class commodityserviceimpl implements commodityservice {
    @Autowired
    private commoditymapper commodityMapper;

    public commoditylist getCommodityByID(Integer commodityid){return commodityMapper.getCommodityByID(commodityid);}
    public commoditylist getCommodityByCombine(Integer mercantid,String name){return commodityMapper.getCommodityByCombine(mercantid,name);}
    public void addNewCommodity(commoditylist newcommodity){commodityMapper.addNewCommodity(newcommodity);}
    public void updateOldCommodity(commoditylist newcommodity){commodityMapper.updateOldCommodity(newcommodity);}
    public void removeOldCommodity(Integer commodityid){commodityMapper.removeOldCommodity(commodityid);}
    public List<commoditylist> listCommodityByMerchantid(Integer mercantid){return commodityMapper.listCommodityByMerchantid(mercantid);}
}
