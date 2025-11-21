package com.example.chatapp.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OnlineService {

    private final Map<String, Boolean> onlineUsers = new ConcurrentHashMap<>();
    private final Map<String, String> lastSeen = new ConcurrentHashMap<>();

    public void markOnline(String email) {
        onlineUsers.put(email, true);
    }

    public void markOffline(String email) {
        onlineUsers.put(email, false);
        lastSeen.put(email, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    public Map<String, Boolean> getOnlineUsers() {
        return onlineUsers;
    }

    public Map<String, String> getLastSeen() {
        return lastSeen;
    }

    public boolean isOnline(String email) {
        return onlineUsers.getOrDefault(email, false);
    }

}

