package com.rouclec.inventoryservice.controllers;

import com.rouclec.inventoryservice.services.InventoryService;
import com.rouclec.inventoryservice.domains.dto.InventoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;


    @GetMapping("/{sku-codes}")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryDto> isInStock(@PathVariable("sku-codes") String skuCodes){
        return inventoryService.isInStock(skuCodes);
    }
}
