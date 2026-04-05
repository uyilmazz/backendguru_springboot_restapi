package com.example.restapiegitimders1.controller;


import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.restapiegitimders1.dto.ProductRequest;
import com.example.restapiegitimders1.dto.ProductResponse;
import com.example.restapiegitimders1.exception.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product API", description = "API for managing products") // for documents
public interface IProductController {

	@GetMapping("/{id}")
	@Operation(summary = "Get product by ID", description="Retrieve a product by its unique ID")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Product found"),
		@ApiResponse(responseCode = "404", description = "Product not found!", content = @Content(
				mediaType = "application/json", schema=@Schema(implementation = ErrorResponse.class)))
	})
	
								
	ProductResponse get(Long id);

	@GetMapping
	@Operation(summary = "List all products", description = "Retrieve a list of all products")
	CollectionModel<EntityModel<ProductResponse>> list(Pageable pageable);

	ResponseEntity<EntityModel<ProductResponse>> create(ProductRequest product);

	EntityModel<ProductResponse> update(Long id, ProductRequest request);

	ResponseEntity<Void> delete(Long id);

}