package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class merchantrequest {
    @Id
    private Integer loginRequest;

    private String realname;
    private String nickname;
    private String passwords;
    private String salt;
    private String email;

    public merchantrequest(Integer loginRequest, String realname,String nickname,String passwords,String salt,String email){
        this.loginRequest = loginRequest;
        this.realname = realname;
        this.nickname = nickname;
        this.passwords = passwords;
        this.salt = salt;
        this.email = email;
    }

    public Integer getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(Integer loginRequest) {
        this.loginRequest = loginRequest;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
