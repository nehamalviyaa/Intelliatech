package com.info.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.info.entity.ChatMessage;
import com.info.repo.ChatMessageRepository;

import jakarta.transaction.Transactional;

@Service
public class ChatService {


    private ChatMessageRepository repo;
    public ChatService(ChatMessageRepository repo) {
    	this.repo = repo;
    }

    
    @Transactional
    public ChatMessage save(ChatMessage msg) {
        return repo.save(msg);
    }

    public List<ChatMessage> getAllMessages() {
        return repo.findAll();
    }
}

