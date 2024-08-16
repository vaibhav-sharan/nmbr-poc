package com.prabandhan.dto;

import lombok.Data;

@Data
public class InviteDTO {
    private Long id;
    private Long eventId;
    private String email;
    private String phoneNumber;
    private String whatsappNumber;
    private String message;

}