package com.rouclec.productservice.mappers.impl;

import com.rouclec.productservice.domains.dto.ProductDto;
import com.rouclec.productservice.domains.entity.Product;
import com.rouclec.productservice.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto mapTo(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public Product mapFrom(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
