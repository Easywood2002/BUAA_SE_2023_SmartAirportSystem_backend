package com.example.smartairportsystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/merchant")
public class merchantcontroller {
    @Resource
    private merchantservice merchantService = new merchantserviceimpl();

    //商户注册功能
    @RequestMapping(value = "/logup",method = RequestMethod.POST)
    public Map<String,Object> logupMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String realname = rawmap.get("realname");
        String passwords = rawmap.get("passwords");
        String repasswords = rawmap.get("repasswords");
        String shopname = rawmap.get("shopname");
        String email = rawmap.get("email");

        try{
            if(repasswords.equals(passwords)) {
                //对商户设置的密码加盐加密后保存
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt()+"";
                merchant exist = merchantService.getMerchantByEmail(email);
                // if (exist_name != null) {
                //     map.put("success", false);
                //     map.put("message", "该真实姓名已被注册！");
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "邮箱已被注册！");
                } else {
                    merchantService.logupNewMerchant(new merchant(0,realname,passwords+salt,salt,shopname,email));
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

    //商户登录功能
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> loginMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();
        map.put("token", "null");

        //表单取参
        String email = rawmap.get("email");
        String passwords = rawmap.get("passwords");

        try{
            merchant exist = merchantService.getMerchantByEmail(email);
            if (exist != null) {
                //取出用户盐值，与当前输入的密码拼接加密后再与数据库中的信息进行比较
                String inpwd = securityService.SHA1(passwords+exist.getSalt());
                if(inpwd.equals(exist.getPasswords())) {
                    //将用户id经md5加密后作为token一并返回前端，便于后续访问
                    String merchanttk = securityService.MD5(exist.getMerchantid().toString());
                    token newtk = new token(exist.getMerchantid(),merchanttk);
                    token existtk = tokenService.getTokenByID(newtk.getId(), TokenTypeUtil.MERCHANT);
                    if (existtk == null){
                        tokenService.loginNewToken(newtk, TokenTypeUtil.MERCHANT);
                    }else{
                        tokenService.updateOldToken(newtk, TokenTypeUtil.MERCHANT);
                    }
                    map.put("success", true);
                    map.put("message", "用户登录成功！");
                    map.put("token",merchanttk);
                }else {
                    map.put("success", false);
                    map.put("message", "用户密码错误！");
                }
            } else {
                map.put("success", false);
                map.put("message", "用户不存在！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","用户登录失败！");
        }
        return map;
    }

    //列出该商户的实名信息功能
    @RequestMapping(value = "/listmerchant",method = RequestMethod.POST)
    public Map<String,Object> listMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                List<merchant> rtlist = merchantService.listMerchantByMerchantid(tokenentity.getId());
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

    // //商户修改实名信息功能
    // @RequestMapping(value = "/updateperson",method = RequestMethod.POST)
    // public Map<String,Object> updatePerson(@RequestParam Map<String,String> rawmap){
    //     Map<String,Object> map = new HashMap<>();

    //     //表单取参
    //     String touristtk = rawmap.get("token");
    //     String merchantid = rawmap.get("merchantid");
    //     String realname = rawmap.get("realname");
    //     String shopname = rawmap.get("shopname");
    //     String email = rawmap.get("email");

    //     try {
    //         token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.MERCHANT);
    //         if(tokenentity == null){
    //             map.put("success", false);
    //             map.put("message", "用户未登录或已注销登录！");
    //         }else {
    //             merchant conflict = merchantService.getMerchantByName(realname);
    //             if(conflict != null){
    //                 map.put("success", false);
    //                 map.put("message", "已存在相同实名信息！");
    //             }else {
    //                 personService.updateOldMerchant(new merchant(Integer.parseInt(merchantid),realname,shopname,email));
    //                 map.put("success", true);
    //                 map.put("message", "实名信息已更新！");
    //             }
    //         }
    //     }catch (Exception e){
    //         e.printStackTrace();
    //         map.put("success", false);
    //         map.put("message", "修改实名信息失败！");
    //     }
    //     return map;
    // }

    // //商户删除实名信息功能
    // @RequestMapping(value = "/removemerchant",method = RequestMethod.POST)
    // public Map<String,Object> removeMerchant(@RequestParam Map<String,String> rawmap){
    //     Map<String,Object> map = new HashMap<>();

    //     //表单取参
    //     String touristtk = rawmap.get("token");
    //     String merchantid = rawmap.get("merchantid");

    //     try {
    //         token tokenentity = tokenService.getTokenByToken(touristtk,TokenTypeUtil.MERCHANT);
    //         if(tokenentity == null){
    //             map.put("success", false);
    //             map.put("message", "用户未登录或已注销登录！");
    //         }else {
    //             personService.removeOldMerchant(Integer.parseInt(merchantid));
    //             map.put("success", true);
    //             map.put("message", "实名信息已删除！");
    //         }
    //     }catch (Exception e){
    //         e.printStackTrace();
    //         map.put("success", false);
    //         map.put("message", "删除实名信息失败！");
    //     }
    //     return map;
    // }
}
