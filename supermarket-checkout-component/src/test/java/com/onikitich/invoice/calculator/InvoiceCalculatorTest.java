package com.onikitich.invoice.calculator;

import java.util.concurrent.atomic.AtomicInteger;

import com.onikitich.domain.Product;
import com.onikitich.invoice.Invoice;
import com.onikitich.invoice.InvoiceItem;
import com.onikitich.purchase.ShoppingCart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceCalculatorTest {

    @Mock
    private InvoiceItemCalculator invoiceItemCalculator;
    @Mock
    private ShoppingCart shoppingCart;

    private InvoiceCalculator invoiceCalculator;

    @Before
    public void init() {
        invoiceCalculator = new InvoiceCalculator(invoiceItemCalculator);
    }

    @Test
    public void whenCalculateForEmptyShoppingCartThenEmptyInvoiceShouldBeCalculated() {
        when(shoppingCart.getPurchasesMap()).thenReturn(emptyMap());

        Invoice invoice = invoiceCalculator.calculate(shoppingCart);
        assertNotNull(invoice.getInvoiceNumber());
        assertNotNull(invoice.getDateTime());
        assertTrue(invoice.getItemList().isEmpty());
        assertThat(invoice.getTotalPrice(), is(0d));
        verifyZeroInteractions(invoiceItemCalculator);
    }

    @Test
    public void whenCalculateForShoppingCartWithProductsThenFilledInvoiceShouldBeCalculated() {
        double productPrice = 100d;
        Product product = new Product();
        AtomicInteger itemsCounter = new AtomicInteger();

        InvoiceItem invoiceItem = mock(InvoiceItem.class);
        when(invoiceItem.getPrice()).thenReturn(productPrice);
        when(shoppingCart.getPurchasesMap()).thenReturn(singletonMap(product, itemsCounter));
        when(invoiceItemCalculator.calculate(product, itemsCounter)).thenReturn(invoiceItem);

        Invoice invoice = invoiceCalculator.calculate(shoppingCart);
        assertNotNull(invoice.getInvoiceNumber());
        assertNotNull(invoice.getDateTime());
        assertThat(invoice.getItemList().size(), is(1));
        assertThat(invoice.getItemList().get(0), sameInstance(invoiceItem));
        assertThat(invoice.getTotalPrice(), is(productPrice));

        verify(invoiceItemCalculator).calculate(product, itemsCounter);
        verify(invoiceItem).getPrice();
        verifyNoMoreInteractions(invoiceItemCalculator, invoiceItem);
    }
}
