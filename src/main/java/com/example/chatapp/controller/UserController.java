package com.example.chatapp.controller;

import com.example.chatapp.dto.UserSearchDto;
import com.example.chatapp.entity.User;
import com.example.chatapp.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository ur) {
        this.userRepository = ur;
    }

    @GetMapping("/search")
    public List<UserSearchDto> searchUsers(
            @RequestParam String query,
            @RequestParam String exclude
    ) {
        return userRepository.searchUsers(query)
                .stream()
                .filter(u -> !u.getEmail().equalsIgnoreCase(exclude))
                .map(u -> new UserSearchDto(u.getId(), u.getUsername(), u.getEmail()))
                .collect(Collectors.toList());
    }
}
