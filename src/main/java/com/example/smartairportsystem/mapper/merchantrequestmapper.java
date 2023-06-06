package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.merchantrequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface merchantrequestmapper {
    public void addNewMerchantrequest(merchantrequest newrequest);
    public merchantrequest getMerchantrequestByEmail(String email);
    public merchantrequest getMerchantrequestByID(Integer requestid);
    public merchantrequest getMerchantrequestByIdnumber(String idnumber);
    public void removeOldMerchantrequest(Integer requestid);
}
