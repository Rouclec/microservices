package com.rouclec.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rouclec.productservice.TestDataUtil;
import com.rouclec.productservice.domains.dto.ProductDto;
import com.rouclec.productservice.domains.entity.Product;
import com.rouclec.productservice.services.ProductService;
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
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testThatCreateProductReturns400AndErrorMessageIfRequestBodyIsInvalid() throws Exception{
        ProductDto productDto = TestDataUtil.createTestProductDtoA();
        productDto.setId(null);
        productDto.setName(null);

        String productJson = objectMapper.writeValueAsString(productDto);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.message").value("Product name is missing")
        );
    }


    @Test
    public void testThatCreateProductReturnsHttp201AndCreatedProduct() throws Exception {
        ProductDto productDto = TestDataUtil.createTestProductDtoA();
        productDto.setId(null);
        String productJson = objectMapper.writeValueAsString(productDto);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson)
        ).andExpect(
                        MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("IPhone 13 mini")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.description").value("The smallest 5G model with a high working speed and top battery life.")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.price").value(474.04)
        );
    }


    @Test
    public void testThatGetAllProductsReturnsHttp200AndListOfProducts() throws Exception{
        Product productA = TestDataUtil.createTestProductA();
        productService.create(productA);
        Product productB = TestDataUtil.createTestProductB();
        productService.create(productB);
        Product productC = TestDataUtil.createTestProductC();
        productService.create(productC);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/products")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.length()").value(3)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[2].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].description").value("The smallest 5G model with a high working speed and top battery life.")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].name").value("Macbook pro 2022")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[2].price").value(274.99)
        );
    }

    @Test
    public void testThatGetProductReturns404AndErrorIfProductNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1234555"))
                .andExpect(
                        MockMvcResultMatchers.status().isNotFound()
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.message").value("Value not found")
                );
    }


    @Test
    public void testThatGetProductReturn200AndProductIfProductExists() throws Exception {
        Product productA = TestDataUtil.createTestProductA();
        productService.create(productA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/products/1")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("IPhone 13 mini")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.description").value("The smallest 5G model with a high working speed and top battery life.")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.price").value(474.04)
        );
    }

}
