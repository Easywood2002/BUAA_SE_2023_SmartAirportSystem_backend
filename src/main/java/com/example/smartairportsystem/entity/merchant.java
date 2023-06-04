package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class merchant {
    @Id
    private Integer merchantid;

    private String realname;
    private Integer idnumber;
    private String passwords;
    private String salt;
    private String shopname;
    private String email;

    public merchant(Integer merchantid, String realname, Integer idnumber, String passwords,String salt,String shopname,String email){
        this.merchantid = merchantid;
        this.realname = realname;
        this.idnumber = idnumber;
        this.passwords = passwords;
        this.salt = salt;
        this.shopname = shopname;
        this.email = email;
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

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
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

    public Integer getID() {
        return idnumber;
    }

    public void setID(String idnumber) {
        this.idnumber = idnumber;
    }
}
