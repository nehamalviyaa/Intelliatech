package com.info.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.info.entity.Product;

@Repository
public interface ProductRepository  extends JpaRepository<Product , UUID>{

	
	// Find by SKU
    Optional<Product> findBySku(String sku);

    // Low stock products
    @Query("SELECT p FROM Product p WHERE p.stock < :threshold")
    List<Product> findLowStock(@Param("threshold") int threshold);

    // Search by name (pagination + sorting)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
