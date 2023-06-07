package com.example.smartairportsystem.utils;

import com.example.smartairportsystem.entity.airlinecompany;
import com.example.smartairportsystem.entity.bowl.schedule;
import com.example.smartairportsystem.entity.flight;
import com.example.smartairportsystem.entity.person;
import com.example.smartairportsystem.entity.purchaserecord;
import com.example.smartairportsystem.service.*;
import com.example.smartairportsystem.service.impl.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private companyservice companyService = new companyserviceimpl();

    public static List<schedule> FlightSchedule = new ArrayList<>();

    @Scheduled(fixedRate = 60000)
    public void informTourist(){
        String nowtime = TimeFormatUtil.getCurrentTime();
        String today = nowtime.split(" ")[0];
        List<purchaserecord> list = purchaserecordService.listTodayRecord(today+"%");
        for(purchaserecord i: list){
            person p = personService.getPersonByID(i.getPersonid());
            flight f = flightService.getFlightByID(ticketService.getTicketByID(i.getTicketid()).getFlightid());
            int sub = TimeFormatUtil.getMinutes(f.getDeparturetime())-TimeFormatUtil.getMinutes(nowtime);

            if(sub == 30) {
                EmailUtil.sendInformationEmail(p.getEmail(), "尊敬的用户：您好！\n\t您的从"+f.getTakeofflocation()+"飞往"+f.getLandinglocation()+"的"+f.getName()+"航班将在30分钟内起飞，请您迅速前往"+f.getTerminal()+"号航站楼的"+f.getDeparturegate()+"号登机口登机！");
            }else if(sub == 60){
                EmailUtil.sendInformationEmail(p.getEmail(), "尊敬的用户：您好！\n\t您的从"+f.getTakeofflocation()+"飞往"+f.getLandinglocation()+"的"+f.getName()+"航班将在1小时内起飞，请您尽快办理值机手续，通过安检并前往"+f.getTerminal()+"号航站楼的"+f.getDeparturegate()+"号登机口准备登机！");
            }
        }
    }

    @Scheduled(fixedRate = 60000)
    public void updateSchedule(){
        FlightSchedule.clear();
        String nowtime = TimeFormatUtil.getCurrentTime();
        String today = nowtime.split(" ")[0];
        List<flight> list = flightService.listTodayFlight(today+"%");
        for(flight f:list){
            int departuretime = TimeFormatUtil.getMinutes(f.getDeparturetime());
            int landingtime = TimeFormatUtil.getMinutes(f.getLandingtime());
            int time = TimeFormatUtil.getMinutes(nowtime);

            String status = "";

            if (departuretime - time <= 120 && time <= landingtime){
                if (time < departuretime){
                    status = TypeUtil.Schedule.CHECK;
                    if(departuretime - time <= 30){
                        status = TypeUtil.Schedule.ABOARD;
                        if (departuretime - time <= 10){
                            status = TypeUtil.Schedule.STOP;
                        }
                    }
                }else{
                    status = TypeUtil.Schedule.FLY;
                    if(time - departuretime <= 10){
                        status = TypeUtil.Schedule.TAKEOFF;
                    }else if(landingtime - time <= 10){
                        status = TypeUtil.Schedule.LANDING;
                    }
                }

                airlinecompany cp = companyService.getCompanyByID(f.getCompanyid());
                schedule sc = new schedule(cp.getName(),f.getName(),f.getTakeofflocation(),f.getLandinglocation(),f.getDeparturetime(),f.getLandingtime(),f.getDeparturegate(),f.getTerminal(),status);
                FlightSchedule.add(sc);
            }
        }
    }
}
