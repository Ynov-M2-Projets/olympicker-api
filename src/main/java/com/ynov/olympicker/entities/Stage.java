package com.ynov.olympicker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table
@Entity
@Data
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date date;

    @Column
    private String location;

    @Column
    private Double price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "event_id")
    private StageEvent event;

    @OneToMany(mappedBy = "stage")
    @JsonIgnore
    private List<Ranking> ranking;
}
