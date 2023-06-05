package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.mapper.staffmapper;
import com.example.smartairportsystem.entity.staff;
import com.example.smartairportsystem.service.staffservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("staffservice")
public class staffserviceimpl implements staffservice{
    @Autowired
    private staffmapper staffMapper;

    public void logupNewStaff(staff newstaff){staffMapper.logupNewStaff(newstaff);}
    public void removeOldStaff(Integer staffid){staffMapper.removeOldStaff(staffid);};
    public staff getStaffByEmail(String email){return staffMapper.getStaffByEmail(email);}
    public staff getStaffByID(Integer staffid){return staffMapper.getStaffByID(staffid);}
}
