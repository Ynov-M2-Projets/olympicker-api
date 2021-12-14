package com.ynov.olympicker.controllers;

import com.ynov.olympicker.application.PaginationResponse;
import com.ynov.olympicker.dto.CreateEventDTO;
import com.ynov.olympicker.entities.Event;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.services.AuthService;
import com.ynov.olympicker.services.EventService;
import com.ynov.olympicker.services.OrganizationService;
import com.ynov.olympicker.utils.PaginationUtils;
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
    @RequestMapping(method = RequestMethod.POST)
    public Event createEvent(@RequestBody CreateEventDTO event, Principal principal) {
        return this.eventService.createEvent(event);
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

}
