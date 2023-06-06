package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.luggage;
import com.example.smartairportsystem.mapper.luggagemapper;
import com.example.smartairportsystem.service.luggageservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("luggageservice")
public class luggageserviceimpl implements luggageservice {
    @Autowired
    private luggagemapper luggageMapper;

    public void addNewLuggage(luggage newluggage){luggageMapper.addNewLuggage(newluggage);}
    public void updateOldLuggage(Integer luggageid,String state,String location){luggageMapper.updateOldLuggage(luggageid,state,location);}
    public luggage getLuggageByID(Integer luggageid){return luggageMapper.getLuggageByID(luggageid);}
    public luggage getLuggageByCombine(Integer personid,Integer ticketid){return luggageMapper.getLuggageByCombine(personid,ticketid);}
    public void removeOldLuggage(Integer luggageid){luggageMapper.removeOldLuggage(luggageid);}
}
