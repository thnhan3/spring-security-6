package com.thnhan3.practice.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
    private String message;

    public MessageResponse(String s) {
        this.message = s;
    }
}