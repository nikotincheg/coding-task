package com.onikitich.invoice.calculator;

import java.util.concurrent.atomic.AtomicInteger;

import com.onikitich.domain.Product;
import com.onikitich.domain.SpecialOffer;
import com.onikitich.invoice.InvoiceItem;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InvoiceItemCalculatorTest {

    private InvoiceItemCalculator calculator = new InvoiceItemCalculator();

    @Test
    public void whenCalculateForProductWithoutSpecialOfferThenNormalPriceShouldBeUsed() {
        Product product = new Product();
        product.setName("Test Name");
        product.setPrice(100d);

        AtomicInteger itemsCounter = new AtomicInteger(2);

        InvoiceItem invoiceItem = calculator.calculate(product, itemsCounter);
        assertThat(invoiceItem.getProductName(), is(product.getName()));
        assertThat(invoiceItem.getCount(), is(itemsCounter.get()));

        double price = itemsCounter.get() * product.getPrice();
        assertThat(invoiceItem.getPrice(), is(price));
        assertThat(invoiceItem.getDiscount(), is(0d));
    }

    @Test
    public void whenCalculateForProductWithSpecialOfferWithInsufficientItemsAmountThenNormalPriceShouldBeUsed() {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setItemsAmount(3);
        specialOffer.setSpecialPrice(200d);

        Product product = new Product();
        product.setName("Test Name");
        product.setPrice(100d);
        product.setSpecialOffer(specialOffer);

        AtomicInteger itemsCounter = new AtomicInteger(2);

        InvoiceItem invoiceItem = calculator.calculate(product, itemsCounter);
        assertThat(invoiceItem.getProductName(), is(product.getName()));
        assertThat(invoiceItem.getCount(), is(itemsCounter.get()));

        double price = itemsCounter.get() * product.getPrice();
        assertThat(invoiceItem.getPrice(), is(price));
        assertThat(invoiceItem.getDiscount(), is(0d));
    }

    @Test
    public void whenCalculateForProductWithSpecialOfferWithSufficientItemsAmountThenDiscountedPriceShouldBeCalculated() {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setItemsAmount(2);
        specialOffer.setSpecialPrice(150d);

        Product product = new Product();
        product.setName("Test Name");
        product.setPrice(100d);
        product.setSpecialOffer(specialOffer);

        AtomicInteger itemsCounter = new AtomicInteger(3);

        InvoiceItem invoiceItem = calculator.calculate(product, itemsCounter);
        assertThat(invoiceItem.getProductName(), is(product.getName()));
        assertThat(invoiceItem.getCount(), is(itemsCounter.get()));

        double fullPrice = product.getPrice() * itemsCounter.get();
        double discountedPrice = product.getPrice() + specialOffer.getSpecialPrice();
        double discount = fullPrice - discountedPrice;

        assertThat(invoiceItem.getPrice(), is(discountedPrice));
        assertThat(invoiceItem.getDiscount(), is(discount));
    }
}
