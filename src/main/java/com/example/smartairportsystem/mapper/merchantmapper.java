package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface merchantmapper {
    public void logupNewMerchant(merchant newmerchant);
    public merchant getMerchantByID(Integer merchantid);
    public merchant getMerchantByEmail(String email);
    public merchant getMerchantByIdnumber(String idnumber);
    public List<merchant> listAllMerchant();
    public void updateOldMerchant(merchant newmerchant);
    public void updatePassword(@Param("merchantid") Integer merchantid, @Param("newpasswords") String newpasswords);
    public void removeOldMerchant(Integer merchantid);
}
