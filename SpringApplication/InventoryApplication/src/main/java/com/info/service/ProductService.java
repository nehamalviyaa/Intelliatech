package com.info.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.info.dto.ProductRequestDTO;
import com.info.dto.ProductResponseDTO;
import com.info.dto.StockUpdateDTO;
import com.info.entity.Product;
import com.info.exception.InsufficientStockException;
import com.info.exception.ResourceNotFoundException;
import com.info.repo.ProductRepository;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

   
    public ProductResponseDTO saveProduct(ProductRequestDTO dto) {

        productRepo.findBySku(dto.getSku()).ifPresent(p -> {
            throw new IllegalArgumentException("Product with this SKU already exists");
        });

        Product product = new Product();
        product.setName(dto.getName());
        product.setSku(dto.getSku());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(dto.getCategory());

        return mapToResponse(productRepo.save(product));
    }

  
    public StockUpdateDTO updateStock(UUID productId, int stock) {

        if (stock < 0) {
            throw new InsufficientStockException("Stock cannot be negative");
        }

        Product product = productRepo.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + productId));

        product.setStock(stock);

        Product updatedProduct = productRepo.save(product);

        StockUpdateDTO dto = new StockUpdateDTO();
        dto.setProductId(updatedProduct.getId());
        dto.setStock(updatedProduct.getStock());

        return dto;
    }

   
    @Transactional(readOnly = true)
    public ProductResponseDTO checkStock(UUID productId) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not available"));

        return mapToResponse(product);
    }

   
    @Transactional(readOnly = true)
    public ProductResponseDTO getBySku(String sku) {

        Product product = productRepo.findBySku(sku)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with SKU: " + sku));

        return mapToResponse(product);
    }

   
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getLowStockProducts(int threshold) {

        return productRepo.findLowStock(threshold)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    
    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> searchProducts(String name, Pageable pageable) {

        return productRepo.findByNameContainingIgnoreCase(name, pageable)
                .map(this::mapToResponse);
    }

   
    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> getProducts(Pageable pageable) {

        return productRepo.findAll(pageable)
                .map(this::mapToResponse);
    }

    
    @Transactional(readOnly = true)
   public String deleteProduct( UUID productId) {
	   
	   Product product = productRepo.findById(productId)
               .orElseThrow(() ->
                       new ResourceNotFoundException("Product not available"));
	   
	  productRepo.delete(product);
	  return "Product Deleted Successdully : "+productId;
	   
   }
    private ProductResponseDTO mapToResponse(Product product) {

        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setSku(product.getSku());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategory(product.getCategory());
        dto.setCreatedAt(product.getCreatedAt());

        return dto;
    }
}