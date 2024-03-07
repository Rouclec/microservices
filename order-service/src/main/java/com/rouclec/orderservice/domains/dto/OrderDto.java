package com.rouclec.orderservice.domains.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderDto {
    private Long id;
    private String orderNumber;
    @NotNull(message = "Provide the list of order items")
    private List<OrderLineItemDto> orderLineItems;
}
