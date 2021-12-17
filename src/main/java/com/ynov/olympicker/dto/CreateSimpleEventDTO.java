package com.ynov.olympicker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@JsonIgnoreProperties()
@Data
public class CreateSimpleEventDTO {

    private String name;

    private String description;

    private Long sportId;

    private Long organizationId;

    private Date date;

    private String location;

    private Double price;

    private Integer maxParticipant;
}
