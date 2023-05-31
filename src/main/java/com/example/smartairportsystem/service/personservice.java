package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.person;

public interface personservice {
    public person getPersonByTouristidAndIdnumber(Integer touristid, String idnumber);
    public person getPersonByID(Integer personid);
    public void updateOldPerson(person newperson);
    public void addNewPerson(person newperson);
}
