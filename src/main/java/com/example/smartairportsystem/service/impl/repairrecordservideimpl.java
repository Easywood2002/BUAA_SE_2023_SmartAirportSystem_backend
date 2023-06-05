package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.mapper.repairrecordmapper;
import com.example.smartairportsystem.entity.repairrecord;
import com.example.smartairportsystem.service.repairrecordservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("repairrecordservice")
public class staffserviceimpl implements repairrecordservice{
    @Autowired
    private repairrecordmapper repairrecordMapper;

    public void addNewRepairrecord(repairrecord newrepairrecord){repairrecordMapper.addNewRepairrecord(newrepairrecord);}
    public repairrecord getRepairrecordByDeviceid(Integer deviceid){return repairrecordMapper.getRepairByDeviceid(deviceid);}
    public repairrecord getRepairrecordByID(Integer recordid){return repairrecordMapper.getRepairByID(recordid);}
    public void examineRepairrecord(Integer recordid,String approved){repairrecordMapper.examineRepairrecord(recordid,approved);}
    public void removeOldRepairrecord(Integer recordid){repairrecordMapper.removeOldRepairrecord(recordid);}
}
