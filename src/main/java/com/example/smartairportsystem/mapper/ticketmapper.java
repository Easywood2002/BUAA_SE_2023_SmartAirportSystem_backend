package com.example.smartairportsystem.mapper;

import com.example.smartairportsystem.entity.ticket;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ticketmapper {
    public ticket getTicketByID(String ticketid);
    public void addNewTicket(ticket newticket);
    public void updateOldTicket(ticket newticket);
    public void removeOldTicket(String ticketid);
    public List<ticket> listTicketByFlightid(String flightid);
}
