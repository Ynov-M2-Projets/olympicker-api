package com.ynov.olympicker.services;

import com.ynov.olympicker.dto.CreateSportDTO;
import com.ynov.olympicker.entities.Sport;
import com.ynov.olympicker.repositories.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService {


    @Autowired
    private SportRepository sportRepository;

    public Page<Sport> getAllSports(Pageable pageable) {
        return sportRepository.findAll(pageable);
    }


    public Sport getSportById(Long id) {
        return sportRepository.findById(id).orElse(null);
    }

    public Sport createSport(CreateSportDTO sportDTO) {
        Sport sport = new Sport();
        sport.setName(sportDTO.getName());
        sport.setDescription(sportDTO.getDescription());
        sport.setType(sportDTO.getType());
        return sportRepository.save(sport);
    }
}
