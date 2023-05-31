package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.airlinecompany;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface companymapper {
    public void logupNewCompany(airlinecompany newcompany);
    public airlinecompany getCompanyByName(String name);
    public airlinecompany getCompanyByID(Integer companyid);
}
