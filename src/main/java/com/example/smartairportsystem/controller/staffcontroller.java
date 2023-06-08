package com.example.smartairportsystem.controller;

import com.example.smartairportsystem.entity.*;
import com.example.smartairportsystem.service.*;
import com.example.smartairportsystem.service.impl.*;
import com.example.smartairportsystem.utils.EmailUtil;
import com.example.smartairportsystem.utils.TimeFormatUtil;
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
    private luggageservice luggageService = new luggageserviceimpl();
    @Resource
    private purchaserecordservice purchaserecordService = new purchaserecordserviceimpl();
    @Resource
    private repairrecordservice repairrecordService = new repairrecordserviceimpl();
    @Resource
    private merchantservice merchantService = new merchantserviceimpl();
    @Resource
    private merchantrequestservice merchantrequestService = new merchantrequestserviceimpl();
    @Resource
    private parkingspaceservice parkingspaceService = new parkingspaceserviceimpl();
    @Resource
    private commodityorderservice commodityorderService = new commodityorderserviceimpl();
    @Resource
    private parkingorderservice parkingorderService = new parkingorderserviceimpl();
    @Resource
    private ticketservice ticketService = new ticketserviceimpl();
    @Resource
    private commodityservice commodityService = new commodityserviceimpl();

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
                staff exist = staffService.getStaffByIdnumber(idnumber,0);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "该身份证号已被注册！");
                } else {
                    if(EmailUtil.isCorrect(email)) {
                        exist = staffService.getStaffByEmail(email, 0);
                        if (exist != null) {
                            map.put("success", false);
                            map.put("message", "该邮箱已被注册！");
                        } else {
                            staffService.logupNewStaff(new staff(0, realname, Integer.parseInt(positionpost), email, passwords + salt, salt, idnumber));
                            EmailUtil.sendInformationEmail(email,"尊敬的用户：您好！\n\t您的当前邮箱"+email+"已成功注册为"+TypeUtil.AirportLocation+"智慧机场系统机厂员工！");
                            map.put("success", true);
                            map.put("message", "员工注册成功！");
                        }
                    }else {
                        map.put("success", false);
                        map.put("message", "邮箱格式有误！");
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
        map.put("positionpost","null");

        //表单取参
        String email = rawmap.get("email");
        String passwords = rawmap.get("passwords");

        try{
            staff exist = staffService.getStaffByEmail(email,0);
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
                    map.put("positionpost",exist.getPositionpost());
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
                if(EmailUtil.isCorrect(email)) {
                    staff conflict = staffService.getStaffByIdnumber(idnumber, tokenentity.getId());
                    if (conflict != null) {
                        map.put("success", false);
                        map.put("message", "该身份证号已被使用！");
                    } else {
                        conflict = staffService.getStaffByEmail(email, tokenentity.getId());
                        if (conflict != null) {
                            map.put("success", false);
                            map.put("message", "该邮箱已被使用！");
                        } else {
                            staffService.updateOldStaff(new staff(tokenentity.getId(), realname, Integer.parseInt(positionpost), email, "", "", idnumber));
                            map.put("success", true);
                            map.put("message", "员工信息已更新！");
                        }
                    }
                }else{
                    map.put("success", false);
                    map.put("message", "邮箱格式有误！");
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

    //工作人员列出设备报修请求功能
    @RequestMapping(value = "/listrepairrecord",method = RequestMethod.POST)
    public Map<String,Object> listRepairrecord(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                List<repairrecord> rtlist = repairrecordService.listAllRecord();
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

    //工作人员列出商户入驻请求功能
    @RequestMapping(value = "/listmerchantrequest",method = RequestMethod.POST)
    public Map<String,Object> listMerchantrequest(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                List<merchantrequest> rtlist = merchantrequestService.listAllRequest();
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
                merchantrequest mr = merchantrequestService.getMerchantrequestByID(Integer.parseInt(requestid));
                if (stf.getPositionpost().equals(TypeUtil.Staff.ADMINISTRATOR)) {
                    if (Integer.parseInt(approved) == TypeUtil.Approve.ACCESS) {
                        merchantService.logupNewMerchant(new merchant(0,mr.getRealname(),mr.getPasswords(),mr.getSalt(),mr.getShopname(),mr.getEmail(), mr.getIdnumber()));
                        EmailUtil.sendInformationEmail(mr.getEmail(),"尊敬的用户：您好！\n\t您的当前邮箱"+mr.getEmail()+"已通过审核，成功注册为"+TypeUtil.AirportLocation+"智慧机场系统商户！");
                    }else{
                        EmailUtil.sendInformationEmail(mr.getEmail(),"尊敬的用户：您好！\n\t您的当前邮箱"+mr.getEmail()+"所提交的商户入驻申请未通过管理员审核！");
                    }
                    merchantrequestService.removeOldMerchantrequest(Integer.parseInt(requestid));
                    map.put("success", true);
                    map.put("message", "商户入驻请求审核成功！");
                }else {
                    map.put("success", false);
                    map.put("message", "您无权处理该入驻请求！");
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
    @RequestMapping(value = "/addluggage", method = RequestMethod.POST)
    public Map<String, Object> addLuggage(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();
        map.put("luggageid","null");

        //表单取参
        String stafftk = rawmap.get("token");
        String orderid = rawmap.get("orderid");
        String state = rawmap.get("state");
        String location = rawmap.get("location");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff stf = staffService.getStaffByID(tokenentity.getId());
                if (!stf.getPositionpost().equals(TypeUtil.Staff.COMMONSTAFF)) {
                    map.put("success", false);
                    map.put("message", "您无权添加行李信息！");
                } else {
                    purchaserecord pr = purchaserecordService.getRecordByID(Integer.parseInt(orderid));
                    luggageService.addNewLuggage(new luggage(0,pr.getPersonid(),pr.getTicketid(),state,location));
                    map.put("success", true);
                    map.put("message", "添加行李信息成功！");
                    map.put("luggageid",luggageService.getLuggageByCombine(pr.getPersonid(),pr.getTicketid()).getLuggageid());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "添加行李信息失败！");
        }
        return map;
    }

    //工作人员更新行李信息功能
    @RequestMapping(value = "/updateluggage", method = RequestMethod.POST)
    public Map<String, Object> updateLuggage(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String luggageid = rawmap.get("luggageid");
        String state = rawmap.get("state");
        String location = rawmap.get("location");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff stf = staffService.getStaffByID(tokenentity.getId());
                if (!stf.getPositionpost().equals(TypeUtil.Staff.COMMONSTAFF)) {
                    map.put("success", false);
                    map.put("message", "您无权更新行李信息！");
                } else {
                    luggageService.updateOldLuggage(Integer.parseInt(luggageid),state,location);
                    map.put("success", true);
                    map.put("message", "更新行李信息成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "更新行李信息失败！");
        }
        return map;
    }

    //工作人员删除行李信息功能
    @RequestMapping(value = "/removeluggage", method = RequestMethod.POST)
    public Map<String, Object> removeLuggage(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String luggageid = rawmap.get("luggageid");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff stf = staffService.getStaffByID(tokenentity.getId());
                if (!stf.getPositionpost().equals(TypeUtil.Staff.COMMONSTAFF)) {
                    map.put("success", false);
                    map.put("message", "您无权删除行李信息！");
                } else {
                    luggageService.removeOldLuggage(Integer.parseInt(luggageid));
                    map.put("success", true);
                    map.put("message", "删除行李信息成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "删除行李信息失败！");
        }
        return map;
    }

    //工作人员列出车位信息功能
    @RequestMapping(value = "/listparkingspce", method = RequestMethod.POST)
    public Map<String, Object> listParkingspce(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                List<parkingspace> rtlist = parkingspaceService.listAllParkingspace();
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

    //工作人员添加车位信息功能
    @RequestMapping(value = "/addparkingspce", method = RequestMethod.POST)
    public Map<String, Object> addParkingspce(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String price = rawmap.get("price");
        String location = rawmap.get("location");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff stf = staffService.getStaffByID(tokenentity.getId());
                if (!stf.getPositionpost().equals(TypeUtil.Staff.ADMINISTRATOR)) {
                    map.put("success", false);
                    map.put("message", "您无权添加车位信息！");
                } else {
                    parkingspace ps = parkingspaceService.getParkingspaceByLocation(location,0);
                    if(ps != null){
                        map.put("success", false);
                        map.put("message", "相同车位已存在！");
                    }else {
                        parkingspaceService.addNewParkingspace(new parkingspace(0, location,Double.parseDouble(price)));
                        map.put("success", true);
                        map.put("message", "添加车位信息成功！");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "添加车位信息失败！");
        }
        return map;
    }

    //工作人员修改车位信息功能
    @RequestMapping(value = "/updateparkingspce", method = RequestMethod.POST)
    public Map<String, Object> updateParkingspce(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String parkingspaceid = rawmap.get("parkingspaceid");
        String price = rawmap.get("price");
        String location = rawmap.get("location");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff stf = staffService.getStaffByID(tokenentity.getId());
                if (!stf.getPositionpost().equals(TypeUtil.Staff.ADMINISTRATOR)) {
                    map.put("success", false);
                    map.put("message", "您无权修改车位信息！");
                } else {
                    parkingspace ps = parkingspaceService.getParkingspaceByLocation(location,Integer.parseInt(parkingspaceid));
                    if(ps != null){
                        map.put("success", false);
                        map.put("message", "相同车位已存在！");
                    }else {
                        parkingspaceService.updateOldParkingspace(new parkingspace(Integer.parseInt(parkingspaceid), location,Double.parseDouble(price)));
                        map.put("success", true);
                        map.put("message", "修改车位信息成功！");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改车位信息失败！");
        }
        return map;
    }

    //工作人员删除车位信息功能
    @RequestMapping(value = "/removeparkingspce", method = RequestMethod.POST)
    public Map<String, Object> removeParkingspce(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String parkingspaceid = rawmap.get("parkingspaceid");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                staff stf = staffService.getStaffByID(tokenentity.getId());
                if (!stf.getPositionpost().equals(TypeUtil.Staff.ADMINISTRATOR)) {
                    map.put("success", false);
                    map.put("message", "您无权删除车位信息！");
                } else {
                    parkingspaceService.removeOldParkingspace(Integer.parseInt(parkingspaceid));
                    map.put("success", true);
                    map.put("message", "删除车位信息成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "删除车位信息失败！");
        }
        return map;
    }

    //员工查询报表功能
    @RequestMapping(value = "/reportforms",method = RequestMethod.POST)
    public Map<String,Object> reportForms(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();
        map.put("ticket",0);
        map.put("parking",0);
        map.put("commodity",0);

        //表单取参
        String stafftk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                double ticketsum = 0;
                double parkingsum = 0;
                double commoditysum = 0;

                List<purchaserecord> ticketlist = purchaserecordService.listAllRecord();
                List<commodityorder> commoditylist = commodityorderService.listAllOrder();
                List<parkingorder> parkinglist = parkingorderService.listAllOrder();

                int days = TimeFormatUtil.getDays(TimeFormatUtil.getCurrentTime());

                for (purchaserecord pr:ticketlist){
                    ticket tkt = ticketService.getTicketByID(pr.getTicketid());
                    int tdays = TimeFormatUtil.getDays(pr.getPurchasetime());
                    int sub = days - tdays;
                    if(sub >= 0 && sub <= 15)ticketsum = ticketsum + tkt.getPrice();
                }
                for (commodityorder co:commoditylist){
                    commoditylist cl = commodityService.getCommodityByID(co.getCommodityid());
                    int tdays = TimeFormatUtil.getDays(co.getArrivetime());
                    int sub = days - tdays;
                    if(sub >= 0 && sub <= 15)commoditysum = commoditysum + co.getCounts()* cl.getPrice();
                }
                for (parkingorder po:parkinglist){
                    parkingspace ps = parkingspaceService.getParkingspaceByID(po.getParkingspaceid());

                    int st = TimeFormatUtil.getMinutes(po.getStarttime());
                    int en = TimeFormatUtil.getMinutes(po.getEndtime());

                    int zhour = (en-st)/60;
                    if((en-st)%60 != 0){
                        zhour = zhour + 1;
                    }

                    int tdays = TimeFormatUtil.getDays(po.getStarttime());
                    int sub = days - tdays;

                    if(sub >= 0 && sub <= 15)parkingsum = parkingsum + ps.getPrice()*zhour;
                }
                tokenService.logoutOldToken(stafftk,TypeUtil.Token.STAFF);
                map.put("success", true);
                map.put("message", "查询报表成功！");
                map.put("ticket",ticketsum);
                map.put("parking",parkingsum);
                map.put("commodity",commoditysum);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "查询报表失败！");
        }
        return map;
    }

    //员工注销登录功能
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public Map<String,Object> logoutMerchat(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TypeUtil.Token.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "员工未登录或已注销登录！");
            }else {
                tokenService.logoutOldToken(stafftk,TypeUtil.Token.STAFF);
                map.put("success", true);
                map.put("message", "员工注销登录成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "员工注销登录失败！");
        }
        return map;
    }
}
