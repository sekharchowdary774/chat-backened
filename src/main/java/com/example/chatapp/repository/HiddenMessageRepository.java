package com.example.chatapp.repository;

import com.example.chatapp.entity.HiddenMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HiddenMessageRepository extends JpaRepository<HiddenMessage, Long> {

    List<HiddenMessage> findByUserEmail(String email);

    void deleteByUserEmailAndMessageId(String email, Long msgId);

    boolean existsByUserEmailAndMessageId(String email, Long msgId);
}
