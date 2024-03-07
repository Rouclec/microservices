package com.rouclec.inventoryservice.mappers.impl;

import com.rouclec.inventoryservice.domains.entity.Inventory;
import com.rouclec.inventoryservice.mappers.Mapper;
import com.rouclec.inventoryservice.domains.dto.InventoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper implements Mapper<Inventory, InventoryDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InventoryDto mapTo(Inventory inventory) {
        return modelMapper.map(inventory, InventoryDto.class);
    }

    @Override
    public Inventory mapFrom(InventoryDto inventoryDto) {
        return modelMapper.map(inventoryDto, Inventory.class);
    }
}
