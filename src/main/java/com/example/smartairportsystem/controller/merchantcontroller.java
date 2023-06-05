package com.example.smartairportsystem.controller;

import com.example.smartairportsystem.entity.commoditylist;
import com.example.smartairportsystem.entity.merchantrequest;
import com.example.smartairportsystem.service.*;
import com.example.smartairportsystem.service.impl.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smartairportsystem.entity.merchant;
import com.example.smartairportsystem.entity.token;
import com.example.smartairportsystem.utils.TokenTypeUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping(value = "/merchant")
public class merchantcontroller {
    @Resource
    private merchantservice merchantService = new merchantserviceimpl();
    @Resource
    private commodityservice commodityService = new commodityserviceimpl();
    @Resource
    private merchantrequestservice merchantrequestService = new merchantrequestserviceimpl();
    @Resource
    private securityservice securityService = new securityserviceimpl();
    @Resource
    private tokenservice tokenService = new tokenserviceimpl();

    //商户入驻功能
    @RequestMapping(value = "/logup",method = RequestMethod.POST)
    public Map<String,Object> logupMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String realname = rawmap.get("realname");
        String passwords = rawmap.get("passwords");
        String repasswords = rawmap.get("repasswords");
        String shopname = rawmap.get("shopname");
        String email = rawmap.get("email");
        String idnumber = rawmap.get("idnumber");

