package com.prabandhan.service;

import com.prabandhan.model.Invite;

public interface InviteService {
    Invite createInvite(Long eventId, Invite invite);
    void sendInvites(Long eventId, Invite invite);
}
