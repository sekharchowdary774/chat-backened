package com.example.chatapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TypingEvent {
    private String sender;    // who is typing
    private String receiver;  // who should see it
    private boolean typing;   // true when typing, false when stopped
}
