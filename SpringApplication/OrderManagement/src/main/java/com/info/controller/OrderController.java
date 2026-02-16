package com.info.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.dto.OrderResponseDTO;
import com.info.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	
	private OrderService service;
	public OrderController(OrderService service){
		this.service = service;
	}
	
	//place order
	@PostMapping("/place-order/{userId}/{productId}")
	public ResponseEntity<OrderResponseDTO> placeOrder(
	        @PathVariable Integer userId,
	        @PathVariable Integer productId) {

	    return ResponseEntity.ok(
	            service.placeOrder(userId, productId)
	    );
	}
	
	//Order History
	@GetMapping("/order-history/{userId}")
	public ResponseEntity<?> orderHistory(@PathVariable Integer userId ){
		return ResponseEntity.ok(service.orderHistory(userId));
	}

}
