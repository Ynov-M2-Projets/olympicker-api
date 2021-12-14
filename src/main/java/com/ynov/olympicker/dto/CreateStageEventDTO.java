package com.ynov.olympicker.dto;

import lombok.Data;

@Data
public class CreateStageEventDTO {
    private String name;

    private String description;

    private Long sportId;

    private Long organizationId;
}
