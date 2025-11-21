package com.example.chatapp.controller;

import com.example.chatapp.service.ForgotPasswordService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class ForgotPasswordController {

    private final ForgotPasswordService service;

    public ForgotPasswordController(ForgotPasswordService service) {
        this.service = service;
    }

    @PostMapping("/forgot-password")
    public Map<String, String> forgotPassword(@RequestBody Map<String, String> req) {
        String email = req.get("email");
        return service.handleForgotPassword(email);
    }

    @PostMapping("/reset-password")
    public Map<String, String> resetPassword(@RequestBody Map<String, String> req) {
        String email = req.get("email");
        String password = req.get("password");
        return service.resetPassword(email, password);
    }
}
