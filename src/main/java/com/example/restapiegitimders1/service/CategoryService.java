package com.example.restapiegitimders1.service;


import org.springframework.stereotype.Service;

import com.example.restapiegitimders1.entity.Category;
import com.example.restapiegitimders1.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	public Category findById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found!"));
	}
}
