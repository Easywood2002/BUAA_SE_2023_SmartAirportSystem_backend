package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.airlinecompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface companymapper {
    public void logupNewCompany(airlinecompany newcompany);
    public void updatePassword(@Param("touristid") Integer companyid, @Param("newpasswords") String newpasswords);
    public airlinecompany getCompanyByEmail(String email);
    public airlinecompany getCompanyByID(Integer companyid);
}
