package com.example.restapiegitimders1.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

import com.example.restapiegitimders1.entity.Category;
import com.example.restapiegitimders1.entity.Product;

import org.testcontainers.junit.jupiter.Container;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("ProductRepository Integeration Tests")
class ProductRepositoryIntegrationTest {

	@Container
	private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:15")
	.withDatabaseName("testdb")
	.withUsername("testuser")
	.withPassword("testpass")
	.withReuse(true);
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.flyway.enabled", () -> "false");
	}
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private ProductRepository productRepository;
	
	private Category testCategory;
	
	@BeforeEach
	void setUp() {
		testCategory = new Category();
		testCategory.setName("Electronics");
		testCategory.setCreatedAt(LocalDateTime.now());
		testCategory.setUpdatedAt(LocalDateTime.now());
		
		testEntityManager.persistAndFlush(testCategory); // Persist maybe wait transaction end. PersistAndFlush not wait transaction now save.
	}
	
	@Test
	void shouldSaveAndFindProductById() {
		Product product = new Product();
		product.setSku("SKU001");
        product.setBarcode("123456789");
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setCategory(testCategory);
        product.setPrice(BigDecimal.ONE);
        
        Product savedProduct = productRepository.save(product);
        testEntityManager.flush();
        
        Product dbProduct = productRepository.findById(savedProduct.getId()).get();
        
        assertNotNull(dbProduct.getId());
        assertEquals(product.getName(), dbProduct.getName());
	}
}
