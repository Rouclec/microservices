package com.rouclec.inventoryservice.services.impl;

import com.rouclec.inventoryservice.domains.dto.InventoryDto;
import com.rouclec.inventoryservice.mappers.impl.InventoryMapper;
import com.rouclec.inventoryservice.repositories.InventoryRepository;
import com.rouclec.inventoryservice.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    InventoryMapper inventoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryDto> isInStock(String skuCodes) {
        List<String> skuCodesList = Arrays.asList(skuCodes.split(","));
        return inventoryRepository.findBySkuCodeIn(skuCodesList).stream().map(inventoryMapper::mapTo).toList();
    }

}
