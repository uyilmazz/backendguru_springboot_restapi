package com.example.restapiegitimders1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.restapiegitimders1.dto.ProductRequest;
import com.example.restapiegitimders1.dto.ProductResponse;
import com.example.restapiegitimders1.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	ProductResponse toResponse(Product product);
	
	@Mapping(target = "price", expression = "java(new java.math.BigDecimal(productRequest.price()))")
	Product toEntity(ProductRequest productRequest);
}
