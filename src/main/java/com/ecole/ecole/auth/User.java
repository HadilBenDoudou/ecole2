package com.ecole.ecole.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private Long id;
    private String username;
    private String email;
    private String password;

    private String dateOfBirth;  // Ajoutez cet attribut
    private String role;         // Ajoutez cet attribut

    // Getters et Setters pour tous les attributs

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getDateOfBirth() {  // Getter pour dateOfBirth
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {  // Setter pour dateOfBirth
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {  // Getter pour role
        return role;
    }

    public void setRole(String role) {  // Setter pour role
        this.role = role;
    }
}
