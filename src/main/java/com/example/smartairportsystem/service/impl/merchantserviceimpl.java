package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.mapper.merchantmapper;
import com.example.smartairportsystem.entity.merchant;
import com.example.smartairportsystem.service.merchantservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("merchantservice")
public class merchantserviceimpl implements merchantservice{
    @Autowired
    private merchantmapper merchantMapper;

    public void logupNewMerchant(merchant newmerchant){merchantMapper.logupNewMerchant(newmerchant);}
    public merchant getMerchantByEmail(String email){return merchantMapper.getMerchantByEmail(email);}
    public merchant getMerchantByID(Integer merchantid){return merchantMapper.getMerchantByID(merchantid);}
    public merchant getMerchantByIDnumber(Integer idnumber){return merchantMapper.getMerchantByIDnumber(idnumber);}
    public merchant getMerchantByShopname(String shopname){return merchantMapper.getMerchantByShopname(shopname);}
    public void updateOldMerchant(merchant newmerchant){merchantMapper.updateOldMerchant(newmerchant);}
    public void removeOldMerchant(Integer merchantid){merchantMapper.removeOldMerchant(merchantid);}
}
