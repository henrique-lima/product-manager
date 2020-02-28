package com.product.manager.mapper;

import com.product.manager.dto.OrderInputDTO;
import com.product.manager.dto.OrderOutputDTO;
import com.product.manager.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    Order mapToOrder(OrderInputDTO orderInputDTO);
    OrderOutputDTO mapToOrderOutputDTO(Order order);
}
