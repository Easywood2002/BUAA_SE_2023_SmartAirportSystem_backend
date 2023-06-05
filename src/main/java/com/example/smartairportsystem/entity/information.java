package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class information {
    @Id
    private Integer informationid;

    private String type;
    private Integer touristid;
    private Integer staffid;
    private String sendtime;
    private String content;

    public information(Integer informationid,String type,Integer touristid,Integer staffid,String sendtime,String content){
        this.informationid = informationid;
        this.type = type;
        this.touristid = touristid;
        this.staffid = staffid;
        this.sendtime = sendtime;
        this.content = content;
    }

    public Integer getInformationid() {
        return informationid;
    }

    public void setInformationid(Integer informationid) {
        this.informationid = informationid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTouristid() {
        return touristid;
    }

    public void setTouristid(Integer touristid) {
        this.touristid = touristid;
    }

    public Integer getStaffid() {
        return staffid;
    }

    public void setStaffid(Integer staffid) {
        this.staffid = staffid;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
