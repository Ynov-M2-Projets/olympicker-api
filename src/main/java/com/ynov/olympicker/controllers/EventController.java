package com.ynov.olympicker.controllers;

import com.ynov.olympicker.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/events")
@RestController
public class EventController {

    @Autowired
    private EventService eventService;


}
