package com.tataelxsi.ott.response;

import com.tataelxsi.ott.entity.User;

public class RegisterResponse {
    private int status;
    private String message;
    private User user;

    public RegisterResponse() {
    }

    public RegisterResponse(int status, String message, User user) {
        this.status = status;
        this.message = message;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
