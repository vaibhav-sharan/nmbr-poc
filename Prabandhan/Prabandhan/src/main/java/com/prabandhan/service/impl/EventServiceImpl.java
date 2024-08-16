package com.prabandhan.service.impl;

import com.prabandhan.model.Event;
import com.prabandhan.repository.EventRepository;
import com.prabandhan.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getEventDetails(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Override
    public Event updateEvent(Long eventId, Event event) {
        Event existingEvent = getEventDetails(eventId);
        existingEvent.setName(event.getName());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setDate(event.getDate());
        existingEvent.setLocation(event.getLocation());
        return eventRepository.save(existingEvent);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}