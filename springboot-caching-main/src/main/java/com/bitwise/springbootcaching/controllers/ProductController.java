package com.bitwise.springbootcaching.controllers;

import com.bitwise.springbootcaching.models.CacheData;
import com.bitwise.springbootcaching.models.Product;
import com.bitwise.springbootcaching.models.responses.ProductListResponse;
import com.bitwise.springbootcaching.models.dtos.CreateProductDto;
import com.bitwise.springbootcaching.models.dtos.SearchProductDto;
import com.bitwise.springbootcaching.models.responses.ProductResponse;
import com.bitwise.springbootcaching.repositories.CacheDataRepository;
import com.bitwise.springbootcaching.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final CacheDataRepository cacheDataRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public ProductController(ProductService productService, CacheDataRepository cacheDataRepository) {
        this.productService = productService;
        this.cacheDataRepository = cacheDataRepository;
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductDto createProductDto) {
        Product product = productService.create(createProductDto);

        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping
    public ResponseEntity<ProductListResponse> getAll() throws JsonProcessingException, InterruptedException {
        Optional<CacheData> optionalCacheData = cacheDataRepository.findById("allProducts");

        // cache hit
        if (optionalCacheData.isPresent()) {
            String productsAsString = optionalCacheData.get().getValue();
            TypeReference<List<Product>> mapType = new TypeReference<>() {};
            List<Product> productList = objectMapper.readValue(productsAsString, mapType);

            return ResponseEntity.ok(new ProductListResponse(productList));
        }

        // Cache miss
        List<Product> productList = productService.findAll();
        String productsAsJsonString = objectMapper.writeValueAsString(productList);
        CacheData cacheData = new CacheData("allProducts", productsAsJsonString);

        cacheDataRepository.save(cacheData);

        return ResponseEntity.ok(new ProductListResponse(productList));
    }

    @GetMapping("/search")
    public ResponseEntity<ProductListResponse> search(@Valid SearchProductDto searchProductDto) throws InterruptedException, JsonProcessingException {
        String cacheKey = searchProductDto.buildCacheKey("searchProducts");
        Optional<CacheData> optionalCacheData = cacheDataRepository.findById(cacheKey);

        // Cache hit
        if (optionalCacheData.isPresent()) {
            String productAsString = optionalCacheData.get().getValue();
            TypeReference<List<Product>> mapType = new TypeReference<>() {};
            List<Product> productList = objectMapper.readValue(productAsString, mapType);

            return ResponseEntity.ok(new ProductListResponse(productList));
        }

        List<Product> productList = productService.search(searchProductDto);
        String productsAsJsonString = objectMapper.writeValueAsString(productList);
        CacheData cacheData = new CacheData(cacheKey, productsAsJsonString);

        cacheDataRepository.save(cacheData);

        return ResponseEntity.ok(new ProductListResponse(productList));
    }
}
