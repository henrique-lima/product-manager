package com.product.manager.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class OrderInputDTO {

    public OrderInputDTO() {
    }

    @NotBlank
    @Email(message = "Not a valid email")
    private String customerEmail;
    @NotEmpty(message = "The order should contain at least one product")
    @Valid
    private List<OrderLineInputDTO> orderLines;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<OrderLineInputDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineInputDTO> orderLines) {
        this.orderLines = orderLines;
    }
}
