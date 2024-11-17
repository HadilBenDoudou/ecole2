package com.ecole.ecole.auth;

public class ForgotPasswordRequest {
    private String email;

    // Default constructor
    public ForgotPasswordRequest() {
    }

    // Getter and setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
