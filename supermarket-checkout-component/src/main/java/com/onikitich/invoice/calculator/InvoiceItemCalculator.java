package com.onikitich.invoice.calculator;

import java.util.concurrent.atomic.AtomicInteger;

import com.onikitich.domain.Product;
import com.onikitich.domain.SpecialOffer;
import com.onikitich.invoice.InvoiceItem;
import org.springframework.stereotype.Component;

@Component
public class InvoiceItemCalculator {

    public InvoiceItem calculate(Product product, AtomicInteger itemsCounter) {
        int itemsCount = itemsCounter.get();
        double fullPrice = product.getPrice() * itemsCount;

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setProductName(product.getName());
        invoiceItem.setCount(itemsCount);

        SpecialOffer specialOffer = product.getSpecialOffer();
        if (specialOffer == null) {
            invoiceItem.setPrice(fullPrice);
            return invoiceItem;
        }

        double specialOfferPrice = (itemsCount / specialOffer.getItemsAmount()) * specialOffer.getSpecialPrice();
        double normalPrice = (itemsCount % specialOffer.getItemsAmount()) * product.getPrice();
        double totalPrice = specialOfferPrice + normalPrice;

        invoiceItem.setPrice(totalPrice);
        invoiceItem.setDiscount(fullPrice - totalPrice);
        return invoiceItem;
    }
}
