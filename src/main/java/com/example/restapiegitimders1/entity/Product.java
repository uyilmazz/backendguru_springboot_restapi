package com.example.restapiegitimders1.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@NoArgsConstructor
@Data
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@SequenceGenerator(name = "product_seq", sequenceName = "products_id_seq", allocationSize = 1)
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private String sku;
	private String barcode;
	
	@ManyToOne(fetch = FetchType.LAZY) // default lazy
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;	
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}


