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
    private String email;
    private String passwords;
    private String salt;

    public staff(String realname, String positionpost,String email,String passwords,String salt){
        this.realname = realname;
        this.positionpost = positionpost;
        this.email = email;
        this.passwords = passwords;
        this.salt = salt;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
