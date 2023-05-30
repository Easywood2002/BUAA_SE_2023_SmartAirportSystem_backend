package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.mapper.touristmapper;
import com.example.smartairportsystem.entity.tourist;
import com.example.smartairportsystem.service.touristservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("touristservice")
public class touristserviceimpl implements touristservice{
    @Autowired
    private touristmapper touristMapper;

    public void logupNewTourist(tourist newtourist){touristMapper.logupNewTourist(newtourist);}
    public tourist getTouristByNickname(String nickname){return touristMapper.getTouristByNickname(nickname);}
    public String correspondPasswords(String passwords){return touristMapper.correspondPasswords(passwords);}
}
