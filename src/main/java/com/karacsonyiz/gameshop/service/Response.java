package com.karacsonyiz.gameshop.service;

public class Response {

    private boolean isValidRequest;
    private String message;

    public Response(boolean isValidRequest, String message) {
        this.isValidRequest = isValidRequest;
        this.message = message;
    }

    public boolean isValidRequest() {
        return isValidRequest;
    }

    public void setValidRequest(boolean validRequest) {
        isValidRequest = validRequest;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
