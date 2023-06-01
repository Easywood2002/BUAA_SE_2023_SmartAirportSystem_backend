package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.purchaserecord;
import com.example.smartairportsystem.mapper.purchaserecordmapper;
import com.example.smartairportsystem.service.purchaserecordservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("purchaserecordservice")
public class purchaserecordserviceimpl implements purchaserecordservice {
    @Autowired
    private purchaserecordmapper purchaserecordMapper;

    public void addNewRecord(purchaserecord newrecord){purchaserecordMapper.addNewRecord(newrecord);}
    public void removeOldRecord(Integer orderid){purchaserecordMapper.removeOldRecord(orderid);}
    public Integer getCountByTicketid(Integer ticketid){return purchaserecordMapper.getCountByTicketid(ticketid);}
}
