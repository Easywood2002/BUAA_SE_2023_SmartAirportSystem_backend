package com.example.smartairportsystem.service.impl;

import com.example.smartairportsystem.entity.bowl.eticket;
import com.example.smartairportsystem.entity.ticket;
import com.example.smartairportsystem.mapper.ticketmapper;
import com.example.smartairportsystem.service.ticketservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ticketservice")
public class ticketserviceimpl implements ticketservice {
    @Autowired
    private ticketmapper ticketMapper;

    public ticket getTicketByID(Integer ticketid){return ticketMapper.getTicketByID(ticketid);}
    public ticket getTicketByCombine(Integer flightid,String tickettype){return ticketMapper.getTicketByCombine(flightid,tickettype);}
    public void addNewTicket(ticket newticket){ticketMapper.addNewTicket(newticket);}
    public void updateOldTicket(ticket newticket){ticketMapper.updateOldTicket(newticket);}
    public void removeOldTicket(Integer ticketid){ticketMapper.removeOldTicket(ticketid);}
    public List<ticket> listTicketByFlightid(Integer flightid){return ticketMapper.listTicketByFlightid(flightid);}
    public List<eticket> listEticketByTouristid(Integer touristid){return ticketMapper.listEticketByTouristid(touristid);}
}
