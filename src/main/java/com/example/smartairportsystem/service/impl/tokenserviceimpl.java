package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.mapper.tokenmapper;
import com.example.smartairportsystem.entity.token;
import com.example.smartairportsystem.service.tokenservice;
import com.example.smartairportsystem.utils.TokenTypeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tokenservice")
public class tokenserviceimpl implements tokenservice {
    @Autowired
    private tokenmapper tokenMapper;

    public void loginNewToken(token newtoken,int type){
        switch (type){
            case TokenTypeUtil.TOURIST:
                tokenMapper.loginNewToken_TOURIST(newtoken);
                break;
            case TokenTypeUtil.MERCHANT:
                tokenMapper.loginNewToken_MERCHANT(newtoken);
                break;
            case TokenTypeUtil.STAFF:
                tokenMapper.loginNewToken_STAFF(newtoken);
                break;
            case TokenTypeUtil.COMPANY:
                tokenMapper.loginNewToken_COMPANY(newtoken);
                break;
            default:
                break;
        }
    }
    public void updateOldToken(token newtoken,int type){
        switch (type){
            case TokenTypeUtil.TOURIST:
                tokenMapper.updateOldToken_TOURIST(newtoken);
                break;
            case TokenTypeUtil.MERCHANT:
                tokenMapper.updateOldToken_MERCHANT(newtoken);
                break;
            case TokenTypeUtil.STAFF:
                tokenMapper.updateOldToken_STAFF(newtoken);
                break;
            case TokenTypeUtil.COMPANY:
                tokenMapper.updateOldToken_COMPANY(newtoken);
                break;
            default:
                break;
        }
    }
    public token getTokenByID(Integer id,int type){
        token rttoken = null;
        switch (type){
            case TokenTypeUtil.TOURIST:
                rttoken = tokenMapper.getTokenByID_TOURIST(id);
                break;
            case TokenTypeUtil.MERCHANT:
                rttoken = tokenMapper.getTokenByID_MERCHANT(id);
                break;
            case TokenTypeUtil.STAFF:
                rttoken = tokenMapper.getTokenByID_STAFF(id);
                break;
            case TokenTypeUtil.COMPANY:
                rttoken = tokenMapper.getTokenByID_COMPANY(id);
                break;
            default:
                break;
        }
        return rttoken;
    }
}
