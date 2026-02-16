package com.info.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.entity.Cart;
import com.info.entity.CartStatus;
import com.info.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart , Integer>{

	Optional<Cart> findByUserAndStatus(User user ,CartStatus status);
	
	
}
