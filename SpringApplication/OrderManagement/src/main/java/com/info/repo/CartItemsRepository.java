package com.info.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.info.entity.Cart;
import com.info.entity.CartItems;
import com.info.entity.Product;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems , Integer> {

	
	Optional<CartItems> findByCartAndProduct(Cart cart, Product product);
}

