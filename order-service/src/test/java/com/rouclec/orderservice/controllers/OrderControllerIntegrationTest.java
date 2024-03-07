package com.rouclec.orderservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rouclec.orderservice.TestDataUtil;
import com.rouclec.orderservice.domains.dto.OrderDto;
import com.rouclec.orderservice.domains.entity.Order;
import com.rouclec.orderservice.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testThatCreateOrderReturns400AndErrorMessageIfRequestBodyIsInvalid() throws Exception{
        OrderDto orderDto = TestDataUtil.createTestOrderDtoA();
        orderDto.setId(null);
        orderDto.setOrderLineItems(null);

        String orderJson = objectMapper.writeValueAsString(orderDto);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value("Provide the list of order items")
        );
    }


    @Test
    public void testThatCreateOrderReturnsHttp201AndCreatedOrder() throws Exception {
        OrderDto orderDto = TestDataUtil.createTestOrderDtoA();
        orderDto.setId(null);
        String orderJson = objectMapper.writeValueAsString(orderDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson)
        ).andExpect(
                        MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.orderLineItems.length()").value(2)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.orderNumber").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.orderLineItems[0].price").value(10.0)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.orderLineItems[1].quantity").value(3)
        );
    }


    @Test
    public void testThatGetAllOrdersReturnsHttp200AndListOfOrders() throws Exception{
        Order orderA = TestDataUtil.createTestOrderA();
        orderService.create(orderA);
        Order orderB = TestDataUtil.createTestOrderB();
        orderService.create(orderB);
        Order orderC = TestDataUtil.createTestOrderC();
        orderService.create(orderC);


        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/orders")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.length()").value(3)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].orderNumber").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].orderLineItems[0].price").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[2].orderLineItems[1].skuCode").isString()
        );
    }

    @Test
    public void testThatGetOrderReturns404AndErrorIfOrderNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/1234555"))
                .andExpect(
                        MockMvcResultMatchers.status().isNotFound()
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.message").value("Value not found")
                );
    }


    @Test
    public void testThatGetOrderReturn200AndOrderIfOrderNotExists() throws Exception {
        Order orderA = TestDataUtil.createTestOrderA();
        orderService.create(orderA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/orders/1")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.orderNumber").isString()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.orderLineItems[0].skuCode").value("SKU1")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.orderLineItems[1].quantity").value(3)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.orderLineItems[1].price").value(20)
        );
    }

}
