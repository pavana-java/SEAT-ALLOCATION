package com.tataelxsi.ott.response;

public class ForgotResponse {
    private int status;
    private String message;

    public ForgotResponse() {
    }

    public ForgotResponse(int status, String message) {
        this.status = status;
        this.message = message;
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
}
