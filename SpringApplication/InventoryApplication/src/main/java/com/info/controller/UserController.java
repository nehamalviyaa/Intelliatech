package com.info.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.info.dto.UserLoginDTO;
import com.info.dto.UserRequestDTO;
import com.info.dto.UserResponseDTO;
import com.info.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @Validated @RequestBody UserRequestDTO dto) {

        return ResponseEntity.ok(service.register(dto));
    }

   
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Validated @RequestBody UserLoginDTO dto) {

        return ResponseEntity.ok(service.login(dto));
    }
}