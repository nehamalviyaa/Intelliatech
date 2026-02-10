package com.info.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.info.entity.User;
import com.info.repo.UserRepository;


@Service
public class UserService {
	
	private UserRepository userRepo;
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
		
	}
	
	//save
	@CacheEvict(value = {"users"}, allEntries = true)
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	//getAllUsers
	@Cacheable(value = "users")
	public List<User> getAllUsers(){
		return userRepo.findAll();
		
	}
	
	//getUserByID
	@Cacheable(value = "userById" , key="#id")
	public User getUserById(Long id) {
	   
	        return userRepo.findById(id).orElse(null);
	   
	}

	
	//updateUser
	@CachePut(value = "userById" , key = "#id")
	@CacheEvict(value = {"users"} , allEntries = true)
	public User updateUser(Long id , User user) {
		
		User exsitingUser = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
		exsitingUser.setName(user.getName());
		exsitingUser.setEmail(user.getEmail());
		
	    return userRepo.save(exsitingUser);
	}
	
	
	//DeleteUser
	@CacheEvict(value = {"users" , "userById"} , allEntries = true)
	public void deleteUser(Long id) {
     userRepo.deleteById(id);
     System.out.println("User deleted for ID : "+id);
	}
}
