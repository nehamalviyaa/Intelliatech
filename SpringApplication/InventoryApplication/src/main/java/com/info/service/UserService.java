package com.info.service;

import com.info.dto.UserLoginDTO;
import com.info.dto.UserRequestDTO;
import com.info.dto.UserResponseDTO;

public interface UserService {
	
	
	UserResponseDTO register(UserRequestDTO dto);

    String login(UserLoginDTO dto);

}
