package com.ynov.olympicker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Table(name = "rankings")
@Entity
@Data
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_stage", referencedColumnName = "id")
    private Stage stage;


    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    private Integer position;
}
