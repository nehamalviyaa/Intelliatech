package com.info.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.info.entity.ChatMessage;
import com.info.service.ChatService;

@RestController
@RequestMapping
public class ChatController {
	
	
	private ChatService service;
	public ChatController(ChatService service) {
		this.service = service;
	}
	
	
	@MessageMapping("chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(ChatMessage msg) {
		return service.save(msg);
	}
	
	@GetMapping("/message")
	@ResponseBody
	public List<ChatMessage> history(){
		return service.getAllMessages();
	}
	

}
