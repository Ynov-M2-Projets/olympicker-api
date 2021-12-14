package com.ynov.olympicker.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CreateStageDTO {
    private String name;
    private String description;
    private Date date;
    private String location;
    private Double price;
}
