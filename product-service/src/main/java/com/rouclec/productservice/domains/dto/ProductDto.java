package com.rouclec.productservice.domains.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDto {
    private Long id;
    @NotNull(message= "Product name is missing")
    private String name;
    @NotNull(message= "Enter product description")
    private String description;
    @NotNull(message = "Provide a price for the product")
    private Double price;
}
