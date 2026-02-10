package com.info.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
	
	//save
	@Transactional
	@CacheEvict(value = {"users"}, allEntries = true)
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	//getAllUsers
	@Cacheable(value = "userList")
	public List<User> getAllUsers(){
		return userRepo.findAll();
		
	}
	
	//getUserByID
	@Cacheable(value = "userById" , key="#id", unless = "#result == null" )
	public User getUserById(Long id) {
	   
	        return userRepo.findById(id).orElse(null);
	   
	}

	//updateUser
	@CachePut(value = "userById" , key = "#id")
	@CacheEvict(value = "users" , allEntries = true , beforeInvocation = true)
	public User updateUser(Long id , User user) {
		
		User exsitingUser = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
		exsitingUser.setName(user.getName());
		exsitingUser.setEmail(user.getEmail());
	    return userRepo.save(exsitingUser);
	}
	
	
	//DeleteUser
//	@Caching(evict = {
//		    @CacheEvict(value = "userById", key = "#id"),
//		    @CacheEvict(value = "userList", allEntries = true , beforeInvocation = true)
//		})
	
	@CacheEvict(value = "userList", allEntries = true , beforeInvocation = true)
	public void deleteUser(Long id) {
     try {
		userRepo.deleteById(id);
     }
     catch(Exception e) {
    	 e.printStackTrace();
     }
	}
}








