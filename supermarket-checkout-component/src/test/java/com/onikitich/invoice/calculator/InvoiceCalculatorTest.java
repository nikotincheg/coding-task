package com.onikitich.invoice.calculator;

import com.onikitich.invoice.Invoice;
import com.onikitich.purchase.ShoppingCart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Collections.emptyMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
    public void whenCalculateForEmptyShoppingCartThenEmptyInvoiceShouldBeCreated() {
        when(shoppingCart.getPurchasesMap()).thenReturn(emptyMap());

        Invoice invoice = invoiceCalculator.calculate(shoppingCart);
        assertNotNull(invoice);
        assertNotNull(invoice.getInvoiceNumber());
        assertNotNull(invoice.getDateTime());
        assertTrue(invoice.getItemList().isEmpty());
        assertThat(invoice.getTotalPrice(), is(0d));
    }
}
