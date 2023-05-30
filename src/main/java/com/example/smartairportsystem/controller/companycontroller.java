package com.example.smartairportsystem.controller;

import com.example.smartairportsystem.entity.airlinecompany;
import com.example.smartairportsystem.entity.flight;
import com.example.smartairportsystem.entity.token;
import com.example.smartairportsystem.service.companyservice;
import com.example.smartairportsystem.service.flightservice;
import com.example.smartairportsystem.service.impl.companyserviceimpl;
import com.example.smartairportsystem.service.impl.flightserviceimpl;
import com.example.smartairportsystem.service.impl.securityserviceimpl;
import com.example.smartairportsystem.service.impl.tokenserviceimpl;
import com.example.smartairportsystem.service.securityservice;
import com.example.smartairportsystem.service.tokenservice;
import com.example.smartairportsystem.utils.TokenTypeUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping(value = "/company")
public class companycontroller {
    @Resource
    private companyservice companyService = new companyserviceimpl();
    @Resource
    private tokenservice tokenService = new tokenserviceimpl();
    @Resource
    private securityservice securityService = new securityserviceimpl();
    @Resource
    private flightservice flightService = new flightserviceimpl();

    //航空公司注册功能
    @RequestMapping(value = "/logup", method = RequestMethod.POST)
    public Map<String, Object> logupNewTourist(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String name = rawmap.get("name");
        String passwords = rawmap.get("passwords");
        String repasswords = rawmap.get("repasswords");

        try {
            if (repasswords.equals(passwords)) {
                //对用户设置的密码加盐加密后保存
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt() + "";
                airlinecompany newcompany = new airlinecompany(name, passwords + salt, salt);
                airlinecompany exist = companyService.getCompanyByName(name);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "航司重复注册！");
                } else {
                    companyService.logupNewCompany(newcompany);
                    map.put("success", true);
                    map.put("message", "航司注册成功！");
                }
            } else {
                map.put("success", false);
                map.put("message", "确认密码不一致！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "航司注册失败！");
        }
        return map;
    }

    //航空公司登录功能
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> loginOldTourist(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();
        map.put("token", "null");

        //表单取参
        String name = rawmap.get("name");
        String passwords = rawmap.get("passwords");

        try {
            airlinecompany exist = companyService.getCompanyByName(name);
            if (exist != null) {
                //取出用户盐值，与当前输入的密码拼接加密后再与数据库中的信息进行比较
                String inpwd = securityService.SHA1(passwords + exist.getSalt());
                if (inpwd.equals(exist.getPasswords())) {
                    //将用户id经md5加密后作为token一并返回前端，便于后续访问
                    String companytk = securityService.MD5(exist.getCompanyid().toString());
                    token newtk = new token(exist.getCompanyid(), companytk);
                    token existtk = tokenService.getTokenByID(newtk.getId(), TokenTypeUtil.COMPANY);
                    if (existtk == null) {
                        tokenService.loginNewToken(newtk, TokenTypeUtil.COMPANY);
                    } else {
                        tokenService.updateOldToken(newtk, TokenTypeUtil.COMPANY);
                    }
                    map.put("success", true);
                    map.put("message", "航司登录成功！");
                    map.put("token", companytk);
                } else {
                    map.put("success", false);
                    map.put("message", "航司密码错误！");
                }
            } else {
                map.put("success", false);
                map.put("message", "航空公司不存在！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "航司登录失败！");
        }
        return map;
    }

    //航司添加航班信息功能
    @RequestMapping(value = "/addflight", method = RequestMethod.POST)
    public Map<String, Object> addFlight(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String companytk = rawmap.get("token");
        String name = rawmap.get("name");
        String takeofflocation = rawmap.get("takeofflocation");
        String landinglocation = rawmap.get("landinglocation");
        String departuretime = rawmap.get("departuretime");
        String landingtime = rawmap.get("landingtime");
        String departuregate = rawmap.get("departuregate");
        String terminal = rawmap.get("terminal");

        try {
            airlinecompany company = companyService.getCompanyByToken(companytk);
            String flightid = securityService.MD5(company.getName()+name+departuretime);
            flight newflight = new flight(flightid,name,company.getCompanyid(),takeofflocation,landinglocation,departuretime,landingtime,departuregate,Integer.parseInt(terminal));
            flight exist = flightService.getFlightByID(flightid);
            if (exist != null) {
                map.put("success", false);
                map.put("message", "航班信息已存在！");
            } else {
                flightService.addNewFlight(newflight);
                map.put("success", true);
                map.put("message", "添加航班信息成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "添加航班信息失败！");
        }
        return map;
    }

    //航司修改航班信息功能
    /*@RequestMapping(value = "/updateflight", method = RequestMethod.POST)
    public Map<String, Object> updateFlight(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String companytk = rawmap.get("token");
        String flightid = rawmap.get("flightid");
        String name = rawmap.get("name");
        String takeofflocation = rawmap.get("takeofflocation");
        String landinglocation = rawmap.get("landinglocation");
        String departuretime = rawmap.get("departuretime");
        String landingtime = rawmap.get("landingtime");
        String departuregate = rawmap.get("departuregate");
        String terminal = rawmap.get("terminal");

        try {
            airlinecompany company = companyService.getCompanyByToken(companytk);
            flight newflight = new flight(flightid,name,company.getCompanyid(),takeofflocation,landinglocation,departuretime,landingtime,departuregate,Integer.parseInt(terminal));
            flight exist = flightService.getFlightByID(flightid);
            if (exist != null) {
                map.put("success", false);
                map.put("message", "航班信息已存在！");
            } else {
                flightService.addNewFlight(newflight);
                map.put("success", true);
                map.put("message", "添加航班信息成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "添加航班信息失败！");
        }
        return map;
    }*/
}
