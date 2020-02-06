package com.example.lab1;
public class Message {
    private String message;
    private ChatRoomActivity.MessageType type;

    Message(String message, ChatRoomActivity.MessageType type) {
        this.message = message;
        this.type = type;
    }

    String getMessage() {
        return message;
    }

    ChatRoomActivity.MessageType getType() {
        return type;
    }

}