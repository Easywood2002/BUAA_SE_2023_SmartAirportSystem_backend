package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.mapper.repairrecordmapper;
import com.example.smartairportsystem.entity.repairrecord;
import com.example.smartairportsystem.service.repairrecordservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("repairrecordservice")
public class repairrecordserviceimpl implements repairrecordservice{
    @Autowired
    private repairrecordmapper repairrecordMapper;

    public void addNewRepairrecord(repairrecord newrepairrecord){repairrecordMapper.addNewRepairrecord(newrepairrecord);}
    public repairrecord getRepairrecordByID(Integer recordid){return repairrecordMapper.getRepairrecordByID(recordid);}
    public void examineRepairrecord(Integer recordid,Integer approved){repairrecordMapper.examineRepairrecord(recordid,approved);}
    public void removeOldRepairrecord(Integer recordid){repairrecordMapper.removeOldRepairrecord(recordid);}
    public List<repairrecord> listAllRecord(){return repairrecordMapper.listAllRecord();}
}
