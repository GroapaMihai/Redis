package com.bitwise.springbootcaching.controllers;

import com.bitwise.springbootcaching.models.Category;
import com.bitwise.springbootcaching.models.responses.CategoryListResponse;
import com.bitwise.springbootcaching.models.responses.CategoryResponse;
import com.bitwise.springbootcaching.models.dtos.CreateCategoryDto;
import com.bitwise.springbootcaching.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
        Category category = categoryService.create(createCategoryDto);

        return ResponseEntity.ok(new CategoryResponse(category));
    }

    @GetMapping
    public ResponseEntity<CategoryListResponse> getAll() {
        List<Category> categories = categoryService.findAll();

        return ResponseEntity.ok(new CategoryListResponse(categories));
    }
}
