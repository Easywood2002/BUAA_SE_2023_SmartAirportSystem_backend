package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.merchantrequest;

public interface merchantrequestservice { 
    public void addNewMerchantrequest(merchantrequest newmerchantrequest);
    public merchantrequest getMerchantrequestByEmail(String email);
    public void removeOldMerchantrequest(String email);
}