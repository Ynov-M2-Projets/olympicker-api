package com.ynov.olympicker.dto;

import lombok.Data;

@Data
public class CreateRankingEntryDTO {
    private Long userId;
    private Integer position;
}
