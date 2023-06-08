package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.runway;

import java.util.List;

public interface runwayservice {
    public List<runway> listOccupyByRunway(Integer runway,String thatday,Integer exceptid);
    public void addNewOccupy(runway newrunway);
    public void updateOldOccupy(runway newrunway);
}
