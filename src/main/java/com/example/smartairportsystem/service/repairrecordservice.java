package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.repairrecord;

public interface repairrecordservice {
    public void addNewRepairrecord(repairrecord newrepairrecord);
    public repairrecord getRepairrecordByDeviceid(Integer deviceid);
    public repairrecord getRepairrecordByID(Integer recordid);
    public void examineRepairrecord(Integer recordid,String approved);
    public void removeOldRepairrecord(Integer recordid);
}