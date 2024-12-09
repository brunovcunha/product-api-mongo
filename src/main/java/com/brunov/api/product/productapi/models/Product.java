package com.brunov.api.product.productapi.models;

import com.brunov.api.product.productapi.models.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product {
    @Id
    private String id;
    private String productIdentifier;
    private String nome;
    private String descricao;
    private Double preco;

    @DBRef
    private Category category;

    public static Product convert(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setProductIdentifier(dto.getProductIdentifier());
        product.setNome(dto.getNome());
        product.setDescricao(dto.getDescricao());
        product.setPreco(dto.getPreco());
        product.setCategory(Category.convert(dto.getCategory()));
        return product;
    }



}
