package com.ynov.olympicker.services;

import com.ynov.olympicker.dto.CreateEventDTO;
import com.ynov.olympicker.entities.Event;
import com.ynov.olympicker.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;


    List<Event> getAllEvents(Integer page, Integer size) {
        return eventRepository.findAll(PageRequest.of(page, size))
                .getContent();
    }

    Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    Event createEvent(CreateEventDTO event) {
        return eventRepository.save(event);
    }

}
