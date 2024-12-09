package com.brunov.api.product.productapi.controllers;

import com.brunov.api.product.productapi.models.dto.CategoryDTO;
import com.brunov.api.product.productapi.models.dto.ProductDTO;
import com.brunov.api.product.productapi.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    private ResponseEntity<ProductDTO> save(@RequestBody @Valid  ProductDTO productDTO) {

        return ResponseEntity.ok(productService.save(productDTO));
    }

    @PutMapping("/{productId}")
    private ResponseEntity<ProductDTO> update(@PathVariable String productId, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.update(productId, productDTO));
    }

    @GetMapping
    private ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{productId}")
    private ResponseEntity<ProductDTO> findById(@PathVariable String productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping("/identifier/{productIdentifier}")
    private ResponseEntity<ProductDTO> findByProductIdentifier(@PathVariable String productIdentifier) {
        return ResponseEntity.ok(productService.findByProductIdentifier(productIdentifier));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getByCategoryId(@PathVariable String categoryId) {
        return ResponseEntity.ok(productService.findByCategoryId(categoryId));
    }


    @DeleteMapping("/{productId}")
    private ResponseEntity<Void> delete(@PathVariable String productId) {
        productService.delete(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<ProductDTO>> getPageable(Pageable pageable) {
        return ResponseEntity.ok(productService.getPageable(pageable));
    }



}
