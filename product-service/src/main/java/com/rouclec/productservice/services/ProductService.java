package com.rouclec.productservice.services;

import com.rouclec.productservice.domains.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product);

    List<Product> getAll();

    Product get(Long id);
}
