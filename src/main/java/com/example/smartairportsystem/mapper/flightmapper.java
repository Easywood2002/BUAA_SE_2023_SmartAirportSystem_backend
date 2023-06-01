package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.flight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface flightmapper {
    public flight getFlightByID(Integer flightid);
    public flight getFlightByCombine(@Param("name") String name,@Param("companyid") Integer companyid,@Param("departuretime") String departuretime);
    public void addNewFlight(flight newflight);
    public void updateOldFlight(flight newflight);
    public void removeOldFlight(Integer flightid);
    public List<flight> listFlightByCompanyid(Integer companyid);
}
