package com.product.manager.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductUpdateDTO {

    public ProductUpdateDTO() {
    }

    @NotNull(message = "ID is mandatory")
    private Long id;
    @NotBlank(message = "SKU is mandatory")
    private String sku;
    private String description;
    @NotNull(message = "Price is mandatory")
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
