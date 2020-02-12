package com.example.lab1;

public class Message {
    private String message;
    private String type;
    private long id;

    Message(long id, String message, String type) {
        this.message = message;
        this.id = id;
        this.type = type;
    }

    String getMessage() {
        return message;
    }

    String getType() {
        return type;
    }

}