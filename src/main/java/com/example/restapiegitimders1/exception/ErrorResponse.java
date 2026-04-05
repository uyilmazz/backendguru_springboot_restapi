package com.example.restapiegitimders1.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error response schema for API exceptions")
public record ErrorResponse(
		@Schema(description = "Error message describing the issue", example = "Product not found")
		String error
		) {
	
}