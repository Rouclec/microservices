package com.rouclec.productservice.domains.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double price;
}
