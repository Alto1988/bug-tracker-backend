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

    public UserProfile() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeveloperLevel getDeveloperLevel() {
        return developerLevel;
    }

    public void setDeveloperLevel(DeveloperLevel developerLevel) {
        this.developerLevel = developerLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
