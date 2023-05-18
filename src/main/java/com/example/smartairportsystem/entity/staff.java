package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffid;

    private String realname;
    private String positionpost;
    private String nickname;
    private String passwords;

    public staff(String realname, String positionpost,String nickname,String passwords){
        this.realname = realname;
        this.positionpost = positionpost;
        this.nickname = nickname;
        this.passwords = passwords;
    }

    public Integer getStaffid(){
        return this.staffid;
    }

    public void setStaffid(Integer staffid){
        this.staffid = staffid;
    }

    public String getRealname(){
        return this.realname;
    }

    public void setRealname(String realname){
        this.realname = realname;
    }

    public String getPositionpost(){
        return this.positionpost;
    }

    public void setPositionpost(String positionpost){
        this.positionpost = positionpost;
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
}
