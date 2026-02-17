package com.info.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.dto.UserResponseDTO;
import com.info.entity.User;
import com.info.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	
	private UserService service;
	public UserController(UserService service) {
		this.service = service;
	}
	
	//Create User
	@PostMapping("/create")
	public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody User user){
		User savedUser = service.saveUser(user);

	    UserResponseDTO dto = new UserResponseDTO();
	    dto.setId(savedUser.getId());
	    dto.setName(savedUser.getName());
	    dto.setEmail(savedUser.getEmail());

	    return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id){
		service.deleteUser(id);
		return ResponseEntity.ok("User Deleted  : "+id);
	}
}
