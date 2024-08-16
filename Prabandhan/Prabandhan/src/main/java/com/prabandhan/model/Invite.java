package com.prabandhan.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "invites")
@Data
public class Invite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private String email;
    private String phoneNumber;
    private String whatsappNumber;
    private String message;

}
