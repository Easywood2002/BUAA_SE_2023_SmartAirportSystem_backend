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
    private String idnumber;

    public merchant(Integer merchantid, String realname,String passwords,String salt,String shopname,String email,String idnumber){
        this.merchantid = merchantid;
        this.realname = realname;
        this.salt = salt;
        this.passwords = passwords;
        this.shopname = shopname;
        this.email = email;
        this.idnumber = idnumber;
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

    public String Idnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
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
