package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class tourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer touristid;

    private String nickname;
    private Boolean vip;
    private String realname;
    private String passwords;

    public tourist(String nickname,Boolean vip,String realname,String passwords){
        this.nickname = nickname;
        this.vip = vip;
        this.realname = realname;
        this.passwords = passwords;
    }

    public Integer getTouristid(){
        return touristid;
    }

    public void setTouristid(Integer touristid){
        this.touristid = touristid;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public Boolean getVip(){
        return this.vip;
    }

    public void setVip(Boolean vip){
        this.vip = vip;
    }

    public String getRealname(){
        return this.realname;
    }

    public void setRealname(String realname){
        this.realname = realname;
    }

    public String getPasswords(){
        return passwords;
    }

    public void setPasswords(String passwords){
        this.passwords = passwords;
    }
}
