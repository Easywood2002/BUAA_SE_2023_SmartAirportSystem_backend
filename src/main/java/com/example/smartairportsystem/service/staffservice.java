package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.staff;

public interface staffservice {
    public void logupNewStaff(staff newstaff);
    public void updateOldStaff(staff newstaff);
    public void updatePassword(Integer staffid, String newpasswords);
    public void removeOldStaff(Integer staffid);
    public staff getStaffByEmail(String email);
    public staff getStaffByIdnumber(String  idnumber);
    public staff getStaffByID(Integer staffid);
}