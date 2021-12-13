package com.ynov.olympicker.controllers;

import com.ynov.olympicker.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService eventService;


}
