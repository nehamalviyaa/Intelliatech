package com.info.dto;

import java.util.UUID;

public class StockUpdateDTO {
	
	private UUID productId;
    private Integer stock;
    
    
	public UUID getProductId() {
		return productId;
	}
	public void setProductId(UUID productId) {
		this.productId = productId;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
    

}
