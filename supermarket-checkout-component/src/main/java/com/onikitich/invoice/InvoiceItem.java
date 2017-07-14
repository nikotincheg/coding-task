package com.onikitich.invoice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceItem {

    private String productName;
    private int count = 0;
    private Double price = 0d;
    private Double discount = 0d;
}
