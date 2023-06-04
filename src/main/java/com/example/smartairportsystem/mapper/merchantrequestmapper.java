package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.merchantrequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface merchantrequestmapper {
    public void logupNewMerchantrequest(merchantrequest logupNewMerchantrequest);
}
