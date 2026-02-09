package com.info.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.info.entity.User;
import com.info.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private UserRepository userRepo;
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Transactional
	public User save( User user){
		if(userRepo.findByEmail(user.getEmail()).isPresent()) {
			throw new RuntimeException("User not Found");
		}
		return userRepo.save(user);
	}
	
	@Transactional
	@CacheEvict(value = "usersList", allEntries = true)
	public List<User> saveUserList(List<User> userList){
		return userRepo.saveAll(userList);
	}
	
	@Cacheable(value = "users_all")
	public List<User> getAllUser(){
		return userRepo.findAll();
	}
	
	@Cacheable(value = "users" , key = "#id")
	public User getUserById(int id) {
		return userRepo.findById(id).orElseThrow();
	}
}
