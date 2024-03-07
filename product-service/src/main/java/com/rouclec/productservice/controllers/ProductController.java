package com.rouclec.productservice.controllers;

import com.rouclec.productservice.domains.dto.ProductDto;
import com.rouclec.productservice.domains.entity.Product;
import com.rouclec.productservice.mappers.impl.ProductMapper;
import com.rouclec.productservice.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProdct(@RequestBody @Valid ProductDto productRequest){
        Product product = productMapper.mapFrom(productRequest);
        return productMapper.mapTo(productService.create(product));
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAllProducts(){
        List<Product> products = productService.getAll();
        return products.stream()
                .map(productMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProduct(@PathVariable("id") Long id){
        return productMapper.mapTo(productService.get(id));
    }
}
