package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.token;

public interface tokenservice {
    public void loginNewToken(token newtoken,int type);
    public void updateOldToken(token newtoken,int type);
    public token getTokenByID(Integer id,int type);
    public token getTokenByToken(String token,int type);
}
