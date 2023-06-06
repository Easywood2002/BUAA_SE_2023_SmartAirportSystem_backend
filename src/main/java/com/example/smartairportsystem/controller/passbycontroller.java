package com.example.smartairportsystem.controller;

import com.example.smartairportsystem.entity.flight;
import com.example.smartairportsystem.service.flightservice;
import com.example.smartairportsystem.service.impl.flightserviceimpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/passby")
public class passbycontroller {
    @Resource
    private flightservice flightService = new flightserviceimpl();

    //游客查询航班信息功能
    @RequestMapping(value = "/searchflight",method = RequestMethod.POST)
    public Map<String,Object> searchFlight(@RequestParam Map<String,String> rawmap){
        Map<String,Object> map = new HashMap<>();

        //表单取参
        String takeofflocation = rawmap.get("takeofflocation");
        String landinglocation = rawmap.get("landinglocation");
        String date = rawmap.get("date");

        try {
            List<flight> rtlist = flightService.listFlightByCombine(takeofflocation,landinglocation,date+"%");
            map.put("success", true);
            map.put("message", rtlist);
        }catch (Exception e){
            e.printStackTrace();
            map.put("success", false);
            map.put("message", "获取列表失败！");
        }
        return map;
    }
}
