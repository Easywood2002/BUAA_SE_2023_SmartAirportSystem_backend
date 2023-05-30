package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class airlinecompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyid;

    private String name;
    private String passwords;
    private String salt;

    public airlinecompany(String name,String passwords,String salt){
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
