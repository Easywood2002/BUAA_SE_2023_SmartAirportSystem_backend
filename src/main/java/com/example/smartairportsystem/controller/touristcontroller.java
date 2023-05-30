package com.example.smartairportsystem.controller;

import com.example.smartairportsystem.entity.token;
import com.example.smartairportsystem.service.impl.securityserviceimpl;
import com.example.smartairportsystem.service.impl.tokenserviceimpl;
import com.example.smartairportsystem.service.impl.touristserviceimpl;
import com.example.smartairportsystem.service.securityservice;
import com.example.smartairportsystem.service.tokenservice;
import com.example.smartairportsystem.service.touristservice;
import com.example.smartairportsystem.entity.tourist;

import com.example.smartairportsystem.utils.TokenTypeUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    @RequestMapping(value = "/logup",method = RequestMethod.POST)
    public Map<String,Object> logupNewTourist(@RequestParam("nickname") String nickname, @RequestParam("realname") String realname, @RequestParam("passwords") String passwords, @RequestParam("repasswords") String repasswords, @RequestParam("vip") String vip){
        Map<String,Object> map = new HashMap<>();

        try{
            if(repasswords.equals(passwords)) {
                Random root = new Random((new Random()).nextInt());
                String salt = root.nextInt()+"";
                tourist newtourist = new tourist(nickname, realname, passwords+salt,salt, vip);
                tourist exist = touristService.getTouristByNickname(nickname);
                if (exist != null) {
                    map.put("success", false);
                    map.put("message", "用户名已被注册！");
                } else {
                    touristService.logupNewTourist(newtourist);
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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> loginOldTourist(@RequestParam("nickname") String nickname, @RequestParam("passwords") String passwords){
        Map<String,Object> map = new HashMap<>();
        map.put("token", "null");

        try{
            tourist exist = touristService.getTouristByNickname(nickname);
            if (exist != null) {
                String inpwd = securityService.SHA1(passwords+exist.getSalt());
                if(inpwd.equals(exist.getPasswords())) {
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
                map.put("message", "用户名不存在！");
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","用户登录失败！");
        }
        return map;
    }
}
