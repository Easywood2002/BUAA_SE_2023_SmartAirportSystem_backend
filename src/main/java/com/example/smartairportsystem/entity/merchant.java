package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class merchant {
    @Id
    private Integer merchantid;

    private String realname;
    private String passwords;
    private String salt;
    private String shopname;
    private String email;
    private String numberid;

    public merchant(Integer merchantid, String realname,String passwords,String salt,String shopname,String email,String numberid){
        this.merchantid = merchantid;
        this.realname = realname;
        this.salt = salt;
        this.passwords = passwords;
        this.shopname = shopname;
        this.email = email;
        this.numberid = numberid;
    }

    public Integer getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(Integer merchantid) {
        this.merchantid = merchantid;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getNumberid() {
        return numberid;
    }

    public void setNumberid(String numberid) {
        this.numberid = numberid;
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
