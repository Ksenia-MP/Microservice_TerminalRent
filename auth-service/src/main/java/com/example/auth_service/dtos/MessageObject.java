package com.example.auth_service.dtos;

public class MessageObject {
    private final String message;

    public MessageObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
