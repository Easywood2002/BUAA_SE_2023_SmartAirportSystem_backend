package com.example.smartairportsystem.controller;

import com.example.smartairportsystem.service.impl.touristserviceimpl;
import com.example.smartairportsystem.service.touristservice;
import com.example.smartairportsystem.entity.tourist;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/tourist")
public class touristcontroller {
    @Resource
    private touristservice touristService = new touristserviceimpl();

    @RequestMapping(value = "/logup",method = RequestMethod.POST)
    public Map<String,Object> logupNewTourist(@RequestParam("nickname") String nickname, @RequestParam("realname") String realname, @RequestParam("passwords") String passwords, @RequestParam("vip") Boolean vip){
        Map<String,Object> map = new HashMap<>();

        try{
            tourist newtourist = new tourist(nickname,vip,realname,passwords);
            touristService.logupNewTourist(newtourist);
            map.put("success", true);
            map.put("message", "用户注册成功！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("success",false);
            map.put("message","注册失败！");
        }
        return map;
    }
}
