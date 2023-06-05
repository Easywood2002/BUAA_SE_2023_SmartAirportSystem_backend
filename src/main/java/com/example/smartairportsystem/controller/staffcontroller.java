package com.example.smartairportsystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/staff")
public class staffcontroller {
    @Resource
    private staff staffService = new staffserviceimpl();
    @Resource
    private repairrecordservice repairrecordService = new repairrecordserviceimpl();
    @Resource
    private merchantservice merchantService = new merchantserviceimpl();

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

        try{
            if(repasswords.equals(passwords)) {
                //对用户设置的密码加盐加密后保存
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt()+"";
                staff exist = staffService.getStaffByEmail(email);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "邮箱已被注册！");
                } else {
                    staffService.logupNewStaff(new staff(0,realname,positionpost,email,passwords+salt,salt));
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
                    token existtk = tokenService.getTokenByID(newtk.getId(), TokenTypeUtil.STAFF);
                    if (existtk == null){
                        tokenService.loginNewToken(newtk, TokenTypeUtil.STAFF);
                    }else{
                        tokenService.updateOldToken(newtk, TokenTypeUtil.STAFF);
                    }
                    map.put("success", true);
                    map.put("message", "用户登录成功！");
                    map.put("token",stafftk);
                }else {
                    map.put("success", false);
                    map.put("message", "用户密码错误！");
                }
            } else {
                map.put("success", false);
                map.put("message", "用户名不存在！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","用户登录失败！");
        }
        return map;
    }

    //列出该工作人员的实名信息功能
    @RequestMapping(value = "/liststaff",method = RequestMethod.POST)
    public Map<String,Object> listStaff(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TokenTypeUtil.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                staff rt = staffService.getStaffByID(tokenentity.getId());
                map.put("success", true);
                map.put("message", rt);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取信息失败！");
        }
        return map;
    }
    //工作人员删除实名信息功能
    @RequestMapping(value = "/removestaff",method = RequestMethod.POST)
    public Map<String,Object> removeStaff(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TokenTypeUtil.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                staffService.removeOldStaff(tokenentity.getId());
                map.put("success", true);
                map.put("message", "用户已注销！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "用户注销失败！");
        }
        return map;
    }

    //工作人员添加设备报修请求功能
    @RequestMapping(value = "/addrepairrecord", method = RequestMethod.POST)
    public Map<String, Object> addRepairrecord(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String deviceid = rawmap.get("deviceid");
        String devicename = rawmap.get("devicename");
        String devicepicture = rawmap.get("devicepicture");
        String deviceinfo = rawmap.get("deviceinfo");
        String location = rawmap.get("location");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TokenTypeUtil.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                repairrecord exist = repairService.getRepairrecordByDeviceid(Integer.parseInt(deviceid));
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "请勿重复提交报修请求！");
                } else {
                    repairService.addNewRepairrecord(new repairrecord(0,Integer.parseInt(deviceid),devicename,devicepicture,deviceinfo,location,"False"));
                    map.put("success", true);
                    map.put("message", "报修请求提交成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "报修请求添加失败！");
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
            token tokenentity = tokenService.getTokenByToken(stafftk,TokenTypeUtil.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                repairrecord exist = repairService.getRepairrecordByID(Integer.parseInt(recordid));
                if (exist == null) {
                    map.put("success", false);
                    map.put("message", "该报修请求不存在！");
                } else {
                    repairService.examineRepairrecord(Integer.parseInt(recordid),approved);
                    map.put("success", true);
                    map.put("message", "报修请求审核成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "报修请求审核失败！");
        }
        return map;
    }

    //工作人员撤销设备报修请求功能
    @RequestMapping(value = "/removerepairrecord",method = RequestMethod.POST)
    public Map<String,Object> removeRepairrecord(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String recordid = rawmap.get("recordid");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TokenTypeUtil.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                staffService.removeOldRepairrecord(Integer.parseInt(recordid));
                map.put("success", true);
                map.put("message", "该报修请求撤销成功！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "该报修请求撤销失败！");
        }
        return map;
    }

    //工作人员审核商户入驻请求功能
    @RequestMapping(value = "/examinemerchantrequest", method = RequestMethod.POST)
    public Map<String, Object> examineMerchantrequest(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String stafftk = rawmap.get("token");
        String email = rawmap.get("email");
        String approved = rawmap.get("approved");

        try {
            token tokenentity = tokenService.getTokenByToken(stafftk,TokenTypeUtil.STAFF);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                merchant exist = merchantService.getMerchantByEmail(email);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "该商户已入驻！");
                } else {
                    if (approved == "Approve") {
                        merchantService.logupNewMerchant(new merchant(0,exist.getRealname,exist.getID,exist.getPasswords,exist.getSalt,exist.getShopname,exist.getEmail));
                    }
                    merchantrequestService.removeOldMerchantrequest(email);
                    map.put("success", true);
                    map.put("message", "商户入驻请求审核成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "商户入驻请求审核失败！");
        }
        return map;
    }
}
