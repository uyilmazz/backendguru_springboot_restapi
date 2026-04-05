package com.example.restapiegitimders1.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.restapiegitimders1.dto.ProductRequest;
import com.example.restapiegitimders1.dto.ProductResponse;
import com.example.restapiegitimders1.entity.Category;
import com.example.restapiegitimders1.entity.Product;
import com.example.restapiegitimders1.mapper.ProductMapper;
import com.example.restapiegitimders1.service.CategoryService;
import com.example.restapiegitimders1.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductCotroller implements IProductController {
	
	private final ProductService productService;
	private final CategoryService categoryService;
	private final ProductMapper productMapper;
	
	public ProductCotroller(ProductMapper productMapper, ProductService productService, CategoryService categoryService) {
		this.productMapper = productMapper;
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@Override
	@GetMapping("/{id}")
	public ProductResponse get(@PathVariable("id") Long id){
		ProductResponse product = productMapper.toResponse(productService.findById(id));
		if(product == null) {
			throw new NoSuchElementException("Product not found!");
		}
		
		return product;
	}
	
	@GetMapping("/hateos/{id}")
	public EntityModel<ProductResponse> getWithHateOs(@PathVariable("id") Long id){
		ProductResponse product = productMapper.toResponse(productService.findById(id));
		if(product == null) {
			throw new NoSuchElementException("Product not found!");
		}
		
		return toModel(product);
	}
	
	@Override
	@GetMapping
	public CollectionModel<EntityModel<ProductResponse>> list(
			@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
			){
		
		List<EntityModel<ProductResponse>> productResponse = productService.findAll(pageable).stream().map(productMapper::toResponse).map(this::toModel).toList();
		return CollectionModel.of(productResponse);
	}
	
	/*
	@GetMapping("/hateos")
	public CollectionModel<EntityModel<ProductResponse>> listWithHateOs(){
		return CollectionModel.of(products.values().stream().map(EntityModel::of).toList());
	}*/
	
	@Override
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<EntityModel<ProductResponse>> create(@Valid @RequestBody ProductRequest product){ // validasyon kontrolleri için @Valid unutulmamalı
		Product p = productMapper.toEntity(product);
		Category category = categoryService.findById(product.categoryId());
		p.setCategory(category);
		
		Product createdProduct = productService.save(p);
		EntityModel<ProductResponse> responseEntityModel = EntityModel.of(productMapper.toResponse(createdProduct));
		return ResponseEntity.created(
				linkTo(methodOn(ProductCotroller.class).get(createdProduct.getId()))
				.toUri()).body(responseEntityModel);
	}
	
	@Override
	@PutMapping("/{id}")
	public EntityModel<ProductResponse> update(@PathVariable("id") Long id, @RequestBody ProductRequest request){
		if(productService.findById(id) == null) {
			throw new NoSuchElementException("Product not found!");
		}
		
		Product updatedProduct = productService.update(productMapper.toEntity(request));
		return toModel(productMapper.toResponse(updatedProduct));
	}
	
	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if(productService.findById(id) == null) {
			throw new NoSuchElementException("Product not found!");
		}
		
		productService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	public EntityModel<ProductResponse> toModel(ProductResponse product){
		return EntityModel.of(product,
				linkTo(methodOn(ProductCotroller.class).get(product.getId())).withSelfRel(),
				linkTo(methodOn(ProductCotroller.class).list(null)).withRel("products"));
	}
}
