package com.ynov.olympicker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "organizations")
@Data
public class Organization {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToMany(mappedBy = "organizations")
    @JsonIgnore
    private List<User> members;

    @Column
    private Boolean verified = false;


    public Boolean isMember(User user) {
        return this.members.contains(user) || this.owner.equals(user);
    }
}
