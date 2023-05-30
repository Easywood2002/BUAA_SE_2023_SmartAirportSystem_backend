package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.tourist;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface touristmapper {
    public void logupNewTourist(tourist newtourist);
    public tourist getTouristByNickname(String nickname);
    public String correspondPasswords(String passwords);
}
