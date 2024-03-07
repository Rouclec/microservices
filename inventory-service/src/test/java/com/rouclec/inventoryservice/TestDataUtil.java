package com.rouclec.inventoryservice;

import com.rouclec.inventoryservice.domains.dto.InventoryDto;
import com.rouclec.inventoryservice.domains.entity.Inventory;

public class TestDataUtil {

    private TestDataUtil(){
    }

    public static InventoryDto createTestInventoryDtoA(){
        return InventoryDto.builder()
                .id(1L)
                .skuCode("SKU1")
                .quantity(5)
                .build();
    }

    public static Inventory createTestInventoryA(){
        return Inventory.builder()
                .id(1L)
                .skuCode("SKU1")
                .quantity(5)
                .build();
    }

    public static Inventory createTestInventoryB(){
        return Inventory.builder()
                .id(2L)
                .skuCode("SKU2")
                .quantity(7)
                .build();
    }

    public static Inventory createTestInventoryC(){
        return Inventory.builder()
                .id(3L)
                .skuCode("SKU3")
                .quantity(10)
                .build();
    }
}
