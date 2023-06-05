package com.example.smartairportsystem.controller;

import com.example.smartairportsystem.entity.*;
import com.example.smartairportsystem.entity.bowl.eticket;
import com.example.smartairportsystem.entity.bowl.myparkingorder;
import com.example.smartairportsystem.entity.bowl.seat;
import com.example.smartairportsystem.service.*;
import com.example.smartairportsystem.service.impl.*;

import com.example.smartairportsystem.utils.TimeFormatUtil;
import com.example.smartairportsystem.utils.TokenTypeUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/tourist")
public class touristcontroller {
    @Resource
    private touristservice touristService = new touristserviceimpl();
    @Resource
    private tokenservice tokenService = new tokenserviceimpl();
    @Resource
    private securityservice securityService = new securityserviceimpl();
    @Resource
    private personservice personService = new personserviceimpl();
    @Resource
    private flightservice flightService = new flightserviceimpl();
    @Resource
    private ticketservice ticketService = new ticketserviceimpl();
    @Resource
    private purchaserecordservice purchaserecordService = new purchaserecordserviceimpl();
    @Resource
    private parkingorderservice parkingorderService = new parkingorderserviceimpl();
    @Resource
    private parkingspaceservice parkingspaceService = new parkingspaceserviceimpl();

    //旅客用户注册功能
    @RequestMapping(value = "/logup",method = RequestMethod.POST)
    public Map<String,Object> logupTourist(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String email = rawmap.get("email");
        String passwords = rawmap.get("passwords");
        String repasswords = rawmap.get("repasswords");

        try{
            if(repasswords.equals(passwords)) {
                //对用户设置的密码加盐加密后保存
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt()+"";
                tourist exist = touristService.getTouristByEmail(email);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "邮箱已被注册！");
                } else {
                    touristService.logupNewTourist(new tourist(0,email,passwords+salt,salt, "false"));
                    map.put("success", true);
                    map.put("message", "用户注册成功！");
                }
            }else{
                map.put("success", false);
                map.put("message", "确认密码不一致！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","用户注册失败！");
        }
        return map;
    }

    //旅客用户登录功能
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> loginTourist(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();
        map.put("token", "null");

        //表单取参
        String email = rawmap.get("email");
        String passwords = rawmap.get("passwords");

        try{
            tourist exist = touristService.getTouristByEmail(email);
            if (exist != null) {
                //取出用户盐值，与当前输入的密码拼接加密后再与数据库中的信息进行比较
                String inpwd = securityService.SHA1(passwords+exist.getSalt());
                if(inpwd.equals(exist.getPasswords())) {
                    //将用户id经md5加密后作为token一并返回前端，便于后续访问
                    String touristtk = securityService.MD5(exist.getTouristid().toString());
                    token newtk = new token(exist.getTouristid(),touristtk);
                    token existtk = tokenService.getTokenByID(newtk.getId(), TokenTypeUtil.TOURIST);
                    if (existtk == null){
                        tokenService.loginNewToken(newtk, TokenTypeUtil.TOURIST);
                    }else{
                        tokenService.updateOldToken(newtk, TokenTypeUtil.TOURIST);
                    }
                    map.put("success", true);
                    map.put("message", "用户登录成功！");
                    map.put("token",touristtk);
                }else {
                    map.put("success", false);
                    map.put("message", "用户密码错误！");
                }
            } else {
                map.put("success", false);
                map.put("message", "旅客用户不存在！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","用户登录失败！");
        }
        return map;
    }

    //旅客用户修改密码功能
    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public Map<String,Object> updatePassword(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String newpasswords = rawmap.get("newpasswords");
        String renewpasswords = rawmap.get("renewpasswords");
        String passwords = rawmap.get("passwords");

        try{
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                if(renewpasswords.equals(newpasswords)) {
                    tourist exist = touristService.getTouristByID(tokenentity.getId());
                    String inpwd = securityService.SHA1(passwords+exist.getSalt());
                    if(inpwd.equals(exist.getPasswords())) {
                        touristService.updatePassword(tokenentity.getId(),newpasswords+exist.getSalt());
                        map.put("success", true);
                        map.put("message", "修改密码成功！");
                    }else{
                        map.put("success", false);
                        map.put("message", "用户密码错误！");
                    }
                }else{
                    map.put("success",false);
                    map.put("message","确认密码不一致！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","修改密码失败！");
        }
        return map;
    }

    //列出该用户的实名信息功能
    @RequestMapping(value = "/listperson",method = RequestMethod.POST)
    public Map<String,Object> listPerson(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                List<person> rtlist = personService.listPersonByTouristid(tokenentity.getId());
                map.put("success", true);
                map.put("message", rtlist);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取列表失败！");
        }
        return map;
    }

    //旅客用户添加实名信息功能
    @RequestMapping(value = "/addperson",method = RequestMethod.POST)
    public Map<String,Object> addPerson(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String realname = rawmap.get("realname");
        String idnumber = rawmap.get("idnumber");
        String email = rawmap.get("email");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                person exist = personService.getPersonByCombine(tokenentity.getId(),idnumber);
                if(exist != null){
                    map.put("success", false);
                    map.put("message", "实名信息已存在！");
                }else{
                    personService.addNewPerson(new person(0,tokenentity.getId(),realname,idnumber,email));
                    map.put("success", true);
                    map.put("message", "添加实名信息成功！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "添加实名信息失败！");
        }
        return map;
    }

    //旅客用户修改实名信息功能
    @RequestMapping(value = "/updateperson",method = RequestMethod.POST)
    public Map<String,Object> updatePerson(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String personid = rawmap.get("personid");
        String realname = rawmap.get("realname");
        String idnumber = rawmap.get("idnumber");
        String email = rawmap.get("email");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                person conflict = personService.getPersonByCombine(tokenentity.getId(),idnumber);
                if(conflict != null){
                    map.put("success", false);
                    map.put("message", "已存在相同实名信息！");
                }else {
                    personService.updateOldPerson(new person(Integer.parseInt(personid),tokenentity.getId(),realname,idnumber,email));
                    map.put("success", true);
                    map.put("message", "实名信息已更新！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改实名信息失败！");
        }
        return map;
    }

    //旅客用户删除实名信息功能
    @RequestMapping(value = "/removeperson",method = RequestMethod.POST)
    public Map<String,Object> removePerson(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String personid = rawmap.get("personid");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                personService.removeOldPerson(Integer.parseInt(personid));
                map.put("success", true);
                map.put("message", "实名信息已删除！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "删除实名信息失败！");
        }
        return map;
    }

    //旅客用户查询航班信息功能
    @RequestMapping(value = "/searchflight",method = RequestMethod.POST)
    public Map<String,Object> searchFlight(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String takeofflocation = rawmap.get("takeofflocation");
        String landinglocation = rawmap.get("landinglocation");
        String date = rawmap.get("date");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                List<flight> rtlist = flightService.listFlightByCombine(takeofflocation,landinglocation,date+"%");
                map.put("success", true);
                map.put("message", rtlist);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取列表失败！");
        }
        return map;
    }

    //列出该航班的机票信息功能
    @RequestMapping(value = "/listticket",method = RequestMethod.POST)
    public Map<String,Object> listTicket(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String flightid = rawmap.get("flightid");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
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

    //旅客用户购票功能
    @RequestMapping(value = "/purchaseflight",method = RequestMethod.POST)
    public Map<String,Object> purchaseFlight(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String ticketid = rawmap.get("ticketid");
        String personidlist = rawmap.get("personidlist");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                String[] personids = personidlist.split("&");
                //总人数
                int len = personids.length;
                ticket tkt = ticketService.getTicketByID(Integer.parseInt(ticketid));
                int count = purchaserecordService.getCountByTicketid(Integer.parseInt(ticketid));
                if(tkt.getAmount()-count < len){
                    map.put("success", false);
                    map.put("message", "剩余机票不足！");
                }else {
                    for (String personid : personids) {
                        purchaserecordService.addNewRecord(new purchaserecord(0,Integer.valueOf(personid), Integer.parseInt(ticketid), TimeFormatUtil.getCurrentTime(), "0"));
                    }
                    map.put("success", true);
                    map.put("message", "用户购票成功");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "用户购票失败！");
        }
        return map;
    }

    //列出已购机票功能
    @RequestMapping(value = "/listpurchase",method = RequestMethod.POST)
    public Map<String,Object> listPurchase(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                List<eticket> rtlist = ticketService.listEticketByTouristid(tokenentity.getId());
                map.put("success", true);
                map.put("message", rtlist);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取列表失败！");
        }
        return map;
    }

    //旅客用户退票功能
    @RequestMapping(value = "/returnpurchase",method = RequestMethod.POST)
    public Map<String,Object> returnPurchase(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String orderid = rawmap.get("orderid");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                purchaserecordService.removeOldRecord(Integer.parseInt(orderid));
                map.put("success", true);
                map.put("message", "用户退票成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "用户退票失败！");
        }
        return map;
    }

    //列出空余座位功能
    @RequestMapping(value = "/listseat",method = RequestMethod.POST)
    public Map<String,Object> listSeat(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String orderid = rawmap.get("orderid");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                purchaserecord record = purchaserecordService.getRecordByID(Integer.parseInt(orderid));
                ticket tkt = ticketService.getTicketByID(record.getTicketid());
                int mount = tkt.getAmount();
                List<purchaserecord> prlist = purchaserecordService.getRecordByTicketid(record.getTicketid());
                List<seat> rtlist = new ArrayList<>();
                for(int i=1;i<=mount;i++){
                    //均初始化为未选择座位（可用蓝色标识）
                    rtlist.add(new seat(i,"false"));
                }
                for(purchaserecord pr : prlist){
                    //跳过尚未选座的订单
                    if(! pr.getSeatinfo().equals("0")) {
                        //根据订单信息设置为已选择座位（可用灰色标识）
                        rtlist.set(Integer.parseInt(pr.getSeatinfo()) - 1, new seat(Integer.parseInt(pr.getSeatinfo()), "true"));
                    }
                }
                //若当前用户已选择座位则单独标出（可用绿色标识）
                if(! record.getSeatinfo().equals("0")){
                    rtlist.set(Integer.parseInt(record.getSeatinfo()) - 1,new seat(Integer.parseInt(record.getSeatinfo()),"mine"));
                }
                map.put("success", true);
                map.put("message", rtlist);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取列表失败！");
        }
        return map;
    }

    //旅客用户选座功能
    @RequestMapping(value = "/selectseat",method = RequestMethod.POST)
    public Map<String,Object> selectseat(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String orderid = rawmap.get("orderid");
        String seatid = rawmap.get("seatid");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                purchaserecord record = purchaserecordService.getRecordByID(Integer.parseInt(orderid));
                //旅客尚未选座
                if(record.getSeatinfo().equals("0")) {
                    purchaserecord pr = purchaserecordService.getRecordByCombine(record.getTicketid(),seatid);
                    //座位已选
                    if(pr != null){
                        map.put("success", false);
                        map.put("message", "座位已被其他旅客选择！");
                    }else {
                        purchaserecordService.selectSeatForOrderid(record.getOrderid(),seatid);
                        map.put("success", true);
                        map.put("message", "用户选座成功！");
                    }
                }else{
                    map.put("success", false);
                    map.put("message", "不可重复选座！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "用户选座失败！");
        }
        return map;
    }

    //旅客用户预定泊车功能
    @RequestMapping(value = "/selectparking",method = RequestMethod.POST)
    public Map<String,Object> selectParking(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String starttime = rawmap.get("starttime");
        String endtime = rawmap.get("endtime");
        String parkingspaceid = rawmap.get("parkingspaceid");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                myparkingorder order = parkingorderService.getOrderByTouristid(tokenentity.getId());
                //用户尚未预定
                if(order == null) {
                    parkingorder exist = parkingorderService.getOrderBySpaceid(Integer.parseInt(parkingspaceid));
                    if(exist != null){
                        map.put("success", false);
                        map.put("message", "停车位已被其他旅客选择！");
                    }else {
                        parkingorderService.addNewOrder(new parkingorder(0, tokenentity.getId(), starttime, endtime, Integer.parseInt(parkingspaceid)));
                        map.put("success", true);
                        map.put("message", "用户预定车位成功！");
                    }
                }else{
                    map.put("success", false);
                    map.put("message", "不可重复预订！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "预定车位失败！");
        }
        return map;
    }

    //旅客用户查询预定泊车订单
    @RequestMapping(value = "/listparkingorder",method = RequestMethod.POST)
    public Map<String,Object> listparkingorder(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                List<myparkingorder> rtlist = new ArrayList<>();
                rtlist.add(parkingorderService.getOrderByTouristid(tokenentity.getId()));
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

    //旅客用户退预定泊车位订单
    @RequestMapping(value = "/returnparkingorder",method = RequestMethod.POST)
    public Map<String,Object> returnParkingorder(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");
        String orderid = rawmap.get("orderid");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                parkingorderService.removeOldOrder(Integer.parseInt(orderid));
                map.put("success", true);
                map.put("message", "车位退订成功!");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "车位退订失败！");
        }
        return map;
    }

    //列出空余车位功能
    @RequestMapping(value = "/listparkinspace",method = RequestMethod.POST)
    public Map<String,Object> listParkingspace(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String touristtk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.TOURIST);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                List<parkingspace> rtlist = parkingspaceService.listEmptyParkingspace();
                map.put("success", true);
                map.put("message", rtlist);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取列表失败！");
        }
        return map;
    }
}
