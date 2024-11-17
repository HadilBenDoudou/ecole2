package com.ecole.ecole.auth;

public class AuthResponse {
    private String message;
    private String token; // Champ pour le token

    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    // Getters et Setters
    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
