package com.prabandhan.controller;

import com.prabandhan.model.Ticket;
import com.prabandhan.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events/{eventId}/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@PathVariable Long eventId, @RequestBody Ticket ticket) {
        Ticket newTicket = ticketService.createTicket(eventId, ticket);
        return ResponseEntity.ok(newTicket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTicketsForEvent(@PathVariable Long eventId) {
        List<Ticket> tickets = ticketService.getTicketsForEvent(eventId);
        return ResponseEntity.ok(tickets);
    }
}
