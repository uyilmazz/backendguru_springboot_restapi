package com.example.restapiegitimders1.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import com.example.restapiegitimders1.dto.ProductRequest;
import com.example.restapiegitimders1.dto.ProductResponse;
import com.example.restapiegitimders1.entity.Category;
import com.example.restapiegitimders1.entity.Product;
import com.example.restapiegitimders1.mapper.ProductMapper;
import com.example.restapiegitimders1.service.CategoryService;
import com.example.restapiegitimders1.service.JwtService;
import com.example.restapiegitimders1.service.ProductService;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Product Controller test")
public class ProductControllerTest {

	  	@Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private ObjectMapper objectMapper;

	    @MockitoBean
	    private ProductService productService;

	    @MockitoBean
	    private CategoryService categoryService;

	    @MockitoBean
	    private ProductMapper productMapper;

	    @MockitoBean
	    private JwtService jwtService;
	    
	    @Test
	    void shouldCreateProductSuccessfully() throws Exception {
	        // Given
	        ProductRequest request = new ProductRequest("iPhone", 999, "Latest iPhone model", "SKU-123", null, 3L);

	        Category category = new Category();
	        Product product = new Product();
	        Product savedProduct = new Product();
	        savedProduct.setId(1L);


	        when(productMapper.toEntity(any(ProductRequest.class))).thenReturn(product);
	        when(categoryService.findById(3L)).thenReturn(category);
	        when(productService.save(any(Product.class))).thenReturn(savedProduct);
	        when(productMapper.toResponse(savedProduct)).thenReturn(new ProductResponse(1L, "iPhone", BigDecimal.valueOf(999)));

	        // When & Then
	        mockMvc.perform(post("/api/products")
	                        .with(SecurityMockMvcRequestPostProcessors.user("admin").roles("ADMIN"))
	                        .contentType(MediaType.APPLICATION_JSON)
	                        .content(objectMapper.writeValueAsString(request)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.id").value(1))
	                .andExpect(jsonPath("$.name").value("iPhone"))
	                .andExpect(jsonPath("$.price").value(999));
	    }
}
