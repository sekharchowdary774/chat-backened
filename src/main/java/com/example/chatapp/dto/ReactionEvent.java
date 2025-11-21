package com.example.chatapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReactionEvent {
    private Long messageId;
    private String emoji;
    private String userEmail;
    private String receiver;
}
