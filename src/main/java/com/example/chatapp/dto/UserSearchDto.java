package com.example.chatapp.dto;

public class UserSearchDto {
    private Long id;
    private String username;
    private String email;

    public UserSearchDto() {}
    public UserSearchDto(Long id, String username, String email) {
        this.id = id; this.username = username; this.email = email;
    }
    // getters & setters
}
