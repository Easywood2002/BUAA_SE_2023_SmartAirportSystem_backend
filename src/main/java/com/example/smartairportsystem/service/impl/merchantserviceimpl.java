package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.mapper.merchantmapper;
import com.example.smartairportsystem.entity.merchant;
import com.example.smartairportsystem.service.merchantservice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("merchantservice")
public class merchantserviceimpl implements merchantservice{
    @Autowired
    private merchantmapper merchantMapper;

    public void logupNewMerchant(merchant newmerchant){merchantMapper.logupNewMerchant(newmerchant);}
    public merchant getMerchantByID(Integer merchantid){return merchantMapper.getMerchantByID(merchantid);}
    public merchant getMerchantByEmail(String email,Integer exceptid){return merchantMapper.getMerchantByEmail(email,exceptid);}
    public merchant getMerchantByIdnumber(String idnumber,Integer exceptid){return merchantMapper.getMerchantByIdnumber(idnumber,exceptid);}
    public List<merchant> listAllMerchant(){return merchantMapper.listAllMerchant();}
    public void updatePassword(Integer merchantid, String newpasswords){merchantMapper.updatePassword(merchantid,newpasswords);}
    public void updateOldMerchant(merchant newmerchant){merchantMapper.updateOldMerchant(newmerchant);}
    public void removeOldMerchant(Integer merchantid){merchantMapper.removeOldMerchant(merchantid);}
}
