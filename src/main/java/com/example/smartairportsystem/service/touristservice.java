package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.tourist;

public interface touristservice {
    public void logupNewTourist(tourist newtourist);
    public void updatePassword(Integer touristid, String newpasswords);
    public void updateEmail(Integer touristid,String newemail);
    public tourist getTouristByEmail(String email);
    public tourist getTouristByID(Integer touristid);
}
