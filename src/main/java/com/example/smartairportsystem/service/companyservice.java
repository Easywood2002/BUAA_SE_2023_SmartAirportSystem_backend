package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.airlinecompany;

public interface companyservice {
    public void logupNewCompany(airlinecompany newcompany);
    public void updateOldCompany(airlinecompany newcompany);
    public void updatePassword(Integer companyid,String newpasswords);
    public airlinecompany getCompanyByEmail(String email,Integer exceptid);
    public airlinecompany getCompanyByID(Integer companyid);
}
