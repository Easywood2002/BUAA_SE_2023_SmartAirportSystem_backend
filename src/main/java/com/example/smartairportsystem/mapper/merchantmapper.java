package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.merchant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface merchantmapper {
    public void logupNewMerchant(merchant newmerchant);
    public merchant getMerchantByEmail(String email);
    public merchant getMerchantByID(Integer merchantid);
    public merchant getMerchantByIDnumber(Integer idnumber);
    // public void updateOldMerchant(person newmerchant);
    // public void removeOldMerchant(Integer merchantid);
    public List<merchant> listMerchantByMerchantid(Integer merchantid);
}
