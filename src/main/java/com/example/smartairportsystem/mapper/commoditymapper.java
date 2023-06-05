package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.commoditylist;
import com.example.smartairportsystem.entity.merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface commoditymapper {
    public commoditylist getCommodityByID(Integer commodityid);
    public commoditylist getCommodityByCombine(@Param("merchantid") Integer merchantid,@Param("name") String name);
    public Integer getMerchantidByCommodityid(Integer commodityid);
    public void addNewCommodity(commoditylist newcommodity);
    public void updateOldCommodity(commoditylist newcommodity);
    public void removeOldCommodity(Integer commodityid);
    public List<commoditylist> listCommodityByMerchantid(Integer merchantid);
}
