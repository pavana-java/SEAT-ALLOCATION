package com.tataelxsi.ott.response;

import com.tataelxsi.ott.entity.Seat;
import com.tataelxsi.ott.entity.User;

import java.util.List;

public class LoginResponse {

    private int status;
    private String message;
    private String token;

    private User user;

    public LoginResponse() {
    }

    public LoginResponse(int status, String message, String token, User user) {
        this.status = status;
        this.message = message;
        this.token = token;
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
