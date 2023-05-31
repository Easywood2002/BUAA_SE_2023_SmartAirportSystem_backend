package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface personmapper {
    public person getPersonByTouristidAndIdnumber(@Param("touristid") Integer touristid, @Param("idnumber") String idnumber);
    public person getPersonByID(Integer personid);
    public void updateOldPerson(person newperson);
    public void addNewPerson(person newperson);
}
