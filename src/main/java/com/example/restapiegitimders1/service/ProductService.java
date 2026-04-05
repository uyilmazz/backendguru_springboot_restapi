package com.example.restapiegitimders1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.restapiegitimders1.entity.Product;
import com.example.restapiegitimders1.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public Product findById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	public Page<Product> findAll(Pageable pageable){
		return productRepository.findAll(pageable);
	}

	public Product update(Product product) {
		return productRepository.save(product);
	}
}
