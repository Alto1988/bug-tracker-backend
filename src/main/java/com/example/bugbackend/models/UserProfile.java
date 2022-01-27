package com.example.bugbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    public enum DeveloperLevel {
        Junior, Mid, Senior
    }


    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private DeveloperLevel developerLevel;


    @JsonIgnore
    @OneToOne(mappedBy = "userProfile")
    private User user;

}
