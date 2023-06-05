package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface staffmapper {
    public void logupNewStaff(staff newstaff);
    public void removeOldStaff(Integer staffid);
    public void updatePassword(@Param("staffid") Integer staffid, @Param("newpasswords") String newpasswords);
    public staff getStaffByEmail(String email);
    public staff getStaffByIdnumber(String  idnumber);
    public staff getStaffByID(Integer staffid);
}