package com.ynov.olympicker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ynov.olympicker.entities.Event;

@JsonIgnoreProperties({ "organization", "id"})
public class CreateEventDTO extends Event {
}
