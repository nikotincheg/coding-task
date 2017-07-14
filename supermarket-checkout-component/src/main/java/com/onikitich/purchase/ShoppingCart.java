package com.onikitich.purchase;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.onikitich.domain.Product;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class ShoppingCart {

    @Getter
    private Map<Product, AtomicInteger> purchasesMap = new LinkedHashMap<>();

    public void addProduct(Product product) {
        AtomicInteger itemsCounter = purchasesMap.get(product);
        if (itemsCounter == null) {
            itemsCounter = new AtomicInteger(1);
            purchasesMap.put(product, itemsCounter);
        } else {
            itemsCounter.getAndIncrement();
        }
    }
}
