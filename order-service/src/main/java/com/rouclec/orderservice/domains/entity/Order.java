package com.rouclec.orderservice.domains.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "orderdb")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItem> orderLineItems;

    @PrePersist
    private void generateOrderNumber() {
        this.orderNumber = UUID.randomUUID().toString();
    }}
