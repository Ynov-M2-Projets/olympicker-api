package com.ynov.olympicker.services;

import com.ynov.olympicker.dto.CreateStageDTO;
import com.ynov.olympicker.dto.CreateStageEventDTO;
import com.ynov.olympicker.dto.CreateSimpleEventDTO;
import com.ynov.olympicker.entities.*;
import com.ynov.olympicker.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SimpleEventRepository simpleEventRepository;

    @Autowired
    private StageEventRepository stageEventRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private UserRepository userRepository;


    public Page<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public SimpleEvent createSimpleEvent(CreateSimpleEventDTO eventDTO) {
        SimpleEvent event = new SimpleEvent();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        if (eventDTO.getMaxParticipant() != null) {
            event.setSlots(eventDTO.getMaxParticipant());
        }
        Stage stage = new Stage();
        stage.setName(eventDTO.getName());
        stage.setDescription(eventDTO.getDescription());
        stage.setDate(eventDTO.getDate());
        stage.setLocation(eventDTO.getLocation());
        stage.setPrice(eventDTO.getPrice());
        stageRepository.save(stage);
        Sport sport = sportRepository.findById(eventDTO.getSportId()).orElse(null);
        Organization organization = organizationRepository.findById(eventDTO.getOrganizationId()).orElse(null);
        if (sport == null || organization == null) {
            throw new IllegalArgumentException("Sport or Organization not found");
        }
        event.setSport(sport);
        event.setOrganization(organization);
        event.setStage(stage);
        return simpleEventRepository.save(event);
    }

    public boolean joinEvent(User user, Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) return false;
        if (event.getSlots() != null && event.getSlots() <= event.getParticipants().size()) return false;
        user.getEvents().add(event);
        userRepository.save(user);
        return true;
    }

    public boolean leaveEvent(User user, Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event == null) return false;
        user.getEvents().remove(event);
        userRepository.save(user);
        return true;
    }

    public StageEvent createStageEvent(CreateStageEventDTO event) {

        StageEvent stageEvent = new StageEvent();
        stageEvent.setName(event.getName());
        stageEvent.setDescription(event.getDescription());
        if (event.getMaxParticipant() != null) {
            stageEvent.setSlots(event.getMaxParticipant());
        }
        Sport sport = sportRepository.findById(event.getSportId()).orElse(null);
        Organization organization = organizationRepository.findById(event.getOrganizationId()).orElse(null);
        if (sport == null || organization == null) {
            throw new IllegalArgumentException("Sport or Organization not found");
        }
        stageEvent.setSport(sport);
        stageEvent.setOrganization(organization);
        Stage stage = new Stage();
        stage.setName(event.getName());
        stage.setDescription(event.getDescription());
        stageEventRepository.save(stageEvent);
        stage.setEvent(stageEvent);
        stageRepository.save(stage);
        return stageEvent;
    }

    public Stage addStageToEvent(CreateStageDTO stageDTO, StageEvent event) {
        if (event == null) return null;
        Stage stage = new Stage();
        stage.setLocation(stageDTO.getLocation());
        stage.setDate(stageDTO.getDate());
        stage.setName(stageDTO.getName());
        stage.setDescription(stageDTO.getDescription());
        stage.setPrice(stageDTO.getPrice());
        stageRepository.save(stage);
        event.addStage(stage);
        eventRepository.save(event);
        return stage;
    }
}
