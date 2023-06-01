package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.person;

import java.util.List;

public interface personservice {
    public person getPersonByCombine(Integer touristid, String idnumber);
    public person getPersonByID(Integer personid);
    public void updateOldPerson(person newperson);
    public void removeOldPerson(Integer personid);
    public void addNewPerson(person newperson);
    public List<person> listPersonByTouristid(Integer touristid);
}
