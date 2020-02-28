package com.product.manager.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderLine {

    public OrderLine() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer quantity;
    private BigDecimal orderLineValue;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
