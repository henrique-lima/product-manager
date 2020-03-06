package com.product.manager.service;

import com.product.manager.dto.*;
import com.product.manager.exception.ProductNotFoundException;
import com.product.manager.mapper.OrderLineMapper;
import com.product.manager.mapper.OrderMapper;
import com.product.manager.repository.OrderLineRepository;
import com.product.manager.repository.OrderRepository;
import com.product.manager.repository.ProductRepository;
import com.product.manager.entity.Order;
import com.product.manager.entity.OrderLine;
import com.product.manager.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderMapper orderMapper;
    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderLineMapper orderLineMapper;
    @Mock
    OrderLineRepository orderLineRepository;
    @Mock
    ProductRepository productRepository;

    OrderService orderService;

    OrderInputDTO orderInputDTO;
    Order order;
    Product product1;
    Product product2;

    @BeforeEach
    void init() {
        orderService = new OrderService(orderMapper, orderLineMapper,
                orderRepository, orderLineRepository, productRepository);

        orderInputDTO = new OrderInputDTO();
        orderInputDTO.setCustomerEmail("test@gmail.com");

        order = new Order();
        order.setId(1L);
        order.setCustomerEmail("test@gmail.com");

        product1 = new Product();
        product1.setId(1L);
        product1.setSku("sk001");
        product1.setDescription("test product 1");
        product1.setPrice(new BigDecimal(5.00));
        product2 = new Product();
        product2.setId(2L);
        product2.setSku("sk002");
        product2.setDescription("test product 2");
        product2.setPrice(new BigDecimal(10.00));
    }

    //TODO: creation date???
    @Test
    void testCreateOrder() {
        List<OrderLineInputDTO> orderLineInputDTOList = new ArrayList<>();
        OrderLineInputDTO orderLineInputDTO1 = new OrderLineInputDTO();
        orderLineInputDTO1.setProductId(1L);
        orderLineInputDTO1.setQuantity(2);
        OrderLineInputDTO orderLineInputDTO2 = new OrderLineInputDTO();
        orderLineInputDTO2.setProductId(2L);
        orderLineInputDTO2.setQuantity(1);
        orderLineInputDTOList.add(orderLineInputDTO1);
        orderLineInputDTOList.add(orderLineInputDTO2);
        orderInputDTO.setOrderLines(orderLineInputDTOList);

        when(orderMapper.mapToOrder(orderInputDTO)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);

        OrderLine orderLine1 = new OrderLine();
        orderLine1.setId(1L);
        orderLine1.setQuantity(2);
        OrderLine orderLine2 = new OrderLine();
        orderLine2.setId(2L);
        orderLine2.setQuantity(1);

        when(orderLineMapper.mapToOrderLine(orderLineInputDTO1)).thenReturn(orderLine1);
        when(orderLineMapper.mapToOrderLine(orderLineInputDTO2)).thenReturn(orderLine2);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));

        Order orderOutput = new Order();
        orderOutput.setId(1L);
        orderOutput.setCustomerEmail("test@gmail.com");
        orderOutput.setOrderValue(new BigDecimal(20.00));

        Order createdOrder = orderService.create(orderInputDTO);
        assertNotNull(createdOrder);
        assertEquals(1L, createdOrder.getId());
        assertEquals("test@gmail.com", createdOrder.getCustomerEmail());
        assertEquals(new BigDecimal(20.00), createdOrder.getOrderValue());
    }

    @Test
    void testCreateOrderProductDoesntExist() {
        when(orderMapper.mapToOrder(orderInputDTO)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);

        OrderLineInputDTO orderLineInputDTO1 = new OrderLineInputDTO();
        orderLineInputDTO1.setProductId(3L);
        orderLineInputDTO1.setQuantity(2);
        orderInputDTO.setOrderLines(Arrays.asList(orderLineInputDTO1));

        when(productRepository.findById(3L)).thenThrow(new ProductNotFoundException("product does not exist"));

        assertThrows(ProductNotFoundException.class, () ->  orderService.create(orderInputDTO));
    }

    @Test
    void testSearchOrders() {
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(1L);
        order1.setCustomerEmail("test@gmail.com");
        order1.setOrderValue(new BigDecimal(20.00));
        Order order2 = new Order();
        order2.setId(2L);
        order2.setCustomerEmail("testttt@gmail.com");
        order2.setOrderValue(new BigDecimal(5.00));
        orderList.add(order1);
        orderList.add(order2);

        when(orderRepository.findAllByCreationDateBetween(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(orderList);

        List<OrderLine> orderLines1 = new ArrayList<>();
        OrderLine orderLine11 = new OrderLine();
        orderLine11.setId(1L);
        orderLine11.setProduct(product1);
        orderLine11.setQuantity(2);
        orderLine11.setOrderLineValue(new BigDecimal(10.00));
        orderLine11.setOrder(order1);
        OrderLine orderLine12 = new OrderLine();
        orderLine12.setId(2L);
        orderLine12.setProduct(product2);
        orderLine12.setQuantity(1);
        orderLine12.setOrderLineValue(new BigDecimal(10.00));
        orderLine12.setOrder(order1);
        orderLines1.add(orderLine11);
        orderLines1.add(orderLine12);

        when(orderLineRepository.findAllByOrderId(1L)).thenReturn(orderLines1);

        List<OrderLine> orderLines2 = new ArrayList<>();
        OrderLine orderLine21 = new OrderLine();
        orderLine21.setId(3L);
        orderLine21.setProduct(product1);
        orderLine21.setQuantity(1);
        orderLine21.setOrderLineValue(new BigDecimal(5.00));
        orderLine21.setOrder(order2);
        orderLines2.add(orderLine21);

        when(orderLineRepository.findAllByOrderId(2L)).thenReturn(orderLines2);

        ProductLineOutputDTO productLineOutputDTO1 = new ProductLineOutputDTO();
        productLineOutputDTO1.setId(1L);
        productLineOutputDTO1.setSku("sk001");
        productLineOutputDTO1.setDescription("test product 1");
        ProductLineOutputDTO productLineOutputDTO2 = new ProductLineOutputDTO();
        productLineOutputDTO1.setId(2L);
        productLineOutputDTO1.setSku("sk002");
        productLineOutputDTO1.setDescription("test product 2");

        OrderLineOutputDTO orderLineOutputDTO11 = new OrderLineOutputDTO();
        orderLineOutputDTO11.setId(1L);
        orderLineOutputDTO11.setProduct(productLineOutputDTO1);
        orderLineOutputDTO11.setQuantity(2);
        orderLineOutputDTO11.setOrderLineValue(new BigDecimal(10.00));
        OrderLineOutputDTO orderLineOutputDTO12 = new OrderLineOutputDTO();
        orderLineOutputDTO12.setId(2L);
        orderLineOutputDTO12.setProduct(productLineOutputDTO2);
        orderLineOutputDTO12.setQuantity(1);
        orderLineOutputDTO12.setOrderLineValue(new BigDecimal(10.00));

        when(orderLineMapper.mapToOrderLineOutputDTO(orderLine11)).thenReturn(orderLineOutputDTO11);
        when(orderLineMapper.mapToOrderLineOutputDTO(orderLine12)).thenReturn(orderLineOutputDTO12);

        OrderLineOutputDTO orderLineOutputDTO21 = new OrderLineOutputDTO();
        orderLineOutputDTO21.setId(1L);
        orderLineOutputDTO21.setProduct(productLineOutputDTO1);
        orderLineOutputDTO21.setQuantity(1);
        orderLineOutputDTO21.setOrderLineValue(new BigDecimal(5.00));

        when(orderLineMapper.mapToOrderLineOutputDTO(orderLine21)).thenReturn(orderLineOutputDTO21);

        OrderOutputDTO orderOutputDTO1 = new OrderOutputDTO();
        orderOutputDTO1.setId(1L);
        orderOutputDTO1.setCustomerEmail("test@gmail.com");
        orderOutputDTO1.setOrderValue(new BigDecimal(20.00));
        OrderOutputDTO orderOutputDTO2 = new OrderOutputDTO();
        orderOutputDTO2.setId(2L);
        orderOutputDTO2.setCustomerEmail("testttt@gmail.com");
        orderOutputDTO2.setOrderValue(new BigDecimal(5.00));

        when(orderMapper.mapToOrderOutputDTO(order1)).thenReturn(orderOutputDTO1);
        when(orderMapper.mapToOrderOutputDTO(order2)).thenReturn(orderOutputDTO2);

        List<OrderOutputDTO> orderOutputDTOList = orderService.search(
                LocalDateTime.of(2020, 2, 1, 0, 0),
                LocalDateTime.of(2020, 2, 29, 0, 0));

        assertEquals(2, orderOutputDTOList.size());
        assertEquals("test@gmail.com", orderOutputDTOList.get(0).getCustomerEmail());
        assertEquals(orderOutputDTOList.get(0).getOrderValue(), new BigDecimal(20.00));
        assertEquals(2, orderOutputDTOList.get(0).getOrderLines().size());
        assertEquals("testttt@gmail.com", orderOutputDTOList.get(1).getCustomerEmail());
        assertEquals(orderOutputDTOList.get(1).getOrderValue(), new BigDecimal(5.00));
        assertEquals(1, orderOutputDTOList.get(1).getOrderLines().size());
    }
}
