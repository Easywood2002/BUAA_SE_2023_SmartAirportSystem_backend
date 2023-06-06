package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.parkingspace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface parkingspacemapper {
    public List<parkingspace> listEmptyParkingspace();
    public List<parkingspace> listAllParkingspace();
    public void addNewParkingspace(parkingspace newps);
    public void updateOldParkingspace(parkingspace newps);
    public void removeOldParkingspace(Integer parkingspaceid);
    public parkingspace getParkingspaceByLocation(@Param("location") String location,@Param("exceptid") Integer exceptid);
}
