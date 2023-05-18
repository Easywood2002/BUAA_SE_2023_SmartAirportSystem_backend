package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer merchantid;

    private String realname;
    private String passwords;
    private String nickname;

    public merchant(String realname,String passwords,String nickname){
        this.realname = realname;
        this.passwords = passwords;
        this.nickname = nickname;
    }

    public Integer getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Integer merchantid) {
        this.merchantid = merchantid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
