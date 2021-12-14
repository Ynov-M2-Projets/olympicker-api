package com.ynov.olympicker.services;

import com.ynov.olympicker.dto.CreateEventDTO;
import com.ynov.olympicker.entities.Event;
import com.ynov.olympicker.entities.Organization;
import com.ynov.olympicker.entities.Sport;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.repositories.EventRepository;
import com.ynov.olympicker.repositories.OrganizationRepository;
import com.ynov.olympicker.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository<Event> eventRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private OrganizationRepository organizationRepository;


    public Page<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(CreateEventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        Sport sport = sportRepository.findById(eventDTO.getSportId()).orElse(null);
        Organization organization = organizationRepository.findById(eventDTO.getOrganizationId()).orElse(null);
        if (sport == null || organization == null) {
            throw new IllegalArgumentException("Sport or Organization not found");
        }
        event.setSport(sport);
        event.setOrganization(organization);
        return eventRepository.save(event);
    }

    public boolean joinEvent(User user, Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) return false;
        event.addParticipant(user);
        eventRepository.save(event);
        return true;
    }

    public boolean leaveEvent(User user, Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) return false;
        event.getParticipants().remove(user);
        eventRepository.save(event);
        return true;
    }
}
