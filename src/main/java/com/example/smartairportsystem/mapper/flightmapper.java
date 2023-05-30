package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.flight;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface flightmapper {
    public flight getFlightByID(String flightid);
    public void addNewFlight(flight newflight);
}
