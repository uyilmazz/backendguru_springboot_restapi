package com.example.restapiegitimders1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.restapiegitimders1.entity.Category;
import com.example.restapiegitimders1.entity.Product;
import com.example.restapiegitimders1.repository.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductService Unit Tests")
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductService productService;
	
	private Product testProduct;
	private Category testCategory;
	
	@BeforeEach
	void setUp() {
		testCategory = new Category();
		testCategory.setId(1L);
		testCategory.setName("Electronics");
		
		testProduct = new Product();
		testProduct.setId(1L);
		testProduct.setSku("SKU001");
		testProduct.setBarcode("123456789");
		testProduct.setDescription("Test Description");
		testProduct.setPrice(BigDecimal.valueOf(99.99));
		testProduct.setCategory(testCategory);
	}
	
	@Test
	@DisplayName("Should save product successfully when category exists")
	void shouldSaveProductSuccessfully() {
		when(productRepository.save(testProduct)).thenReturn(testProduct);
		
		Product product = productService.save(testProduct);
		assertNotNull(product);
		assertEquals(testProduct.getName(), product.getName());
		assertEquals(testCategory, product.getCategory());
	}
	
	@Test
	@DisplayName("Should find product by ID successfully")
	void shouldFindProductByIdSuccessfully() {
		when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
		
		Product product = productService.findById(1L);
		assertNotNull(product);
		assertEquals(testProduct.getName(), product.getName());
		assertEquals(testCategory, product.getCategory());
	}
	
	@Test
	@DisplayName("Should delete product by ID successfully")
	void shouldDeleteProductByIdSuccessfully() {
		Long productId = 1L;
		
		productService.deleteById(productId);
	}
	
	@Test
	@DisplayName("Should update product successfully")
	void shouldUpdateProductSuccessfully() {
		
		Product updatedProduct = new Product();
		updatedProduct.setId(testProduct.getId());
		updatedProduct.setSku("SKU002");
		updatedProduct.setBarcode(testProduct.getBarcode());
		updatedProduct.setDescription(testProduct.getDescription());
		updatedProduct.setPrice(testProduct.getPrice());
		updatedProduct.setCategory(testCategory);
		
		when(productService.update(updatedProduct)).thenReturn(updatedProduct);
		
		Product product = productService.update(updatedProduct);
		assertEquals(product.getId(), testProduct.getId());
		assertEquals(product.getBarcode(), testProduct.getBarcode());
		assertEquals(product.getDescription(), testProduct.getDescription());
		assertEquals(product.getCategory(), testProduct.getCategory());
		assertNotEquals(product.getSku(), testProduct.getSku());
	}
	
	@Test
	@DisplayName("Should find all product successfully")
	void shouldFindAllProductSuccessfully() {
		
		Pageable pageable = Pageable.unpaged();
		PageImpl<Product> productPage = new PageImpl<>(java.util.List.of(testProduct), pageable, 1);
		when(productService.findAll(pageable)).thenReturn(productPage);
		
		Page<Product> pageProducts = productService.findAll(pageable);
		
		assertNotNull(pageProducts);
		assertEquals(1, pageProducts.getTotalElements());
		
	}
}
