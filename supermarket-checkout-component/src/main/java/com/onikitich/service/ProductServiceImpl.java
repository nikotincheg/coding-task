package com.onikitich.service;

import com.onikitich.domain.Product;
import com.onikitich.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.onikitich.config.CacheNames.PRODUCTS;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Cacheable(PRODUCTS)
    public Product getProductByCode(String productCode) {
        return productMapper.getProductByCode(productCode);
    }
}
