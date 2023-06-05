package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.parkingspace;
import com.example.smartairportsystem.mapper.parkingspacemapper;
import com.example.smartairportsystem.service.parkingspaceservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("parkingspaceservice")
public class parkingspaceserviceimpl implements parkingspaceservice {
    @Autowired
    private parkingspacemapper parkingspaceMapper;

    public List<parkingspace> listEmptyParkingspace(){return parkingspaceMapper.listEmptyParkingspace();}
}
