package com.ynov.olympicker.dto;

import com.ynov.olympicker.entities.Stage;
import com.ynov.olympicker.entities.StageEvent;
import lombok.Data;

@Data
public class AddStageResponseDTO {

    public AddStageResponseDTO(StageEvent event, Stage stage) {
        this.event = event;
        this.stage = stage;
    }

    private StageEvent event;
    private Stage stage;
}
