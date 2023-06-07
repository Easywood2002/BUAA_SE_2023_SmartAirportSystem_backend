package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.parkingorder;
import com.example.smartairportsystem.entity.parkingspace;
import com.example.smartairportsystem.mapper.parkingordermapper;
import com.example.smartairportsystem.mapper.parkingspacemapper;
import com.example.smartairportsystem.service.parkingspaceservice;
import com.example.smartairportsystem.utils.TimeFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("parkingspaceservice")
public class parkingspaceserviceimpl implements parkingspaceservice {
    @Autowired
    private parkingspacemapper parkingspaceMapper;
    @Autowired
    private parkingordermapper parkingorderMapper;

    public List<parkingspace> listEmptyParkingspace(String starttime,String endtime){
        int st = TimeFormatUtil.getMinutes(starttime);
        int en = TimeFormatUtil.getMinutes(endtime);
        List<parkingspace> pslist = parkingspaceMapper.listAllParkingspace();
        List<parkingspace> rtlist = new ArrayList<>();
        for (parkingspace ps:pslist){
            List<parkingorder> polist = parkingorderMapper.listOrderBySpaceid(ps.getParkingspaceid());
            boolean isend = true;
            for(parkingorder po:polist){
                int post = TimeFormatUtil.getMinutes(po.getStarttime());
                int poen = TimeFormatUtil.getMinutes(po.getEndtime());
                if((st < post && en > post) || (st >= post && st < poen)){
                    isend = false;
                    break;
                }
            }
            if (isend) {
                rtlist.add(ps);
            }
        }
        return rtlist;
    }
    public List<parkingspace> listAllParkingspace(){return parkingspaceMapper.listAllParkingspace();}
    public void addNewParkingspace(parkingspace newps){parkingspaceMapper.addNewParkingspace(newps);}
    public void updateOldParkingspace(parkingspace newps){parkingspaceMapper.updateOldParkingspace(newps);}
    public void removeOldParkingspace(Integer parkingspaceid){parkingspaceMapper.removeOldParkingspace(parkingspaceid);}
    public parkingspace getParkingspaceByID(Integer parkingspaceid){return parkingspaceMapper.getParkingspaceByID(parkingspaceid);}
    public parkingspace getParkingspaceByLocation(String location,Integer exceptid){return parkingspaceMapper.getParkingspaceByLocation(location,exceptid);}
}
