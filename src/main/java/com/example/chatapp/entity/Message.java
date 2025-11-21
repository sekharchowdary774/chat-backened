package com.example.chatapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String receiver;
    private String content;
    private String timestamp;
    private String status; // SENT / DELIVERED / SEEN

    @Column(columnDefinition = "TEXT")
    private String reactions; // JSON

    // For "Delete for Everyone"
    private boolean deleted = false;

    // For "Edit Message"
    private String editedContent;

    // 🔥 NEW: For "Delete for Me"
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "message_deleted_for", joinColumns = @JoinColumn(name = "message_id"))
    @Column(name = "user_email")
    private List<String> deletedFor = new ArrayList<>();
}
