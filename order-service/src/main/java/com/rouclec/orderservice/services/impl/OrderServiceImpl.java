package com.rouclec.orderservice.services.impl;

import com.rouclec.orderservice.domains.dto.InventoryResponse;
import com.rouclec.orderservice.domains.entity.Order;
import com.rouclec.orderservice.repositories.OrderRepository;
import com.rouclec.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private  OrderRepository orderRepository;

    @Autowired
    private WebClient webClient;

    @Override
    public Order create(Order order) {
       String skuCodes =  String.join(",", order.getOrderLineItems().stream().map(orderLineItem -> orderLineItem.getSkuCode()).toList());
       InventoryResponse[] inventoryResponses =  webClient.get()
                .uri("http://localhost:8082/api/inventories/" + skuCodes)
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
       boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if(allProductsInStock){
            return orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order get(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }
}
