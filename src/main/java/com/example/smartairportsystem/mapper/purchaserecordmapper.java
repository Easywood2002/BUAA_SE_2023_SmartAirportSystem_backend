package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.purchaserecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface purchaserecordmapper {
    public void addNewRecord(purchaserecord newrecord);
    public void removeOldRecord(Integer orderid);
    public Integer getCountByTicketid(Integer ticketid);
}
