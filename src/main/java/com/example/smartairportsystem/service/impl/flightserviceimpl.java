package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.flight;
import com.example.smartairportsystem.mapper.flightmapper;
import com.example.smartairportsystem.service.flightservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("flightservice")
public class flightserviceimpl implements flightservice {
    @Autowired
    private flightmapper flightMapper;

    public flight getFlightByID(String flightid){return flightMapper.getFlightByID(flightid);}
    public void addNewFlight(flight newflight){flightMapper.addNewFlight(newflight);}
    public void updateOldFlight(flight newflight){flightMapper.updateOldFlight(newflight);}
    public void removeOldFlight(String flightid){flightMapper.removeOldFlight(flightid);}
    public List<flight> listFlightByCompanyid(Integer companyid){return flightMapper.listFlightByCompanyid(companyid);}
}
