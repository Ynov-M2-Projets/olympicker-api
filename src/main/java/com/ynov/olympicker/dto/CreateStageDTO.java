package com.ynov.olympicker.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynov.olympicker.entities.Stage;

public class CreateStageDTO extends Stage {

    @Override
    @JsonIgnore
    public Long getId() {
        return super.getId();
    }

    @Override
    @JsonIgnore
    public void setId(Long id) {
        super.setId(id);
    }
}
