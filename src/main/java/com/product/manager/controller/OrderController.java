package com.product.manager.controller;

import com.product.manager.dto.OrderInputDTO;
import com.product.manager.dto.OrderOutputDTO;
import com.product.manager.entity.Order;
import com.product.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody @Valid OrderInputDTO orderInputDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(orderInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<OrderOutputDTO>> search(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.search(fromDate, toDate));
    }
}
