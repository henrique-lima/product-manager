package com.product.manager.service;

import com.product.manager.dto.OrderInputDTO;
import com.product.manager.dto.OrderLineInputDTO;
import com.product.manager.dto.OrderLineOutputDTO;
import com.product.manager.dto.OrderOutputDTO;
import com.product.manager.entity.Order;
import com.product.manager.entity.OrderLine;
import com.product.manager.entity.Product;
import com.product.manager.exception.ProductNotFoundException;
import com.product.manager.mapper.OrderLineMapper;
import com.product.manager.mapper.OrderMapper;
import com.product.manager.repository.OrderLineRepository;
import com.product.manager.repository.OrderRepository;
import com.product.manager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderLineMapper orderLineMapper;
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderLineMapper orderLineMapper, OrderRepository orderRepository, OrderLineRepository orderLineRepository, ProductRepository productRepository) {
        this.orderMapper = orderMapper;
        this.orderLineMapper = orderLineMapper;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order create(OrderInputDTO orderInputDTO) {
        Order order = orderRepository.save(orderMapper.mapToOrder(orderInputDTO));
        BigDecimal totalValue = new BigDecimal(0);
        for (OrderLineInputDTO orderLineInputDTO : orderInputDTO.getOrderLines()) {
            OrderLine orderLine = orderLineMapper.mapToOrderLine(orderLineInputDTO);

            Product product = Optional.ofNullable(productRepository.findById(orderLineInputDTO.getProductId())).get().orElseThrow(() -> new ProductNotFoundException("Product not found with ID " + orderLineInputDTO.getProductId()));
            orderLine.setProduct(product);
            BigDecimal orderLineValue = product.getPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity()));
            orderLine.setOrderLineValue(orderLineValue);
            totalValue = totalValue.add(orderLineValue);

            orderLine.setOrder(order);
            orderLineRepository.save(orderLine);
        }
        order.setOrderValue(totalValue);
        return orderRepository.save(order);
    }

    @Cacheable("orders")
    public List<OrderOutputDTO> search(LocalDateTime fromDate, LocalDateTime toDate) {
        List<Order> orderList = orderRepository.findAllByCreationDateBetween(fromDate, toDate);
        List<OrderOutputDTO> orderOutputDTOList = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderLine> orderLines = orderLineRepository.findAllByOrderId(order.getId());
            List<OrderLineOutputDTO> orderLineOutputDTOList = new ArrayList<>();
            for (OrderLine orderLine : orderLines) {
                orderLineOutputDTOList.add(orderLineMapper.mapToOrderLineOutputDTO(orderLine));
            }
            OrderOutputDTO orderOutputDTO = orderMapper.mapToOrderOutputDTO(order);
            orderOutputDTO.setOrderLines(orderLineOutputDTOList);
            orderOutputDTOList.add(orderOutputDTO);
        }
        return orderOutputDTOList;
    }
}
