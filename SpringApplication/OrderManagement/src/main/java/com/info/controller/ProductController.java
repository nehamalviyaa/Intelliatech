package com.info.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.dto.ProductResponseDTO;
import com.info.dto.StockUpdateDTO;
import com.info.entity.Product;
import com.info.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	
	private ProductService service ;
	public ProductController(ProductService service) {
		this.service = service;
		
	}
	
	//Save Product
	@PostMapping("/save")
	public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody Product product){
		Product savedProduct = service.saveProduct(product);
		
		ProductResponseDTO dto = new ProductResponseDTO();
		dto.setId(savedProduct.getId());
		dto.setName(savedProduct.getName());
		dto.setPrice(savedProduct.getPrice());
		dto.setStock( savedProduct.getStock());
		
		return ResponseEntity.ok(dto);
		
	}
	
	//Update Stock
	@PostMapping("/update-stock/{productId}")
	public ResponseEntity<StockUpdateDTO> updateStock(
	        @PathVariable Integer productId,
	        @RequestBody StockUpdateDTO dto) {

	    return ResponseEntity.ok(
	            service.updateStock(productId, dto.getStock())
	    );
	}
	
	
	//Check Stock
	@GetMapping("/check-stock/{productId}")
	public ResponseEntity<ProductResponseDTO> checkStock(@PathVariable Integer productId){
		return ResponseEntity.ok(service.checkStock(productId));
	}
	
	//Product List
	@GetMapping("/list")
	public ResponseEntity<List<ProductResponseDTO>> getProductList() {

	    List<ProductResponseDTO> dtoList = service.getProducts()
	        .stream()
	        .map(p -> {
	            ProductResponseDTO dto = new ProductResponseDTO();
	            dto.setId(p.getId());
	            dto.setName(p.getName());
	            dto.setPrice(p.getPrice());
	            dto.setStock(p.getStock());
	            return dto;
	        })
	        .toList();

	    return ResponseEntity.ok(dtoList);
	}


}
