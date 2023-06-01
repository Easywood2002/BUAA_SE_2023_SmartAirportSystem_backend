package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.purchaserecord;

public interface purchaserecordservice {
    public void addNewRecord(purchaserecord newrecord);
    public void removeOldRecord(Integer orderid);
    public Integer getCountByTicketid(Integer ticketid);
}
