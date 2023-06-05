package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.mapper.merchantrequestmapper;
import com.example.smartairportsystem.entity.merchantrequest;
import com.example.smartairportsystem.service.merchantrequestservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("merchantrequestservice")
public class merchantrequestserviceimpl implements merchantrequestservice{
    @Autowired
    private merchantmapper merchantMapper;

    public void addNewMerchantrequest(merchantrequest newmerchantrequest){merchantrequestMapper.logupNewMerchantrequest(newmerchantrequest);}
    public merchantrequest getMerchantrequestByEmail(String email){merchantrequestMapper.getMerchantrequestBtEmail(email);}
    public void removeOldMerchantrequest(String email){merchantrequestMapper.removeOldMerchantrequest(email);}
}
