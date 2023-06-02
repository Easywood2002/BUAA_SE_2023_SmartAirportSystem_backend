package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.purchaserecord;

import java.util.List;

public interface purchaserecordservice {
    public void addNewRecord(purchaserecord newrecord);
    public void removeOldRecord(Integer orderid);
    public void selectSeatForOrderid(Integer orderid,String seatinfo);
    public purchaserecord getRecordByID(Integer orderid);
    public purchaserecord getRecordByCombine(Integer ticketid,String seatinfo);
    public List<purchaserecord> getRecordByTicketid(Integer ticketid);
    public Integer getCountByTicketid(Integer ticketid);
}
