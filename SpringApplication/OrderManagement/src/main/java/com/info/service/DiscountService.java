package com.info.service;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {
	
	public double applyDiscount(double amount) {
        if (amount > 5000) return amount * 0.9; // 10%
        return amount;
    }

}
