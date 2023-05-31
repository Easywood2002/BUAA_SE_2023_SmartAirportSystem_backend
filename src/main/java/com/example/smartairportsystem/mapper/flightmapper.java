package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.flight;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface flightmapper {
    public flight getFlightByID(String flightid);
    public void addNewFlight(flight newflight);
    public void updateOldFlight(flight newflight);
    public void removeOldFlight(String flightid);
    public List<flight> listFlightByCompanyid(Integer companyid);
}
