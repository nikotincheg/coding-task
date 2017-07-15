package com.onikitich.service;

import com.onikitich.domain.Product;

public interface ProductService {

    Product getProductByCode(String productCode);
}
