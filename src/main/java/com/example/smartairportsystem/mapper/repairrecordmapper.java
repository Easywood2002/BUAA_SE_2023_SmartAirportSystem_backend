package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.repairrecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface staffmapper {
    public void addNewRepairrecord(repairrecord newrepairrecord);
    public repairrecord getRepairrecordByDeviceid(Integer deviceid);
    public repairrecord getRepairrecordByID(Integer recordid);
    public void examineRepairrecord(Integer recordid,String approved);
    public void removeOldRepairrecord(Integer recordid);
    
}