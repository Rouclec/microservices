package com.rouclec.orderservice.domains.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineItemDto {
    private Long id;
    @NotNull(message = "Enter a SKU code")
    private String skuCode;
    @NotNull(message = "Enter the price")
    private Double price;
    @NotNull(message = "Enter the quantity")
    private Integer quantity;
}
