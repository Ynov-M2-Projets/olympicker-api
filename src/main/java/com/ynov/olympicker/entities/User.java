package com.ynov.olympicker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "user_organization",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "organization_id"))
    List<Organization> organizations;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    List<Event> events;

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
}

