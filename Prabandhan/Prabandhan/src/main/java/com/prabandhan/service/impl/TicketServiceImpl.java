package com.prabandhan.service.impl;

import com.prabandhan.model.Ticket;
import com.prabandhan.repository.TicketRepository;
import com.prabandhan.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(Long eventId, Ticket ticket) {
        // Assuming validation of event ID
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getTicketsForEvent(Long eventId) {
        return ticketRepository.findByEventId(eventId);
    }
}