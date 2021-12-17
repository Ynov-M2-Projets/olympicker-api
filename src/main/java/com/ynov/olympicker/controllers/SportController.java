package com.ynov.olympicker.controllers;

import com.ynov.olympicker.dto.CreateSportDTO;
import com.ynov.olympicker.entities.Event;
import com.ynov.olympicker.entities.Sport;
import com.ynov.olympicker.services.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sports")
public class SportController {


    @Autowired
    private SportService sportService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Sport> getAllSports() {
        return sportService.getAllSports();
    }


    @RequestMapping(method = RequestMethod.POST)
    public Sport createSport(@RequestBody CreateSportDTO createSportDTO) {
        return sportService.createSport(createSportDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Sport getSportById(@PathVariable Long id) {
        Sport sport = sportService.getSportById(id);
        if (sport != null) return sport;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found");
    }


    @RequestMapping(value = "/{id}/events", method = RequestMethod.DELETE)
    public List<Event> getSportEvent(@PathVariable Long id) {
        Sport sport = sportService.getSportById(id);
        if (sport != null) return sport.getEvents();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sport not found");
    }

}
