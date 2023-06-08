package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.runway;
import com.example.smartairportsystem.mapper.runwaymapper;
import com.example.smartairportsystem.service.runwayservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("runwayservice")
public class runwayserviceimpl implements runwayservice {
    @Autowired
    private runwaymapper runwayMapper;

    public List<runway> listOccupyByRunway(Integer runway,String thatday,Integer exceptid){return runwayMapper.listOccupyByRunway(runway,thatday,exceptid);}
    public void addNewOccupy(runway newrunway){runwayMapper.addNewOccupy(newrunway);}
    public void updateOldOccupy(runway newrunway){runwayMapper.updateOldOccupy(newrunway);}
}
