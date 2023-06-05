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
    public commoditylist getCommodityByCombine(Integer merchantid,String name){return commodityMapper.getCommodityByCombine(merchantid,name);}
    public Integer getMerchantidByCommodityid(Integer commodityid){return commodityMapper.getMerchantidByCommodityid(commodityid);}
    public void addNewCommodity(commoditylist newcommodity){commodityMapper.addNewCommodity(newcommodity);}
    public void updateCounts(Integer commodityid,Integer newcounts){commodityMapper.updateCounts(commodityid,newcounts);}
    public void updateOldCommodity(commoditylist newcommodity){commodityMapper.updateOldCommodity(newcommodity);}
    public void removeOldCommodity(Integer commodityid){commodityMapper.removeOldCommodity(commodityid);}
    public List<commoditylist> listCommodityByMerchantid(Integer merchantid){return commodityMapper.listCommodityByMerchantid(merchantid);}
}
