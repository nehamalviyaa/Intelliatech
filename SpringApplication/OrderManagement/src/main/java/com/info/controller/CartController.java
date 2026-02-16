package com.info.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.info.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	
	private CartService service;
	public CartController(CartService service) {
		this.service = service;
	}
	
	//Add Item In Cart
	@PostMapping("/{userId}/{productId}/{qty}")
	public ResponseEntity<?> addItemInCart(@PathVariable int userId ,@PathVariable int productId ,@PathVariable int qty){
	service.addItem(userId, productId, qty);
	return ResponseEntity.ok("Item Added Successfully ");
	}
	
	
	//View Cart
	@GetMapping("/{userId}")
	public ResponseEntity<?> viewCart(@PathVariable Integer userId){
	return ResponseEntity.ok(service.viewCart(userId));	
	}
	
	
	//Remove Item
	@DeleteMapping("/{userId}/{productId}")
	public ResponseEntity<?> removeItem(@PathVariable Integer userId , @PathVariable Integer productId ){
		service.removeItem(userId, productId);
		return ResponseEntity.ok("Item Removed ProductId : "+productId);
	}
}
