package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface personmapper {
    public person getPersonByTouristidAndIdnumber(@Param("touristid") Integer touristid, @Param("idnumber") String idnumber);
    public person getPersonByID(Integer personid);
    public void updateOldPerson(person newperson);
    public void removeOldPerson(Integer personid);
    public void addNewPerson(person newperson);
    public List<person> listPersonByTouristid(Integer touristid);
}
