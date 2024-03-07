package com.rouclec.inventoryservice.domains.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InventoryDto {
    private Long id;
    @NotNull(message = "Provide the sku code")
    private String skuCode;
    @NotNull(message = "Enter the quantity of the product")
    private Integer quantity;
}
