package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.airlinecompany;

public interface companyservice {
    public void logupNewCompany(airlinecompany newcompany);
    public airlinecompany getCompanyByName(String name);
    public airlinecompany getCompanyByToken(String token);
}
