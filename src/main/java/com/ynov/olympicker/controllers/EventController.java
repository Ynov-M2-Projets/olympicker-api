package com.ynov.olympicker.controllers;

import com.ynov.olympicker.dto.CreateSimpleEventDTO;
import com.ynov.olympicker.dto.CreateStageDTO;
import com.ynov.olympicker.dto.CreateStageEventDTO;
import com.ynov.olympicker.entities.*;
import com.ynov.olympicker.services.AuthService;
import com.ynov.olympicker.services.EventService;
import com.ynov.olympicker.services.OrganizationService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RequestMapping(value = "/events")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private AuthService authService;

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(method = RequestMethod.GET)
    public Page<Event> getEvents(@ParameterObject @PageableDefault(size = 25) Pageable pageable) {
        return this.eventService.getAllEvents(pageable);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable("id") Long id) {
        Event event = this.eventService.getEventById(id);
        if (event != null) return event;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
    }

    @PreAuthorize("organizationService.getOrganizationById(event.organizationId).isMember(authService.whoami(principal))")
    @RequestMapping(value = "/simple", method = RequestMethod.POST)
    public SimpleEvent createSimpleEvent(@RequestBody CreateSimpleEventDTO event, Principal principal) {
        return this.eventService.createSimpleEvent(event);
    }

    @PreAuthorize("organizationService.getOrganizationById(event.organizationId).isMember(authService.whoami(principal))")
    @RequestMapping(value = "/stage", method = RequestMethod.POST)
    public StageEvent createStageEvent(@RequestBody CreateStageEventDTO event, Principal principal) {
        return this.eventService.createStageEvent(event);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}/join", method = RequestMethod.GET)
    public void joinEvent(@PathVariable("id") Long id, Principal principal) {
        User user = this.authService.whoami(principal);
        boolean joined = this.eventService.joinEvent(user, id);
        if (!joined) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}/leave", method = RequestMethod.GET)
    public void leaveEvent(@PathVariable("id") Long id, Principal principal) {
        User user = this.authService.whoami(principal);
        boolean left = this.eventService.leaveEvent(user, id);
        if (!left) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
    }

    @RequestMapping(value = "/{id}/participants", method = RequestMethod.GET)
    public List<User> getParticipants(@PathVariable("id") Long id) {
        Event event = this.eventService.getEventById(id);
        if (event != null) return event.getParticipants();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
    }


    @RequestMapping(value = "/{id}/add-stage", method = RequestMethod.POST)
    @PreAuthorize("organizationService.getOrganizationById(event.organization).isMember(authService.whoami(principal))")
    public Stage addStage(@PathVariable("id") StageEvent event, @RequestBody CreateStageDTO stageDTO) {
        Stage stage = this.eventService.addStageToEvent(stageDTO, event);
        if (stage != null) return stage;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");

    }
}
