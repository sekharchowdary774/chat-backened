package com.example.chatapp.service;

import com.example.chatapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ForgotPasswordService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordService(UserRepository userRepo,
                                 PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, String> handleForgotPassword(String email) {
        if (!userRepo.existsByEmail(email)) {
            return Map.of(
                    "success", "false",
                    "message", "Email not found"
            );
        }

        // No email, no token — directly go to reset password screen
        return Map.of(
                "success", "true",
                "email", email,
                "message", "Proceed to reset page"
        );
    }

    public Map<String, String> resetPassword(String email, String password) {
        if (!userRepo.existsByEmail(email)) {
            return Map.of("message", "Email not found");
        }

        userRepo.updatePassword(email, passwordEncoder.encode(password));

        return Map.of("message", "Password updated successfully");
    }
}
