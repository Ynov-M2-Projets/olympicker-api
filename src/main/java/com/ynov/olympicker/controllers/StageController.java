package com.ynov.olympicker.controllers;

import com.ynov.olympicker.dto.CreateRankingEntryDTO;
import com.ynov.olympicker.entities.Ranking;
import com.ynov.olympicker.entities.Stage;
import com.ynov.olympicker.services.AuthService;
import com.ynov.olympicker.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Stage getStage(@PathVariable("id") Long id) {
        Stage stage = this.stageService.getStage(id);
        if (stage != null) return stage;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stage not found");
    }


    @RequestMapping(value = "/{id}/ranking", method = RequestMethod.GET)
    public List<Ranking> getStageRaning(@PathVariable("id") Long id) {
        List<Ranking> ranks = this.stageService.getRanking(id);
        if (ranks != null) return ranks;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Stage not found");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{id}/ranking", method = RequestMethod.POST)
    @PreAuthorize("stageService.getStage(#id).event.organization.isMember(authService.whoami(principal))")
    public Ranking getStageRanking(@PathVariable("id") Long id, @RequestBody CreateRankingEntryDTO createRankingEntry) {
        Stage stage = this.stageService.getStage(id);
        if (stage != null){
            Ranking ranking = this.stageService.createRankingEntry(stage, createRankingEntry);
            if (ranking != null) return ranking;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

}
