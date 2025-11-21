package com.example.chatapp.repository;

import com.example.chatapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findBySenderAndReceiverOrReceiverAndSender(String s1, String r1, String s2, String r2);

    // used by ChatController → mark SEEN
    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.status = 'SEEN' WHERE m.sender = :sender AND m.receiver = :receiver AND (m.status <> 'SEEN' OR m.status IS NULL)")
    void markSeen(@Param("sender") String sender, @Param("receiver") String receiver);

    // *** THIS ONE IS REQUIRED FOR CHAT ROOM LIST ***
    int countBySenderAndReceiverAndStatus(String sender, String receiver, String status);

    // optional (global unread count)
    @Query("SELECT COUNT(m) FROM Message m WHERE m.receiver = :email AND m.status <> 'SEEN'")
    long getUnreadCount(String email);

    // global unread count v2
    @Query("SELECT COUNT(m) FROM Message m WHERE m.receiver = :user AND m.status <> 'SEEN'")
    long countUnreadTotal(String user);

    // unread count from single sender
    @Query("SELECT COUNT(m) FROM Message m WHERE m.receiver = :user AND m.sender = :sender AND m.status <> 'SEEN'")
    long countUnreadFromUser(String user, String sender);


}
