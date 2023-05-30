package com.example.smartairportsystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface securitymapper {
    public String SHA1(String code);
    public String MD5(String code);
}
