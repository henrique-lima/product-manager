package com.product.manager.mapper;

import com.product.manager.dto.ProductCreationDTO;
import com.product.manager.dto.ProductUpdateDTO;
import com.product.manager.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product mapToProduct(ProductCreationDTO productCreationDTO);
    Product mapToProduct(ProductUpdateDTO productUpdateDTO);
}
