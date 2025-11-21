package com.example.chatapp.service;

import com.example.chatapp.entity.ChatRoom;
import com.example.chatapp.repository.ChatRoomRepository;
import com.example.chatapp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;

    public String getRoomId(String sender, String receiver, boolean createIfMissing) {

        Optional<ChatRoom> existing = chatRoomRepository.findBySenderAndReceiver(sender, receiver);
        if (existing.isPresent()) return existing.get().getRoomId();

        if (!createIfMissing) return null;

        String roomId = UUID.randomUUID().toString();

        // store both directions (A->B & B->A)
        chatRoomRepository.save(ChatRoom.builder()
                .sender(sender)
                .receiver(receiver)
                .roomId(roomId)
                .build());

        chatRoomRepository.save(ChatRoom.builder()
                .sender(receiver)
                .receiver(sender)
                .roomId(roomId)
                .build());

        return roomId;
    }

    // return all rooms + unread count
    public List<ChatRoom> getUserRooms(String email) {

        List<ChatRoom> rooms = chatRoomRepository.findAllBySender(email);


        rooms.forEach(r -> {
            int count = messageRepository.countBySenderAndReceiverAndStatus(r.getReceiver(), email, "DELIVERED");
            r.setUnread(count);
        });

        return rooms;
    }

}
