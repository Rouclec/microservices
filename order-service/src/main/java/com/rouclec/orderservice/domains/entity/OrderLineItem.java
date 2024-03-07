package com.rouclec.orderservice.domains.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "order_line_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineItem {
    @Id
    @GeneratedValue
    private Long id;
    private String skuCode;
    private Double price;
    private Integer quantity;
}
