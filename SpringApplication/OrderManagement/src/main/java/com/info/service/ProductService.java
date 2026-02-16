package com.info.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.info.dto.ProductResponseDTO;
import com.info.dto.StockUpdateDTO;
import com.info.entity.Product;
import com.info.exception.ResourceNotFoundException;
import com.info.repo.ProductRepository;



@Service
public class ProductService {
	
	
	private  ProductRepository productRepo;
    public ProductService(ProductRepository productRepo) {
	this.productRepo = productRepo;
	}
	
	
     //SAVE
     public Product saveProduct(Product product) {
    	 return productRepo.save(product);
     }
     
     
     //UPDATE
     public StockUpdateDTO updateStock(Integer productId, int stock) {

         Product product = productRepo.findById(productId)
                 .orElseThrow(() -> new RuntimeException("Product not found"));

         product.setStock(stock);
         Product updatedProduct = productRepo.save(product);

         StockUpdateDTO dto = new StockUpdateDTO();
         dto.setProductId(updatedProduct.getId());
         dto.setStock(updatedProduct.getStock());

         return dto;
     }
     
     
     //CheckStock
     public ProductResponseDTO checkStock(Integer productId) {
    	 
    	Product product = productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product Not Available"));
    	 
    	ProductResponseDTO  dto = new ProductResponseDTO();
    	dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        
        return dto;
    	
     }
     
     
     //Product List
     public List<Product> getProducts(){
		return productRepo.findAll();
    	 
    	 
     }
     
}
