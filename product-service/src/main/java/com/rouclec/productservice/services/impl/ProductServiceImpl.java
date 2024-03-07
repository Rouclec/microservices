package com.rouclec.productservice.services.impl;

import com.rouclec.productservice.domains.entity.Product;
import com.rouclec.productservice.repositories.ProductRepository;
import com.rouclec.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}
