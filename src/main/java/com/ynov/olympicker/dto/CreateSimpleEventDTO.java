package com.ynov.olympicker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties()
@Data
public class CreateEventDTO {

    private String name;

    private String description;

    private Long sportId;

    private Long organizationId;
}