        try{
            if(repasswords.equals(passwords)) {
                //对商户设置的密码加盐加密后保存
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt()+"";
                merchant exist = merchantService.getMerchantByIdnumber(idnumber);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "该身份证号已申请入驻！");
                } else {
                    exist = merchantService.getMerchantByEmail(email);
                    if(exist != null){
                        map.put("success", false);
                        map.put("message", "该邮箱已申请入驻！");
                    }else {
                        merchantrequestService.addNewMerchantrequest(new merchantrequest(0, realname, passwords + salt, salt, shopname, email, idnumber));
                        map.put("success", true);
                        map.put("message", "商户入驻申请提交成功！");
                    }
                }
            }else{
                map.put("success", false);
                map.put("message", "确认密码不一致！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","商户入驻申请提交失败！");
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
                    map.put("message", "商户登录成功！");
                    map.put("token",merchanttk);
                }else {
                    map.put("success", false);
                    map.put("message", "商户密码错误！");
                }
            } else {
                map.put("success", false);
                map.put("message", "商户不存在！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","商户登录失败！");
        }
        return map;
    }

    //商户修改密码功能
    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public Map<String,Object> updatePassword(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");
        String newpasswords = rawmap.get("newpasswords");
        String renewpasswords = rawmap.get("renewpasswords");
        String passwords = rawmap.get("passwords");

        try{
            token tokenentity = tokenService.getTokenByToken(merchanttk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "商户未登录或已注销登录！");
            }else {
                if(renewpasswords.equals(newpasswords)) {
                    merchant exist = merchantService.getMerchantByID(tokenentity.getId());
                    String inpwd = securityService.SHA1(passwords+exist.getSalt());
                    if(inpwd.equals(exist.getPasswords())) {
                        merchantService.updatePassword(tokenentity.getId(),newpasswords+exist.getSalt());
                        map.put("success", true);
                        map.put("message", "修改密码成功！");
                    }else{
                        map.put("success", false);
                        map.put("message", "商户密码错误！");
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

    //商户修改信息功能
    @RequestMapping(value = "/updatemerchant",method = RequestMethod.POST)
    public Map<String,Object> updateMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");
        String realname = rawmap.get("realname");
        String shopname = rawmap.get("shopname");
        String email = rawmap.get("email");
        String idnumber = rawmap.get("idnumber");

        try {
            token tokenentity = tokenService.getTokenByToken(merchanttk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                merchant conflict = merchantService.getMerchantByIdnumber(idnumber);
                if(conflict != null){
                    map.put("success", false);
                    map.put("message", "该身份证号已被使用！");
                }else {
                    conflict = merchantService.getMerchantByEmail(email);
                    if (conflict != null){
                        map.put("success", false);
                        map.put("message", "该邮箱已被使用！");
                    }else {
                        merchantService.updateOldMerchant(new merchant(tokenentity.getId(), realname, "", "", shopname, email,idnumber));
                        map.put("success", true);
                        map.put("message", "商户信息已更新！");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改商户信息失败！");
        }
        return map;
    }

    /*//商户注销账户功能
    @RequestMapping(value = "/removemerchant",method = RequestMethod.POST)
    public Map<String,Object> removeMerchant(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(merchanttk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                merchantService.removeOldMerchant(tokenentity.getId());
                map.put("success", true);
                map.put("message", "商户信息已注销！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "商户信息注销失败！");
        }
        return map;
    }*/

    /*//商户提交入驻请求
    @RequestMapping(value = "/addmerchantrequest", method = RequestMethod.POST)
    public Map<String, Object> addMerchantrequest(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String realname = rawmap.get("realname");
        String nickname = rawmap.get("nickname");
        String passwords = rawmap.get("passwords");
        String repasswords = rawmap.get("repasswords");
        String email = rawmap.get("email");

        try{
            if(repasswords.equals(passwords)) {
                //对用户设置的密码加盐加密后保存
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt()+"";
                merchant exist = merchantService.getMerchantByEmail(email);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "请勿重复提交请求！");
                } else {
                    merchantrequestService.addNewMerchantrequest(new merchantrequest(0,realname,nickname,passwords+salt,salt,email));
                    map.put("success", true);
                    map.put("message", "商户入驻请求提交成功！");
                }
            }else{
                map.put("success", false);
                map.put("message", "确认密码不一致！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","商户入驻请求提交失败！");
        }

        return map;
    }*/

    //商户添加商品信息功能
    @RequestMapping(value = "/addcommodity", method = RequestMethod.POST)
    public Map<String, Object> addCommodity(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");
        String name = rawmap.get("name");
        String counts = rawmap.get("counts");
        String price = rawmap.get("price");

        try {
            token tokenentity = tokenService.getTokenByToken(merchanttk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "商户未登录或已注销登录！");
            }else {
                commoditylist exist = commodityService.getCommodityByCombine(tokenentity.getId(),name);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "本店商品信息已存在！");
                } else {
                    commodityService.addNewCommodity(new commoditylist(0,name,tokenentity.getId(), Integer.parseInt(counts), Double.parseDouble(price)));
                    map.put("success", true);
                    map.put("message", "添加商品信息成功！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "添加商品信息失败！");
        }
        return map;
    }

    //商户修改商品信息功能
    @RequestMapping(value = "/updatecommodity", method = RequestMethod.POST)
    public Map<String, Object> updateCommodity(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");
        String commodityid = rawmap.get("commodityid");
        String name = rawmap.get("name");
        String counts = rawmap.get("counts");
        String price = rawmap.get("price");

        try {
            token tokenentity = tokenService.getTokenByToken(merchanttk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "商户未登录或已注销登录！");
            }else {
                commoditylist conflict = commodityService.getCommodityByCombine(tokenentity.getId(),name);
                if(conflict != null){
                    map.put("success", false);
                    map.put("message", "已存在相同商品信息！");
                }else{
                    commodityService.updateOldCommodity(new commoditylist(Integer.parseInt(commodityid), name, tokenentity.getId(), Integer.parseInt(counts), Double.parseDouble(price)));
                    map.put("success", true);
                    map.put("message", "商品信息已更新！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "修改商品信息失败！");
        }
        return map;
    }

    //商户删除商品信息功能
    @RequestMapping(value = "/removecommodity", method = RequestMethod.POST)
    public Map<String, Object> removeCommodity(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");
        String commodityid = rawmap.get("commodityid");

        try {
            token tokenentity = tokenService.getTokenByToken(merchanttk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                commodityService.removeOldCommodity(Integer.parseInt(commodityid));
                map.put("success", true);
                map.put("message", "商户信息已删除！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "删除商户信息失败！");
        }
        return map;
    }

    //商户查询自身的商品清单
    @RequestMapping(value = "/listcommodity",method = RequestMethod.POST)
    public Map<String,Object> listCommodity(@RequestParam Map<String,String> rawmap){
        Map<String, Object> map = new HashMap<>();

        //表单取参
        String merchanttk = rawmap.get("token");

        try {
            token tokenentity = tokenService.getTokenByToken(merchanttk,TokenTypeUtil.MERCHANT);
            if(tokenentity == null){
                map.put("success", false);
                map.put("message", "用户未登录或已注销登录！");
            }else {
                List<commoditylist> rtlist = commodityService.listCommodityByMerchantid(tokenentity.getId());
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
