package com.info.dto;

public class StockUpdateDTO {
	
	private Integer productId;
    private Integer stock;
	public Integer getProductId() {
		return productId;
	}
	
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
