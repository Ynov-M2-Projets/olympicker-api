package com.ynov.olympicker.controllers;

import com.ynov.olympicker.entities.Event;
import com.ynov.olympicker.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/events")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Event> getEvents(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "25") Integer size
    ) {
        return this.eventService.getAllEvents(page, size);
    }


}
