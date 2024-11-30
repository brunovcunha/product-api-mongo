package com.brunov.api.product.productapi.repositories;

import com.brunov.api.product.productapi.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Category findByNome(String nome);

}
