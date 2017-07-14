package com.onikitich.invoice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Invoice {

    @Setter
    private LocalDateTime dateTime;
    @Setter
    private int invoiceNumber;
    @Setter
    private double totalPrice;

    private List<InvoiceItem> itemList = new ArrayList<>();

    public void addItem(InvoiceItem item) {
        itemList.add(item);
    }
}
