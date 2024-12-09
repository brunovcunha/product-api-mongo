package com.brunov.api.product.productapi.repositories;

import com.brunov.api.product.productapi.models.Product;
import com.brunov.api.product.productapi.models.dto.ProductDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findByProductIdentifier(String productIdentifier);
    Product findByCategoryId(String categoryId);
}
