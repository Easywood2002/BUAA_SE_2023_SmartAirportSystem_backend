package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.token;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface tokenmapper {
    public void loginNewToken_TOURIST(token newtoken);
    public void loginNewToken_MERCHANT(token newtoken);
    public void loginNewToken_STAFF(token newtoken);
    public void loginNewToken_COMPANY(token newtoken);

    public void updateOldToken_TOURIST(token newtoken);
    public void updateOldToken_MERCHANT(token newtoken);
    public void updateOldToken_STAFF(token newtoken);
    public void updateOldToken_COMPANY(token newtoken);

    public token getTokenByID_TOURIST(Integer id);
    public token getTokenByID_MERCHANT(Integer id);
    public token getTokenByID_STAFF(Integer id);
    public token getTokenByID_COMPANY(Integer id);
}
