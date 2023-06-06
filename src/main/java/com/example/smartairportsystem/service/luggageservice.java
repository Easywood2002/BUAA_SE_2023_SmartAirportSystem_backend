package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.luggage;

public interface luggageservice {
    public void addNewLuggage(luggage newluggage);
    public void updateOldLuggage(Integer luggageid,String state,String location);
    public luggage getLuggageByID(Integer luggageid);
    public luggage getLuggageByCombine(Integer personid,Integer ticketid);
    public void removeOldLuggage(Integer luggageid);
}
