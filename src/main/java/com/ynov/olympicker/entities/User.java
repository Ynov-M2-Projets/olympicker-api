package com.ynov.olympicker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.source.tree.Tree;
import lombok.Data;

import javax.persistence.*;
import java.util.*;

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


    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Organization> ownedOrganizations;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "user_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    List<Event> events;


    @JsonIgnore
    public List<Organization> getAllOrganizations() {
        Set<Organization> allOrganizations = new HashSet<>();
        allOrganizations.addAll(this.organizations);
        allOrganizations.addAll(this.ownedOrganizations);
        return new ArrayList<>(allOrganizations);
    }

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Ranking ranking;
}

