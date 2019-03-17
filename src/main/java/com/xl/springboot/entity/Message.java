package com.xl.springboot.entity;

public class Message {
    private String message;
    private boolean state;

    public Message(String message, boolean state) {
        this.message = message;
        this.state = state;
    }

    public Message() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
