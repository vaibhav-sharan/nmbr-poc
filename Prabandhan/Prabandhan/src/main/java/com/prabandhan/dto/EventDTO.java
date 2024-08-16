package com.prabandhan.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private String location;

}