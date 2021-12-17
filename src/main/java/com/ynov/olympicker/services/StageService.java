package com.ynov.olympicker.services;

import com.ynov.olympicker.dto.CreateRankingEntryDTO;
import com.ynov.olympicker.entities.Ranking;
import com.ynov.olympicker.entities.Stage;
import com.ynov.olympicker.entities.User;
import com.ynov.olympicker.repositories.RankingRepository;
import com.ynov.olympicker.repositories.StageRepository;
import com.ynov.olympicker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RankingRepository rankingRepository;


    public List<Ranking> getRanking(Long id) {
        Stage stage = stageRepository.findById(id).orElse(null);
        if (stage == null) return null;
        return stage.getRanking();
    }

    public Stage getStage(Long id) {
        return stageRepository.findById(id).orElse(null);
    }

    public Ranking createRankingEntry(Stage stage, CreateRankingEntryDTO createRankingEntry) {
        User user = userRepository.findById(createRankingEntry.getUserId()).orElse(null);
        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        if (stage.getEvent().getParticipants().contains(user)) {
            if (user == null) return null;
            Ranking ranking = new Ranking();
            ranking.setUser(user);
            ranking.setStage(stage);
            ranking.setPosition(createRankingEntry.getPosition());
            return rankingRepository.save(ranking);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not in event");
    }
}
