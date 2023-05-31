package com.example.smartairportsystem.service;

import com.example.smartairportsystem.entity.ticket;

import java.util.List;

public interface ticketservice {
    public ticket getTicketByID(String ticketid);
    public void addNewTicket(ticket newticket);
    public void updateOldTicket(ticket newticket);
    public void removeOldTicket(String ticketid);
    public List<ticket> listTicketByFlightid(String flightid);
}
