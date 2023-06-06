package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.repairrecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface repairrecordmapper {
    public void addNewRepairrecord(repairrecord newrepairrecord);
    public repairrecord getRepairrecordByID(Integer recordid);
    public void examineRepairrecord(@Param("recordid") Integer recordid, @Param("approved") Integer approved);
    public void removeOldRepairrecord(Integer recordid);
    
}