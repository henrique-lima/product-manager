package com.product.manager.service;

import com.product.manager.dto.ProductCreationDTO;
import com.product.manager.dto.ProductUpdateDTO;
import com.product.manager.entity.Product;
import com.product.manager.exception.ProductNotFoundException;
import com.product.manager.mapper.ProductMapper;
import com.product.manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(ProductCreationDTO productCreationDTO) {
        return productRepository.save(productMapper.mapToProduct(productCreationDTO));
    }

    public Product update(ProductUpdateDTO productUpdateDTO) {
        Optional.ofNullable(productRepository.findById(productUpdateDTO.getId())).get().orElseThrow(() ->new ProductNotFoundException("Product not found with ID " + productUpdateDTO.getId()));
        return productRepository.save(productMapper.mapToProduct(productUpdateDTO));
    }
}
