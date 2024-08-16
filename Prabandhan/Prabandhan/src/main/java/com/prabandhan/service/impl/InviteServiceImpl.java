package com.prabandhan.service.impl;

import com.prabandhan.model.Event;
import com.prabandhan.model.Invite;
import com.prabandhan.repository.EventRepository;
import com.prabandhan.repository.InviteRepository;
import com.prabandhan.service.EmailService;
import com.prabandhan.service.InviteService;
import com.prabandhan.service.SmsService;
import com.prabandhan.service.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteServiceImpl implements InviteService {
    @Autowired
    private InviteRepository inviteRepository;

    @Autowired
    private EventRepository eventRepository;


    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private WhatsAppService whatsappService;

    @Override
    public Invite createInvite(Long eventId, Invite invite) {
        // Fetch the Event entity using eventId
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Set the Event entity to the Invite
        invite.setEvent(event);

        return inviteRepository.save(invite);
    }

    @Override
    public void sendInvites(Long eventId, Invite invite) {
        // Send email
        if (invite.getEmail() != null) {
            emailService.sendEmail(invite.getEmail(), "Event Invitation", invite.getMessage());
        }
        // Send SMS
        if (invite.getPhoneNumber() != null) {
            smsService.sendSms(invite.getPhoneNumber(), invite.getMessage());
        }
        // Send WhatsApp message
        if (invite.getWhatsappNumber() != null) {
            whatsappService.sendWhatsAppMessage(invite.getWhatsappNumber(), invite.getMessage());
        }
    }
}