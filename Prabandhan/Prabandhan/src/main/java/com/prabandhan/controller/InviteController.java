package com.prabandhan.controller;

import com.prabandhan.model.Invite;
import com.prabandhan.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events/{eventId}/invites")
public class InviteController {
    @Autowired
    private InviteService inviteService;

    @PostMapping
    public ResponseEntity<Invite> createInvite(@PathVariable Long eventId, @RequestBody Invite invite) {
        Invite newInvite = inviteService.createInvite(eventId, invite);
        inviteService.sendInvites(eventId, invite); // Send invite notifications
        return ResponseEntity.ok(newInvite);
    }
}