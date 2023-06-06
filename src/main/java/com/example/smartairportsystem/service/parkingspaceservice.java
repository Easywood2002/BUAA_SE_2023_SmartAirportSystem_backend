package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.parkingspace;

import java.util.List;

public interface parkingspaceservice {
    public List<parkingspace> listEmptyParkingspace();
    public List<parkingspace> listAllParkingspace();
    public void addNewParkingspace(parkingspace newps);
    public void updateOldParkingspace(parkingspace newps);
    public void removeOldParkingspace(Integer parkingspaceid);
    public parkingspace getParkingspaceByID(Integer parkingspaceid);
    public parkingspace getParkingspaceByLocation(String location,Integer exceptid);
}