package com.example.smartairportsystem.controller;

import com.example.smartairportsystem.entity.*;
import com.example.smartairportsystem.service.*;
import com.example.smartairportsystem.service.impl.*;
import com.example.smartairportsystem.utils.TypeUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/staff")
public class staffcontroller {
    @Resource
    private staffservice staffService = new staffserviceimpl();
    @Resource
    private securityservice securityService = new securityserviceimpl();
    @Resource
    private tokenservice tokenService = new tokenserviceimpl();
    @Resource
    private repairrecordservice repairrecordService = new repairrecordserviceimpl();
    @Resource
    private merchantservice merchantService = new merchantserviceimpl();
    @Resource
    private merchantrequestservice merchantrequestService = new merchantrequestserviceimpl();

    //工作人员注册功能
    @RequestMapping(value = "/logup",method = RequestMethod.POST)
    public Map<String,Object> logupStaff(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String realname = rawmap.get("realname");
        String positionpost = rawmap.get("positionpost");
        String email = rawmap.get("email");
        String passwords = rawmap.get("passwords");
        String repasswords = rawmap.get("repasswords");
        String idnumber = rawmap.get("idnumber");

        try{
            if(repasswords.equals(passwords)) {
                //对用户设置的密码加盐加密后保存
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt()+"";
                staff exist = staffService.getStaffByIdnumber(idnumber);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "该身份证号已被注册！");
                } else {
                    exist = staffService.getStaffByEmail(email);
                    if (exist != null){
                        map.put("success", false);
                        map.put("message", "该邮箱已被注册！");
                    }else {
                        staffService.logupNewStaff(new staff(0, realname, Integer.parseInt(positionpost), email, passwords + salt, salt, idnumber));
                        map.put("success", true);
                        map.put("message", "员工注册成功！");
                    }
                }
            }else{
                map.put("success", false);
                map.put("message", "确认密码不一致！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","员工注册失败！");
        }
        return map;
    }

    //工作人员登录功能
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> loginStaff(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();
        map.put("token", "null");

        //表单取参
        String email = rawmap.get("email");
        String passwords = rawmap.get("passwords");

        try{
            staff exist = staffService.getStaffByEmail(email);
            if (exist != null) {
                //取出用户盐值，与当前输入的密码拼接加密后再与数据库中的信息进行比较
                String inpwd = securityService.SHA1(passwords+exist.getSalt());
                if(inpwd.equals(exist.getPasswords())) {
                    //将用户id经md5加密后作为token一并返回前端，便于后续访问
                    String stafftk = securityService.MD5(exist.getStaffid().toString());
                    token newtk = new token(exist.getStaffid(),stafftk);
                    token existtk = tokenService.getTokenByID(newtk.getId(), TypeUtil.Token.STAFF);
                    if (existtk == null){
                        tokenService.loginNewToken(newtk, TypeUtil.Token.STAFF);
                    }else{
                        tokenService.updateOldToken(newtk, TypeUtil.Token.STAFF);
                    }
                    map.put("success", true);
                    map.put("message", "员工登录成功！");
                    map.put("token",stafftk);
                }else {
                    map.put("success", false);
                    map.put("message", "员工密码错误！");
                }
            } else {
                map.put("success", false);
                map.put("message", "员工不存在！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","员工登录失败！");
        }
        return map;
    }

    //员工修改密码功能
    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public Map<String,Object> updatePassword(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String newpasswords = rawmap.get("newpasswords");
        String renewpasswords = rawmap.get("renewpasswords");
        String passwords = rawmap.get("passwords");

        try{
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                if(renewpasswords.equals(newpasswords)) {
                    staff exist = staffService.getStaffByID(tokenentity.getId());
                    String inpwd = securityService.SHA1(passwords+exist.getSalt());
                    if(inpwd.equals(exist.getPasswords())) {
                        staffService.updatePassword(tokenentity.getId(),newpasswords+exist.getSalt());
                        map.put("success", true);
                        map.put("message", "修改密码成功！");
                    }else{
                        map.put("success", false);
                        map.put("message", "员工密码错误！");
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

    //员工修改信息功能
    @RequestMapping(value = "/updatestaff",method = RequestMethod.POST)
    public Map<String,Object> updateStaff(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String realname = rawmap.get("realname");
        String positionpost = rawmap.get("positionpost");
        String email = rawmap.get("email");
        String idnumber = rawmap.get("idnumber");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff conflict = staffService.getStaffByIdnumber(idnumber);
                if(conflict != null){
                    map.put("success", false);
                    map.put("message", "该身份证号已被使用！");
                }else {
                    conflict = staffService.getStaffByEmail(email);
                    if (conflict != null){
                        map.put("success", false);
                        map.put("message", "该邮箱已被使用！");
                    }else {
                        staffService.updateOldStaff(new staff(tokenentity.getId(), realname, Integer.parseInt(positionpost), email, "", "",idnumber));
                        map.put("success", true);
                        map.put("message", "员工信息已更新！");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改员工信息失败！");
        }
        return map;
    }

    //工作人员添加设备报修请求功能
    @RequestMapping(value = "/addrepairrecord", method = RequestMethod.POST)
    public Map<String, Object> addRepairrecord(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String devicename = rawmap.get("devicename");
        String devicepicture = rawmap.get("devicepicture");
        String deviceinfo = rawmap.get("deviceinfo");
        String location = rawmap.get("location");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff stf = staffService.getStaffByID(tokenentity.getId());
                if (!stf.getPositionpost().equals(TypeUtil.Staff.REPAIRSTAFF)) {
                    map.put("success", false);
                    map.put("message", "您无权提交报修申请！");
                } else {
                    repairrecordService.addNewRepairrecord(new repairrecord(0,devicename,devicepicture,deviceinfo,location,TypeUtil.Approve.UNSOLVED));
                    map.put("success", true);
                    map.put("message", "报修请求提交成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "报修请求提交失败！");
        }
        return map;
    }

    //工作人员审核设备报修请求功能
    @RequestMapping(value = "/examinerepairrecord", method = RequestMethod.POST)
    public Map<String, Object> examineRepairrecord(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String recordid = rawmap.get("recordid");
        String approved = rawmap.get("approved");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff stf = staffService.getStaffByID(tokenentity.getId());
                if (!stf.getPositionpost().equals(TypeUtil.Staff.ADMINISTRATOR)) {
                    map.put("success", false);
                    map.put("message", "您无权处理报修申请！");
                } else {
                    repairrecordService.examineRepairrecord(Integer.parseInt(recordid), Integer.parseInt(approved));
                    map.put("success", true);
                    map.put("message", "报修请求审核成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "报修请求处理失败！");
        }
        return map;
    }

    //工作人员删除设备报修请求功能
    @RequestMapping(value = "/removerepairrecord",method = RequestMethod.POST)
    public Map<String,Object> removeRepairrecord(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String recordid = rawmap.get("recordid");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff stf = staffService.getStaffByID(tokenentity.getId());
                if (stf.getPositionpost().equals(TypeUtil.Staff.ADMINISTRATOR) || stf.getPositionpost().equals(TypeUtil.Staff.REPAIRSTAFF)) {
                    repairrecordService.removeOldRepairrecord(Integer.parseInt(recordid));
                    map.put("success", true);
                    map.put("message", "报修请求删除成功！");
                } else {
                    map.put("success", false);
                    map.put("message", "您无权删除报修申请！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "报修请求删除失败！");
        }
        return map;
    }

    //工作人员审核商户入驻请求功能
    @RequestMapping(value = "/examinemerchantrequest", method = RequestMethod.POST)
    public Map<String, Object> examineMerchantrequest(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String requestid = rawmap.get("requestid");
        String approved = rawmap.get("approved");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff stf = staffService.getStaffByID(tokenentity.getId());
                if (!stf.getPositionpost().equals(TypeUtil.Staff.ADMINISTRATOR)) {
                    if (Integer.parseInt(approved) == TypeUtil.Approve.ACCESS) {
                        merchantrequest mr = merchantrequestService.getMerchantrequestByID(Integer.parseInt(requestid));
                        merchantService.logupNewMerchant(new merchant(0,mr.getRealname(),mr.getPasswords(),mr.getSalt(),mr.getShopname(),mr.getEmail(), mr.getIdnumber()));
                    }
                    merchantrequestService.removeOldMerchantrequest(Integer.parseInt(requestid));
                    map.put("success", true);
                    map.put("message", "商户入驻请求审核成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "商户入驻请求处理失败！");
        }
        return map;
    }

    //工作人员添加行李信息功能

    //工作人员更新行李信息功能

    //工作人员删除行李信息功能
}
