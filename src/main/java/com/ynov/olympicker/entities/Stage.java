package com.ynov.olympicker.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table
@Entity
@Data
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date date;

    @Column
    private String location;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private StageEvent event;
}
