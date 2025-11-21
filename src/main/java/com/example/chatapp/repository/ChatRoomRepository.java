package com.example.chatapp.repository;

import com.example.chatapp.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    List<ChatRoom> findAllBySender(String sender);

    Optional<ChatRoom> findBySenderAndReceiver(String sender, String receiver);

}

