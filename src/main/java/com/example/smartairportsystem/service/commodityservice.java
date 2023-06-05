package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.merchant;
import com.example.smartairportsystem.entity.commoditylist;

import java.util.List;

public interface commodityservice {
    public commoditylist getCommodityByID(Integer commodityid);
    public commoditylist getCommodityByCombine(Integer merchantid,String name);
    public Integer getMerchantidByCommodityid(Integer commodityid);
    public void addNewCommodity(commoditylist newcommodity);
    public void updateOldCommodity(commoditylist newcommodity);
    public void removeOldCommodity(Integer commodityid);
    public List<commoditylist> listCommodityByMerchantid(Integer merchantid);
}
