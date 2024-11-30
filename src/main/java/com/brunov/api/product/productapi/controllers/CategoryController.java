package com.brunov.api.product.productapi.controllers;

import com.brunov.api.product.productapi.models.dto.CategoryDTO;
import com.brunov.api.product.productapi.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    private ResponseEntity<CategoryDTO> save(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.save(categoryDTO));
    }

    @GetMapping
    private ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/pageable")
    private ResponseEntity<Page<CategoryDTO>> getPageable(Pageable pageable) {
        return ResponseEntity.ok(categoryService.getPageable(pageable));
    }

    @PutMapping("/{categoryId}")
    private ResponseEntity<CategoryDTO> update(@PathVariable String categoryId, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.update(categoryId, categoryDTO));
    }

    @DeleteMapping("/{categoryId}")
    private ResponseEntity<Void> delete(@PathVariable String categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.noContent().build();
    }
}
