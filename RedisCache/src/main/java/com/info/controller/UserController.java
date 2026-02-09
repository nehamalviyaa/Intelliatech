package com.info.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.entity.User;
import com.info.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private UserService service;
	public UserController(UserService service){
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		 return ResponseEntity.ok(service.save(user));
	}
	
	@PostMapping("/bulk")
	public ResponseEntity<List<User>> save(@RequestBody List<User> userList){
		return ResponseEntity.ok(service.saveUserList(userList));
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.ok(service.getAllUser());
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById( @PathVariable int id){
		return ResponseEntity.ok(service.getUserById(id));
	}
}
