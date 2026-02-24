package com.info.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.info.dto.ProductRequestDTO;
import com.info.dto.ProductResponseDTO;
import com.info.dto.StockUpdateDTO;
import com.info.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/product")
@Tag(name = "Product Inventory APIs" , description = "APIs for managing product inventory and stock")
public class ProductController {
	
	
	private final ProductService service;
	public ProductController(ProductService service) {
		this.service = service;
		
	}
	
	
	@Operation(summary = "CREATE PRODUCT" , description = "Create new Product in Inventory")
	@ApiResponses({
		@ApiResponse(responseCode = "201" , description = " Product Created Successfully"),
		@ApiResponse(responseCode = "400", description = "Invailid Product data")
	})
	@PostMapping
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO dto){
		
	ProductResponseDTO response = service.saveProduct(dto);
	return new ResponseEntity<>(response , HttpStatus.CREATED);
		
	}
	
	
	@Operation(summary = "UPDATE STOCK" , description = "Update Stock of Product")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Stock updated Successfully"),
		@ApiResponse(responseCode = "404" , description = "Product Not Found")
	})
	@PutMapping("/{id}/{stock}")
	public ResponseEntity<StockUpdateDTO> updateStock(@PathVariable UUID id , @PathVariable int stock){
		return ResponseEntity.ok(service.updateStock(id, stock));
	}
	
	
	@Operation(summary = "Check Stock By Product Id" , description = "Get Product Details by Product ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200" , description = "Product Found"),
		@ApiResponse(responseCode = "404" , description = "Product not found")
	})
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> checkStockById(@PathVariable UUID id){
		return ResponseEntity.ok(service.checkStock(id));
		
	}
	
	
	    @Operation(summary = "Get Product by SKU", description = "Fetch product details using SKU")
	    @ApiResponse(responseCode = "200", description = "Product found")
	    @GetMapping("/sku/{sku}")
	    public ResponseEntity<ProductResponseDTO> getProductBySku(
	            @PathVariable String sku) {

	        return ResponseEntity.ok(service.getBySku(sku));
	    }

	
	    @Operation(summary = "Low Stock Products", description = "Get products with stock below threshold")
	    @ApiResponse(responseCode = "200", description = "Low stock products fetched")
	    @GetMapping("/low-stock")
	    public ResponseEntity<List<ProductResponseDTO>> getLowStockProducts(
	            @RequestParam(defaultValue = "5") int threshold) {

	        return ResponseEntity.ok(service.getLowStockProducts(threshold));
	    }
	
	    @Operation(summary = "Search Products", description = "Search products by name with pagination & sorting")
	    @ApiResponse(responseCode = "200", description = "Products fetched successfully")
	    @GetMapping("/search")
	    public ResponseEntity<Page<ProductResponseDTO>> searchProducts(
	            @RequestParam String name,
	            Pageable pageable) {

	        return ResponseEntity.ok(service.searchProducts(name, pageable));
	    }

	    @Operation(summary = "Get All Products", description = "Get all products with pagination & sorting")
	    @ApiResponse(responseCode = "200", description = "Products fetched successfully")
	    @GetMapping
	    public ResponseEntity<Page<ProductResponseDTO>> getAllProducts(
	            Pageable pageable) {

	        return ResponseEntity.ok(service.getProducts(pageable));
	    }
	    
	    @Operation(summary = "Delete Product by ID", description = "Delete the product by its UUID")
	    @ApiResponses({
	    	@ApiResponse(responseCode = "200" , description = "Product Deleted Successfully"),
	    	@ApiResponse(responseCode = "404" , description = "Product not found")
	    })
	    @DeleteMapping("/{productId}")
	    public ResponseEntity<String> deleteProductByUUID(@PathVariable UUID productId){
	    	
	    	service.deleteProduct(productId);
	    	return ResponseEntity.ok("Product Deleted Successfully for ID : "+productId);
	    }
	

}
