package com.info;

import java.time.Year;

import org.springframework.stereotype.Component;

@Component
public class InvoiceNumberGenerator {
	
	
	public String generate(Integer orderId) {
		return "INV-" +Year.now().getValue()+ "-"+String.format("%06d", orderId);
	}

}
