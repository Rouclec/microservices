package com.rouclec.orderservice.domains.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class InventoryResponse {
    private Long id;
    private String skuCode;
    private Integer quantity;

    public Boolean isInStock(){
        return quantity > 0;
    }
}
