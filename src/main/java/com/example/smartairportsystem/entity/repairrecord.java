package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class repairrecord {
    @Id
    private Integer recordid;

    private Integer deviceid;
    private String deviceinfo;
    private String devicename;
    private String devicepicture;
    private String location;
    private String approved;

    public repairrecord(Integer recordid,Integer deviceid,String devicename,String devicepicture,String deviceinfo,String location,String approved){
        this.recordid = recordid;
        this.deviceid = deviceid;
        this.devicename = devicename;
        this.devicepicture = devicepicture;
        this.deviceinfo = deviceinfo;
        this.location = location;
        this.approved = approved;
    }

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
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
}
