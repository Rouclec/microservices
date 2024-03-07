package com.rouclec.orderservice.services;

import com.rouclec.orderservice.domains.entity.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    List<Order> getAll();

    Order get(Long id);
}
