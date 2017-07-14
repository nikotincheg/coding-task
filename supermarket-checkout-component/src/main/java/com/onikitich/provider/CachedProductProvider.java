package com.onikitich.provider;

import com.onikitich.domain.Product;
import com.onikitich.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import static com.onikitich.config.CacheNames.PRODUCTS;

@Component
@RequiredArgsConstructor
public class CachedProductProvider {

    private final ProductMapper productMapper;

    @Cacheable(PRODUCTS)
    public Product getProductByCode(String productCode) {
        return productMapper.getProductByCode(productCode);
    }
}
