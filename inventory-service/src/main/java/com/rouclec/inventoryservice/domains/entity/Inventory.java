package com.rouclec.inventoryservice.domains.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Inventory {

    @Id
    @GeneratedValue
    private Long id;
    private String skuCode;
    private Integer quantity;
}
