package com.info.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.entity.ChatMessage;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage , Long> {

}
