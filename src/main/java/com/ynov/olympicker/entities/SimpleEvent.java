package com.ynov.olympicker.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("S")
@EqualsAndHashCode(callSuper = false)
@Data
public class SimpleEvent extends Event {

    @OneToOne
    private Stage stage;


    @Override
    public EventType getEventType() {
        return EventType.SIMPLE;
    }
}
