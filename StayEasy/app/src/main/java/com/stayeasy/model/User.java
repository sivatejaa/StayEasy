package com.stayeasy.model;

public class User {
    private String userId;
    private String username;
    private String email;

    // Default constructor required for Firebase
    public User() {}

    public User(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    // Getter methods
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}

