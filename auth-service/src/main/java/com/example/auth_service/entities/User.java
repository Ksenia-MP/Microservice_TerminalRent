package com.example.auth_service.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tr_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Long getId() {
        return id;
    }

}
