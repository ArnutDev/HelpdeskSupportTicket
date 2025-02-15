package com.arnut.helpdesk.ticketing.entity;

public class LoginResponse {
    private String message;

    public LoginResponse(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    // Setter
    public void setMessage(String message) {
        this.message = message;
    }
}

