package com.example.smartairportsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightid;

    private String name;
    private Integer companyid;
    private String takeofflocation;
    private String landinglocation;
    private String departuretime;
    private String landingtime;
    private String departuregate;
    private Integer terminal;

    public flight(String name,Integer companyid,String takeofflocation,String landinglocation,String departuretime,String landingtime,String departuregate,Integer terminal){
        this.name = name;
        this.companyid = companyid;
        this.takeofflocation = takeofflocation;
        this.landinglocation = landinglocation;
        this.departuretime = departuretime;
        this.landingtime = landingtime;
        this.departuregate = departuregate;
        this.terminal = terminal;
    }

    public Integer getFlightid() {
        return flightid;
    }

    public void setFlightid(Integer flightid) {
        this.flightid = flightid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getTakeofflocation() {
        return takeofflocation;
    }

    public void setTakeofflocation(String takeofflocation) {
        this.takeofflocation = takeofflocation;
    }

    public String getLandinglocation() {
        return landinglocation;
    }

    public void setLandinglocation(String landinglocation) {
        this.landinglocation = landinglocation;
    }

    public String getDeparturetime() {
        return departuretime;
    }

    public void setDeparturetime(String departuretime) {
        this.departuretime = departuretime;
    }

    public String getLandingtime() {
        return landingtime;
    }

    public void setLandingtime(String landingtime) {
        this.landingtime = landingtime;
    }

    public String getDeparturegate() {
        return departuregate;
    }

    public void setDeparturegate(String departuregate) {
        this.departuregate = departuregate;
    }

    public Integer getTerminal() {
        return terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }
}
