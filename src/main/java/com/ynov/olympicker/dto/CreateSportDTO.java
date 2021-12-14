package com.ynov.olympicker.dto;

import com.ynov.olympicker.entities.SportType;
import lombok.Data;

@Data
public class CreateSportDTO {
    private String name;
    private String description;
    private SportType type;
}
