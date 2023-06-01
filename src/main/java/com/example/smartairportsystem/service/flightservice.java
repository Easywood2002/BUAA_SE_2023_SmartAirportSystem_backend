package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.flight;

import java.util.List;

public interface flightservice {
    public flight getFlightByID(Integer flightid);
    public flight getFlightByCombine(String name,Integer companyid,String departuretime);
    public void addNewFlight(flight newflight);
    public void updateOldFlight(flight newflight);
    public void removeOldFlight(Integer flightid);
    public List<flight> listFlightByCompanyid(Integer companyid);
    public List<flight> listFlightByCombine(String takeofflocation,String landinglocation,String date);
}
