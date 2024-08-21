package com.thnhan3.practice.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public @NotBlank String getUsername() {
        return username;
    }
    public LoginRequest(){}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank String password) {
        this.password = password;
    }

    public String toString() {
        return "LoginRequest [username=" + username + ", password=" + password + "]";
    }
}
