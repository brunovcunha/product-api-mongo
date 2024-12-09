package com.brunov.api.product.productapi.services;

import com.brunov.api.product.productapi.models.Product;
import com.brunov.api.product.productapi.models.dto.ProductDTO;
import com.brunov.api.product.productapi.repositories.CategoryRepository;
import com.brunov.api.product.productapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDTO save(ProductDTO productDTO) {

        if (productDTO.getCategory() == null || !categoryRepository.existsById(productDTO.getCategory().getId())) {
            throw new RuntimeException("Category not found");
        }

        String productIdentifier = productDTO.getNome().toLowerCase().replace(" ", "-");
        productDTO.setProductIdentifier(productIdentifier);
        Product product = productRepository.save(Product.convert(productDTO));
        return ProductDTO.convert(product);
    }

    public ProductDTO update(String productId, ProductDTO productDTO) {
        // Verifica se o produto existe
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Atualiza os campos não nulos
        if (productDTO.getNome() != null) {
            product.setNome(productDTO.getNome());
        }
        if (productDTO.getDescricao() != null) {
            product.setDescricao(productDTO.getDescricao());
        }
        if (productDTO.getPreco() != null) {
            product.setPreco(productDTO.getPreco());
        }
        if (productDTO.getCategory() != null && productDTO.getCategory().getId() != null) {
            product.setCategory(categoryRepository.findById(productDTO.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found")));
        }

        // Salva e retorna o DTO atualizado
        product = productRepository.save(product);
        return ProductDTO.convert(product);
    }


    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findById(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductDTO.convert(product);
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        try {
            Product product = productRepository.findByProductIdentifier(productIdentifier);
            return ProductDTO.convert(product);
        } catch (RuntimeException e) {
            throw new RuntimeException("Product not found");
        }

    }

    public ProductDTO findByCategoryId(String categoryId) {
        try {
            Product product = productRepository.findByCategoryId(categoryId);
            return ProductDTO.convert(product);
        } catch (RuntimeException e) {
            throw new RuntimeException("Product not found");
        }
    }

    public void delete(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    public Page<ProductDTO> getPageable(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(ProductDTO::convert);
    }



}
