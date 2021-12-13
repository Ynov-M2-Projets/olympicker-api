package com.ynov.olympicker.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ynov.olympicker.entities.Organization;

@JsonIgnoreProperties({"owner", "members", "verified", "id"})
public class CreateOrganizationDTO extends Organization { }
