package com.product.manager.dto;

import java.math.BigDecimal;

public class OrderLineOutputDTO {

    public OrderLineOutputDTO() {
    }

    private long id;
    private ProductLineOutputDTO product;
    private Integer quantity;
    private BigDecimal orderLineValue;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductLineOutputDTO getProduct() {
        return product;
    }

    public void setProduct(ProductLineOutputDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getOrderLineValue() {
        return orderLineValue;
    }

    public void setOrderLineValue(BigDecimal orderLineValue) {
        this.orderLineValue = orderLineValue;
    }
}
