package com.bitwise.springbootcaching.services;

import com.bitwise.springbootcaching.models.Category;
import com.bitwise.springbootcaching.repositories.CategoryRepository;
import com.bitwise.springbootcaching.models.dtos.CreateCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(CreateCategoryDto createCategoryDto) {
        return categoryRepository.save(createCategoryDto.toCategory());
    }

    public List<Category> findAll() {
        return categoryRepository.findAllByIdGreaterThanOrderByIdDesc(0);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
