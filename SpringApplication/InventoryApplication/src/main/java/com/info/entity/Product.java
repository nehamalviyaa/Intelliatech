package com.info.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product  extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String sku;

    @Column(nullable = false)
    private Integer stock; 
    

	@Column(nullable = false)
    private BigDecimal price;

   
    @Enumerated(EnumType.STRING)
    private Category category;


    
	public UUID getId() {return id;}

	public void setId(UUID id) {this.id = id;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}

	public String getSku() {return sku;}

	public void setSku(String sku) {this.sku = sku;}

	public Integer getStock() {return stock;}

	public void setStock(Integer stock) {this.stock = stock;}	

	public BigDecimal getPrice() {return price;}

	public void setPrice(BigDecimal price) {this.price = price;}

	public Category getCategory() {	return category;}

	public void setCategory(Category category) {this.category = category;}
    

}
