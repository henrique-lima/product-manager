package com.product.manager.controller;

import com.product.manager.dto.ProductCreationDTO;
import com.product.manager.dto.ProductUpdateDTO;
import com.product.manager.entity.Product;
import com.product.manager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid ProductCreationDTO productCreationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productCreationDTO));
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody @Valid ProductUpdateDTO productUpdateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(productUpdateDTO));
    }
}
