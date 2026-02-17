package com.info.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {
	
	public double applyDiscount(double amount) {
        if (amount > 5000) return amount * 0.9; 
        return amount;
    }
	
	 public double calculateDiscount(double subtotal) {

	        
	        if (subtotal >= 5000) {
	            return subtotal * 0.10; // 10% discount
	        }

	        if (subtotal >= 2000) {
	            return subtotal * 0.05; // 5% discount
	        }

	        return 0.0;
	    }

}
