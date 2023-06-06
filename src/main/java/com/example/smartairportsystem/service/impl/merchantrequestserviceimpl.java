package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.mapper.merchantrequestmapper;
import com.example.smartairportsystem.entity.merchantrequest;
import com.example.smartairportsystem.service.merchantrequestservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("merchantrequestservice")
public class merchantrequestserviceimpl implements merchantrequestservice{
    @Autowired
    private merchantrequestmapper merchantrequestMapper;

    public void addNewMerchantrequest(merchantrequest newrequest){merchantrequestMapper.addNewMerchantrequest(newrequest);}
    public merchantrequest getMerchantrequestByEmail(String email){return merchantrequestMapper.getMerchantrequestByEmail(email);}
    public merchantrequest getMerchantrequestByID(Integer requestid){return merchantrequestMapper.getMerchantrequestByID(requestid);}
    public merchantrequest getMerchantrequestByIdnumber(String idnumber){return merchantrequestMapper.getMerchantrequestByIdnumber(idnumber);}
    public void removeOldMerchantrequest(Integer requestid){merchantrequestMapper.removeOldMerchantrequest(requestid);}
}
