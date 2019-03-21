package com.springboot.base.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageResponse {

    @JsonProperty("message")
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
