package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.person;
import com.example.smartairportsystem.mapper.personmapper;
import com.example.smartairportsystem.service.personservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personservice")
public class personserviceimpl implements personservice {
    @Autowired
    private personmapper personMapper;

    public person getPersonByTouristidAndIdnumber(Integer touristid, String idnumber){return personMapper.getPersonByTouristidAndIdnumber(touristid,idnumber);}
    public person getPersonByID(Integer personid){return personMapper.getPersonByID(personid);}
    public void updateOldPerson(person newperson){personMapper.updateOldPerson(newperson);}
    public void removeOldPerson(Integer personid){personMapper.removeOldPerson(personid);}
    public void addNewPerson(person newperson){personMapper.addNewPerson(newperson);}
    public List<person> listPersonByTouristid(Integer touristid){return personMapper.listPersonByTouristid(touristid);}
}
