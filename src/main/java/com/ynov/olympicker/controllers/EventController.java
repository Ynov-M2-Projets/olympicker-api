package com.ynov.olympicker.controllers;

import com.ynov.olympicker.dto.CreateEventDTO;
import com.ynov.olympicker.entities.Event;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.services.AuthService;
import com.ynov.olympicker.services.EventService;
import com.ynov.olympicker.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Event> getEvents(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "25") Integer size
    ) {
        return this.eventService.getAllEvents(page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable("id") Long id) {
        Event event = this.eventService.getEventById(id);
        if (event != null) return event;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
    }

    @PreAuthorize("organizationService.getOrganizationById(event.organizationId).isMember(authService.whoami(principal))")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Event createEvent(@RequestBody CreateEventDTO event, Principal principal) {
        User user = this.authService.whoami(principal);
        return this.eventService.createEvent(event, user);
    }

}
