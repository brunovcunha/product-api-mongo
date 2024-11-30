package com.brunov.api.product.productapi.services;

import com.brunov.api.product.productapi.models.Category;
import com.brunov.api.product.productapi.models.dto.CategoryDTO;
import com.brunov.api.product.productapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDTO::convert)
                .collect(Collectors.toList());
    }

    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryRepository.save(Category.convert(categoryDTO));
        return CategoryDTO.convert(category);
    }

    public CategoryDTO update(String categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setNome(categoryDTO.getNome());
        categoryRepository.save(category);
        return CategoryDTO.convert(category);
    }

    public Page<CategoryDTO> getPageable(Pageable page) {
        Page<Category> categoryDTOPage = categoryRepository.findAll(page);
        return categoryDTOPage.map(CategoryDTO::convert);
    }

    public void delete(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        categoryRepository.delete(category);
    }


}
