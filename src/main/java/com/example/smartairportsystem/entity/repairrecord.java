package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class repairrecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordid;

    private String deviceinfo;
    private String location;
    private String approved;
    private String devicename;
    private String devicepicture;
    private String deviceid;

    public repairrecord(String deviceinfo,String location,String approved,String devicename,String devicepicture,String deviceid){
        this.deviceinfo = deviceinfo;
        this.location = location;
        this.approved = approved;
        this.devicename = devicename;
        this.devicepicture = devicepicture;
        this.deviceid = deviceid;
    }

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public String getDeviceinfo() {
        return deviceinfo;
    }

    public void setDeviceinfo(String deviceinfo) {
        this.deviceinfo = deviceinfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDevicepicture() {
        return devicepicture;
    }

    public void setDevicepicture(String devicepicture) {
        this.devicepicture = devicepicture;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }
}
