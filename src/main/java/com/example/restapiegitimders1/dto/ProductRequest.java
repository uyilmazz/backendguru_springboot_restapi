package com.example.restapiegitimders1.dto;

import com.example.restapiegitimders1.validation.OneOfSkuOrBarcodeRequired;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@OneOfSkuOrBarcodeRequired()
public record ProductRequest(
		@NotBlank(message = "Product name must not be blank")
		String name, 
		
		@Min(value = 0, message = "Price must be a positive value")
		@Max(value = 10000, message = "Price must not exceed 10,000")
		double price, 
		
		@Size(max = 500, message = "Description must not exceed 500 characters")
		String description, 
		String sku, 
		String barcode,
		
		@NotNull(message = "CategoryId must not be null")
		@Valid Long categoryId) {

}
