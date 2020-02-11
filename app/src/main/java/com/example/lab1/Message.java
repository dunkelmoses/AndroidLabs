package com.example.lab1;

public class Message {
    private String message;
    private ChatRoomActivity.MessageType type;
    private long id;

    Message(long id, String message, ChatRoomActivity.MessageType type) {
        this.message = message;
        this.id = id;
        this.type = type;
    }

    String getMessage() {
        return message;
    }

    ChatRoomActivity.MessageType getType() {
        return type;
    }

}