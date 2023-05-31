package com.example.smartairportsystem.service.impl;

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

    public ticket getTicketByID(String ticketid){return ticketMapper.getTicketByID(ticketid);}
    public void addNewTicket(ticket newticket){ticketMapper.addNewTicket(newticket);}
    public void updateOldTicket(ticket newticket){ticketMapper.updateOldTicket(newticket);}
    public void removeOldTicket(String ticketid){ticketMapper.removeOldTicket(ticketid);}
    public List<ticket> listTicketByFlightid(String flightid){return ticketMapper.listTicketByFlightid(flightid);}
}
