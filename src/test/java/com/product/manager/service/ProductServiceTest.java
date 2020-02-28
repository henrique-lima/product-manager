package com.product.manager.service;

import com.product.manager.dto.ProductUpdateDTO;
import com.product.manager.entity.Product;
import com.product.manager.exception.ProductNotFoundException;
import com.product.manager.mapper.ProductMapper;
import com.product.manager.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductMapper productMapper;
    @Mock
    ProductRepository productRepository;

    ProductService productService;

    Product product1;

    @BeforeEach
    void init() {
        productService = new ProductService(productMapper, productRepository);

        product1 = new Product();
        product1.setId(1L);
        product1.setSku("sk001");
        product1.setDescription("test product");
        product1.setPrice(new BigDecimal(9.99));
    }

    @Test
    void testUpdateProductSuccessfully() {
        ProductUpdateDTO productUpdateDTO = new ProductUpdateDTO();
        productUpdateDTO.setId(1L);
        productUpdateDTO.setSku("sk001");
        productUpdateDTO.setDescription("test product");
        productUpdateDTO.setPrice(new BigDecimal(15.50));

        Product product2 = new Product();
        product2.setId(1);
        product2.setSku("sk001");
        product2.setDescription("test product");
        product2.setPrice(new BigDecimal(15.50));

        Optional<Product> existingProduct = Optional.of(product1);
        when(productRepository.findById(1L)).thenReturn(existingProduct);
        when(productMapper.mapToProduct(productUpdateDTO)).thenReturn(product2);
        when(productRepository.save(product2)).thenReturn(product2);

        Product updatedProduct = productService.update(productUpdateDTO);
        assertNotNull(updatedProduct);
        assertEquals(1L, updatedProduct.getId());
        assertEquals("sk001", updatedProduct.getSku());
        assertEquals("test product", updatedProduct.getDescription());
        assertEquals(new BigDecimal(15.50), updatedProduct.getPrice());
    }

    @Test
    void testUpdateProductNotExists() {
        ProductUpdateDTO productUpdateDTO = new ProductUpdateDTO();
        productUpdateDTO.setId(1L);
        productUpdateDTO.setSku("sk001");
        productUpdateDTO.setDescription("test product");
        productUpdateDTO.setPrice(new BigDecimal(15.50));

        when(productRepository.findById(1L)).thenThrow(new ProductNotFoundException("product does not exist"));
        assertThrows(ProductNotFoundException.class, () -> productService.update(productUpdateDTO));
    }
}
