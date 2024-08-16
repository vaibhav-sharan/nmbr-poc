package com.prabandhan.service;

import com.prabandhan.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket createTicket(Long eventId, Ticket ticket);
    List<Ticket> getTicketsForEvent(Long eventId);
}