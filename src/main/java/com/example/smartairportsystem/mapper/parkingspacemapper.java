package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.parkingspace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface parkingspacemapper {
    public List<parkingspace> listEmptyParkingspace();
}
