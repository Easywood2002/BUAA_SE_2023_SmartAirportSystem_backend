package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class airlinecompany {
    @Id
    private Integer companyid;

    private String email;
    private String name;
    private String passwords;
    private String salt;

    public airlinecompany(Integer companyid,String email,String name,String passwords,String salt){
        this.companyid = companyid;
        this.email = email;
        this.name = name;
        this.passwords = passwords;
        this.salt = salt;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
