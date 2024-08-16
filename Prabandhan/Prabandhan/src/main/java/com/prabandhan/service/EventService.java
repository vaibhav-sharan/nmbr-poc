package com.prabandhan.service;

import com.prabandhan.model.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    List<Event> getAllEvents();
    Event getEventDetails(Long eventId);
    Event updateEvent(Long eventId, Event event);
    void deleteEvent(Long eventId);
}