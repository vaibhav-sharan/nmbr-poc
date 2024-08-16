package com.prabandhan.dto;

import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private Long eventId;
    private String type;
    private Double price;
    private Integer quantity;

}