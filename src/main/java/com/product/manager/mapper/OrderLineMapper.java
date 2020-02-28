package com.product.manager.mapper;

import com.product.manager.dto.OrderLineInputDTO;
import com.product.manager.dto.OrderLineOutputDTO;
import com.product.manager.entity.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderLineMapper {
    OrderLine mapToOrderLine(OrderLineInputDTO orderLineInputDTO);
    OrderLineOutputDTO mapToOrderLineOutputDTO(OrderLine orderLine);
}
