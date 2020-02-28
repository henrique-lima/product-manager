package com.product.manager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderOutputDTO {

    public OrderOutputDTO() {
    }

    private long id;
    private String customerEmail;
    private List<OrderLineOutputDTO> orderLines;
    private BigDecimal orderValue;
    private LocalDateTime creationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<OrderLineOutputDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineOutputDTO> orderLines) {
        this.orderLines = orderLines;
    }

    public BigDecimal getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(BigDecimal orderValue) {
        this.orderValue = orderValue;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
