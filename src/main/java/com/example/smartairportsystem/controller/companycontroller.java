package com.example.smartairportsystem.controller;

import com.example.smartairportsystem.entity.airlinecompany;
import com.example.smartairportsystem.entity.flight;
import com.example.smartairportsystem.entity.ticket;
import com.example.smartairportsystem.entity.token;
import com.example.smartairportsystem.service.*;
import com.example.smartairportsystem.service.impl.*;
import com.example.smartairportsystem.utils.TokenTypeUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    private ticketservice ticketService = new ticketserviceimpl();

    //航空公司注册功能
    @RequestMapping(value = "/logup", method = RequestMethod.POST)
    public Map<String, Object> logupCompany(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String email = rawmap.get("email");
        String name = rawmap.get("name");
        String passwords = rawmap.get("passwords");
        String repasswords = rawmap.get("repasswords");

        try {
            if (repasswords.equals(passwords)) {
                //对用户设置的密码加盐加密后保存
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt() + "";
                airlinecompany newcompany = new airlinecompany(email,name, passwords + salt, salt);
                airlinecompany exist = companyService.getCompanyByEmail(email);
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
    public Map<String, Object> loginCompany(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();
        map.put("token", "null");

        //表单取参
        String email = rawmap.get("email");
        String passwords = rawmap.get("passwords");

        try {
            airlinecompany exist = companyService.getCompanyByEmail(email);
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

    //列出该航司的航班信息功能
    @RequestMapping(value = "/listflight",method = RequestMethod.POST)
    public Map<String,Object> listFlight(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String companytk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.COMPANY);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "航司未登录或已注销登录！");
            }else {
                List<flight> rtlist = flightService.listFlightByCompanyid(tokenentity.getId());
                map.put("success", true);
                map.put("message", rtlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取列表失败！");
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
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.COMPANY);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "航司未登录或已注销登录！");
            }else {
                flight newflight = new flight(name, tokenentity.getId(), takeofflocation, landinglocation, departuretime, landingtime, departuregate, Integer.parseInt(terminal));
                flight exist = flightService.getFlightByCombine(name,tokenentity.getId(),departuretime);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "航班信息已存在！");
                } else {
                    flightService.addNewFlight(newflight);
                    map.put("success", true);
                    map.put("message", "添加航班信息成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "添加航班信息失败！");
        }
        return map;
    }

    //航司修改航班信息功能
     @RequestMapping(value = "/updateflight", method = RequestMethod.POST)
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
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.COMPANY);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "航司未登录或已注销登录！");
            }else {
                flight newflight = new flight(name, tokenentity.getId(), takeofflocation, landinglocation, departuretime, landingtime, departuregate, Integer.parseInt(terminal));
                newflight.setFlightid(Integer.parseInt(flightid));
                flight exist = flightService.getFlightByID(Integer.parseInt(flightid));
                if (exist != null) {
                    flight conflict = flightService.getFlightByCombine(name,tokenentity.getId(),departuretime);
                    if(conflict != null){
                        map.put("success", false);
                        map.put("message", "已存在相同航班信息！");
                    }else {
                        flightService.updateOldFlight(newflight);
                        map.put("success", true);
                        map.put("message", "航班信息已更新！");
                    }
                } else {
                    map.put("success", false);
                    map.put("message", "航班信息不存在！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改航班信息失败！");
        }
        return map;
    }

    //航司删除航班信息功能
    @RequestMapping(value = "/removeflight", method = RequestMethod.POST)
    public Map<String, Object> removeFlight(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String companytk = rawmap.get("token");
        String flightid = rawmap.get("flightid");

        try {
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.COMPANY);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "航司未登录或已注销登录！");
            }else {
                flight exist = flightService.getFlightByID(Integer.parseInt(flightid));
                if (exist != null) {
                    flightService.removeOldFlight(Integer.parseInt(flightid));
                    map.put("success", true);
                    map.put("message", "航班信息已删除！");
                } else {
                    map.put("success", false);
                    map.put("message", "航班信息不存在！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "删除航班信息失败！");
        }
        return map;
    }

    //航司添加机票信息功能
    @RequestMapping(value = "/addticket", method = RequestMethod.POST)
    public Map<String, Object> addTicket(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String companytk = rawmap.get("token");
        String flightid = rawmap.get("flightid");
        String tickettype = rawmap.get("tickettype");
        String price = rawmap.get("price");
        String amount = rawmap.get("amount");

        try {
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.COMPANY);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "航司未登录或已注销登录！");
            }else {
                ticket newticket = new ticket(Integer.parseInt(flightid), tickettype, Double.parseDouble(price), Integer.parseInt(amount));
                ticket exist = ticketService.getTicketByCombine(Integer.parseInt(flightid),tickettype);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "机票信息已存在！");
                } else {
                    ticketService.addNewTicket(newticket);
                    map.put("success", true);
                    map.put("message", "添加机票信息成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "添加机票信息失败！");
        }
        return map;
    }

    //航司修改机票信息功能
    @RequestMapping(value = "/updateticket", method = RequestMethod.POST)
    public Map<String, Object> updateTicket(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String companytk = rawmap.get("token");
        String ticketid = rawmap.get("ticketid");
        String tickettype = rawmap.get("tickettype");
        String price = rawmap.get("price");
        String amount = rawmap.get("amount");

        try {
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.COMPANY);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "航司未登录或已注销登录！");
            }else {
                ticket newticket = new ticket(0, tickettype, Double.parseDouble(price), Integer.parseInt(amount));
                newticket.setTicketid(Integer.parseInt(ticketid));
                ticket exist = ticketService.getTicketByID(Integer.parseInt(ticketid));
                if (exist != null) {
                    ticket conflict = ticketService.getTicketByCombine(exist.getFlightid(),tickettype);
                    if(conflict != null){
                        map.put("success", false);
                        map.put("message", "已存在相同机票信息！");
                    }else {
                        ticketService.updateOldTicket(newticket);
                        map.put("success", true);
                        map.put("message", "机票信息已更新！");
                    }
                } else {
                    map.put("success", false);
                    map.put("message", "机票信息不存在！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改机票信息失败！");
        }
        return map;
    }

    //航司删除机票信息功能
    @RequestMapping(value = "/removeticket", method = RequestMethod.POST)
    public Map<String, Object> removeTicket(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String companytk = rawmap.get("token");
        String ticketid = rawmap.get("ticketid");

        try {
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.COMPANY);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "航司未登录或已注销登录！");
            }else {
                ticket exist = ticketService.getTicketByID(Integer.parseInt(ticketid));
                if (exist != null) {
                    ticketService.removeOldTicket(Integer.parseInt(ticketid));
                    map.put("success", true);
                    map.put("message", "机票信息已删除！");

                } else {
                    map.put("success", false);
                    map.put("message", "机票信息不存在！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "删除机票信息失败！");
        }
        return map;
    }

    //列出该航班的机票信息功能
    @RequestMapping(value = "/listticket",method = RequestMethod.POST)
    public Map<String,Object> listTicket(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String companytk = rawmap.get("token");
        String flightid = rawmap.get("flightid");

        try {
            token tokenentity = tokenService.getTokenByToken(companytk,TokenTypeUtil.COMPANY);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "航司未登录或已注销登录！");
            }else {
                List<ticket> rtlist = ticketService.listTicketByFlightid(Integer.parseInt(flightid));
                map.put("success", true);
                map.put("message", rtlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取列表失败！");
        }
        return map;
    }
}
