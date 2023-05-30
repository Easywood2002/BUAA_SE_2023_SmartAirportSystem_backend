package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.airlinecompany;
import com.example.smartairportsystem.mapper.companymapper;
import com.example.smartairportsystem.service.companyservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyservice")
public class companyserviceimpl implements companyservice {
    @Autowired
    private companymapper companyMapper;

    public void logupNewCompany(airlinecompany newcompany){companyMapper.logupNewCompany(newcompany);}
    public airlinecompany getCompanyByName(String name){return companyMapper.getCompanyByName(name);}
    public airlinecompany getCompanyByToken(String token){return companyMapper.getCompanyByToken(token);}
}
