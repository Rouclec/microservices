package com.rouclec.orderservice.mappers.impl;

import com.rouclec.orderservice.domains.dto.OrderDto;
import com.rouclec.orderservice.domains.entity.Order;
import com.rouclec.orderservice.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements Mapper<Order, OrderDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDto mapTo(Order product) {
        return modelMapper.map(product, OrderDto.class);
    }

    @Override
    public Order mapFrom(OrderDto productDto) {
        return modelMapper.map(productDto, Order.class);
    }
}
