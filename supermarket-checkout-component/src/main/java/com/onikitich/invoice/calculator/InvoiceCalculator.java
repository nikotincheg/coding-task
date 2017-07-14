package com.onikitich.invoice.calculator;

import com.onikitich.ShoppingCart;
import com.onikitich.invoice.Invoice;
import com.onikitich.invoice.InvoiceItem;
import com.onikitich.invoice.InvoiceNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.time.LocalDateTime.now;

@Component
@RequiredArgsConstructor
public class InvoiceCalculator {

    private final InvoiceItemCalculator invoiceItemCalculator;

    public Invoice calculate(ShoppingCart shoppingCart) {
        Invoice invoice = new Invoice();
        invoice.setDateTime(now());
        invoice.setInvoiceNumber(InvoiceNumberGenerator.INST.generate());
        invoice.setTotalPrice(0);

        shoppingCart.getPurchasesMap().forEach((product, itemsCounter) -> {
            InvoiceItem invoiceItem = invoiceItemCalculator.calculate(product, itemsCounter);
            invoice.addItem(invoiceItem);
            invoice.setTotalPrice(invoice.getTotalPrice() + invoiceItem.getPrice());
        });
        return invoice;
    }
}
