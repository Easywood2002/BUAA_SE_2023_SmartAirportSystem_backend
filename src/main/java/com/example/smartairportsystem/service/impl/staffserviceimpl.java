package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.mapper.staffmapper;
import com.example.smartairportsystem.entity.staff;
import com.example.smartairportsystem.service.staffservice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("staffservice")
public class staffserviceimpl implements staffservice{
    @Autowired
    private staffmapper staffMapper;

    public void logupNewStaff(staff newstaff){staffMapper.logupNewStaff(newstaff);}
    public void updateOldStaff(staff newstaff){staffMapper.updateOldStaff(newstaff);}
    public void updatePassword(Integer staffid, String newpasswords){staffMapper.updatePassword(staffid,newpasswords);}
    public void removeOldStaff(Integer staffid){staffMapper.removeOldStaff(staffid);}
    public staff getStaffByEmail(String email){return staffMapper.getStaffByEmail(email);}
    public staff getStaffByIdnumber(String  idnumber){return staffMapper.getStaffByIdnumber(idnumber);}
    public staff getStaffByID(Integer staffid){return staffMapper.getStaffByID(staffid);}
}
