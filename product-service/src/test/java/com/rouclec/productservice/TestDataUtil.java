package com.rouclec.productservice;

import com.rouclec.productservice.domains.dto.ProductDto;
import com.rouclec.productservice.domains.entity.Product;

public class TestDataUtil {

    private TestDataUtil(){
    }

    public static ProductDto createTestProductDtoA(){
        return ProductDto.builder()
                .id(1L)
                .name("IPhone 13 mini")
                .description("The smallest 5G model with a high working speed and top battery life.")
                .price(474.04)
                .build();
    }

    public static Product createTestProductA(){
        return Product.builder()
                .id(1L)
                .name("IPhone 13 mini")
                .description("The smallest 5G model with a high working speed and top battery life.")
                .price(474.04)
                .build();
    }

    public static Product createTestProductB(){
        return Product.builder()
                .id(2L)
                .name("Macbook pro 2022")
                .description("The MacBook Pro with M3, M3 Pro and M3 Max chips. Up to 22 hours battery life.")
                .price(1479.00)
                .build();
    }

    public static Product createTestProductC(){
        return Product.builder()
                .id(3L)
                .name("Xbox series S")
                .description("The Xbox Series S is an impressive console that delivers the ultimate next-generation gaming experience.")
                .price(274.99)
                .build();
    }
}
