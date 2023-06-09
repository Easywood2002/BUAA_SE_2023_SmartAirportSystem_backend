package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.merchant;

import java.util.List;

public interface merchantservice {
    public void logupNewMerchant(merchant newmerchant);
    public merchant getMerchantByID(Integer merchantid);
    public merchant getMerchantByEmail(String email,Integer exceptid);
    public merchant getMerchantByIdnumber(String idnumber,Integer exceptid);
    public List<merchant> listAllMerchant();
    public void updatePassword(Integer merchantid, String newpasswords);
    public void updateOldMerchant(merchant newmerchant);
    public void removeOldMerchant(Integer merchantid);
}