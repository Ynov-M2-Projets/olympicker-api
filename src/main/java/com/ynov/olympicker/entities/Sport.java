package com.ynov.olympicker.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sports")
@Data
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private SportType type = SportType.OTHER;

    @Column(name = "description")
    private String description;
}
