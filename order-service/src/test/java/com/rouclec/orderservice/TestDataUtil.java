package com.rouclec.orderservice;

import com.rouclec.orderservice.domains.dto.OrderDto;
import com.rouclec.orderservice.domains.dto.OrderLineItemDto;
import com.rouclec.orderservice.domains.entity.Order;
import com.rouclec.orderservice.domains.entity.OrderLineItem;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestDataUtil {

    private TestDataUtil(){
    }

    public static OrderDto createTestOrderDtoA(){
        OrderLineItemDto orderLineItem1 = OrderLineItemDto.builder()
                .skuCode("SKU1")
                .price(10.0)
                .quantity(2)
                .build();

        OrderLineItemDto orderLineItem2 = OrderLineItemDto.builder()
                .skuCode("SKU2")
                .price(20.0)
                .quantity(3)
                .build();

        List<OrderLineItemDto> orderLineItems = Arrays.asList(orderLineItem1, orderLineItem2);

        return OrderDto.builder()
                .id(1L)
                .orderLineItems(orderLineItems)
                .build();
    }

    public static Order createTestOrderA(){
        OrderLineItem orderLineItem1 = OrderLineItem.builder()
                .skuCode("SKU1")
                .price(10.0)
                .quantity(2)
                .build();

        OrderLineItem orderLineItem2 = OrderLineItem.builder()
                .skuCode("SKU2")
                .price(20.0)
                .quantity(3)
                .build();

        List<OrderLineItem> orderLineItems = Arrays.asList(orderLineItem1, orderLineItem2);

        return Order.builder()
                .id(1L)
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderLineItems)
                .build();
    }

    public static Order createTestOrderB(){
        OrderLineItem orderLineItem1 = OrderLineItem.builder()
                .skuCode("SKU3")
                .price(15.0)
                .quantity(4)
                .build();

        OrderLineItem orderLineItem2 = OrderLineItem.builder()
                .skuCode("SKU4")
                .price(25.0)
                .quantity(5)
                .build();

        List<OrderLineItem> orderLineItems = Arrays.asList(orderLineItem1, orderLineItem2);

        return Order.builder()
                .id(2L)
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderLineItems)
                .build();
    }

    public static Order createTestOrderC(){
        OrderLineItem orderLineItem1 = OrderLineItem.builder()
                .skuCode("SKU5")
                .price(35.0)
                .quantity(6)
                .build();

        OrderLineItem orderLineItem2 = OrderLineItem.builder()
                .skuCode("SKU6")
                .price(29.0)
                .quantity(7)
                .build();

        List<OrderLineItem> orderLineItems = Arrays.asList(orderLineItem1, orderLineItem2);

        return Order.builder()
                .id(3L)
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderLineItems)
                .build();
    }
}
