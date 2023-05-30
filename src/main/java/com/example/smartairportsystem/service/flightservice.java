package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.flight;

public interface flightservice {
    public flight getFlightByID(String flightid);
    public void addNewFlight(flight newflight);
}
