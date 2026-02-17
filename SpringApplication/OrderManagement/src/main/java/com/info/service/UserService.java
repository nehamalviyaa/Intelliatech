package com.info.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.info.entity.User;
import com.info.repo.UserRepository;

@Service
public class UserService {
	
	
	private UserRepository userRepo;
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	
	@Transactional
	public User saveUser(User user) {
	return userRepo.save(user);
	}
	
	
	//DELETE
	public void deleteUser(Integer id) {
	 userRepo.deleteById(id);
	}
}
