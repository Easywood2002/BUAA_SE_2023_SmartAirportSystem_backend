package com.example.smartairportsystem.utils;

import com.example.smartairportsystem.entity.flight;
import com.example.smartairportsystem.entity.person;
import com.example.smartairportsystem.entity.purchaserecord;
import com.example.smartairportsystem.service.flightservice;
import com.example.smartairportsystem.service.impl.flightserviceimpl;
import com.example.smartairportsystem.service.impl.personserviceimpl;
import com.example.smartairportsystem.service.impl.purchaserecordserviceimpl;
import com.example.smartairportsystem.service.impl.ticketserviceimpl;
import com.example.smartairportsystem.service.personservice;
import com.example.smartairportsystem.service.purchaserecordservice;
import com.example.smartairportsystem.service.ticketservice;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@EnableScheduling
@Component
public class Inform {
    @Resource
    private purchaserecordservice purchaserecordService = new purchaserecordserviceimpl();
    @Resource
    private personservice personService = new personserviceimpl();
    @Resource
    private ticketservice ticketService = new ticketserviceimpl();
    @Resource
    private flightservice flightService = new flightserviceimpl();

    @Scheduled(fixedRate = 60000)
    public void informTourist(){
        String nowtime = TimeFormatUtil.getCurrentTime();
        String today = nowtime.split(" ")[0];
        List<purchaserecord> list = purchaserecordService.listTodayRecord(today+"%");
        for(purchaserecord i: list){
            person p = personService.getPersonByID(i.getPersonid());
            flight f = flightService.getFlightByID(ticketService.getTicketByID(i.getTicketid()).getFlightid());
            String[] time = f.getDeparturetime().split(" ")[1].split(":");
            int hour = Integer.parseInt(time[0]);
            int min = Integer.parseInt(time[1]);
            String[] now = nowtime.split(" ")[1].split(":");
            int nowhour = Integer.parseInt(now[0]);
            int nowmin = Integer.parseInt(now[1]);
            int sub = (hour*60+min)-(nowhour*60+nowmin);
            if(sub == 30) {
                EmailUtil.sendInformationEmail(p.getEmail(), "尊敬的用户：您好！\n\t您的从"+f.getTakeofflocation()+"飞往"+f.getLandinglocation()+"的"+f.getName()+"航班将在30分钟内起飞，请您迅速前往"+f.getTerminal()+"号航站楼的"+f.getDeparturegate()+"号登机口登机！");
            }else if(sub == 60){
                EmailUtil.sendInformationEmail(p.getEmail(), "尊敬的用户：您好！\n\t您的从"+f.getTakeofflocation()+"飞往"+f.getLandinglocation()+"的"+f.getName()+"航班将在1小时内起飞，请您尽快办理值机手续，通过安检并前往"+f.getTerminal()+"号航站楼的"+f.getDeparturegate()+"号登机口准备登机！");
            }
        }
    }
}
