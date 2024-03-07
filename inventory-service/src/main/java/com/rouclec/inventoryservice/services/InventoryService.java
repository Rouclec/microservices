package com.rouclec.inventoryservice.services;

import com.rouclec.inventoryservice.domains.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    List<InventoryDto> isInStock(String skuCodes);
}
