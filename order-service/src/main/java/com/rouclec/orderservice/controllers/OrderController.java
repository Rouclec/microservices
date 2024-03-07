package com.rouclec.orderservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rouclec.orderservice.mappers.impl.OrderMapper;
import com.rouclec.orderservice.domains.dto.OrderDto;
import com.rouclec.orderservice.domains.entity.Order;
import com.rouclec.orderservice.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody @Valid OrderDto orderRequest) throws JsonProcessingException {
        Order order = orderMapper.mapFrom(orderRequest);
        return orderMapper.mapTo(orderService.create(order));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getAllOrders(){
        List<Order> products = orderService.getAll();
        return products.stream()
                .map(orderMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrder(@PathVariable("id") Long id){
        return orderMapper.mapTo(orderService.get(id));
    }
}
