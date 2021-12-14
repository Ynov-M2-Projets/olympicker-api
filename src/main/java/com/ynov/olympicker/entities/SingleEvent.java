package com.ynov.olympicker.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("E")
@EqualsAndHashCode(callSuper = false)
@Data
public class SingleEvent extends Event {

    @OneToOne
    private Stage stage;

}
