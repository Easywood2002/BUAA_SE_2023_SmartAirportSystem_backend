package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.flight;

import java.util.List;

public interface flightservice {
    public flight getFlightByID(String flightid);
    public void addNewFlight(flight newflight);
    public void updateOldFlight(flight newflight);
    public void removeOldFlight(String flightid);
    public List<flight> listFlightByID(Integer companyid);
}
