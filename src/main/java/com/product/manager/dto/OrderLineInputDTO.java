package com.product.manager.dto;

import javax.validation.constraints.NotNull;

public class OrderLineInputDTO {

    public OrderLineInputDTO() {
    }

    @NotNull(message = "Product ID is mandatory")
    private Long productId;
    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
