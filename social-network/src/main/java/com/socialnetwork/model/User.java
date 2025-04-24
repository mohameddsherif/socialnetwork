package com.socialnetwork.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

    

	@Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String bio;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Connections sent by this user
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Connection> connections = new ArrayList<>();

    // Connections received by this user
    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Connection> incomingConnections = new ArrayList<>();

    // Posts by this user
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    // Transient list of actual friend users (accepted connections)
    @Transient
    public List<User> getFriends() {
        List<User> friends = new ArrayList<>();

        for (Connection c : connections) {
            if (c.getStatus() == Status.ACCEPTED) {
                friends.add(c.getFriend());
            }
        }

        for (Connection c : incomingConnections) {
            if (c.getStatus() == Status.ACCEPTED) {
                friends.add(c.getUser());
            }
        }

        return friends;
    }
    public enum Role {
        USER,
        ADMIN
    }

    // === Getters & Setters ===

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    public List<Connection> getIncomingConnections() {
        return incomingConnections;
    }

    public void setIncomingConnections(List<Connection> incomingConnections) {
        this.incomingConnections = incomingConnections;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
