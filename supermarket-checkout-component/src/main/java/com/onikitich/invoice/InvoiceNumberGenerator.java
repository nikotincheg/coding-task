package com.onikitich.invoice;

import java.util.Random;

public enum InvoiceNumberGenerator {
    INST;

    private final int LOWER_RANGE = 0;
    private final int UPPER_RANGE = Integer.MAX_VALUE;

    private final Random random = new Random();

    public int generate() {
        return LOWER_RANGE + (int)(random.nextDouble() * (UPPER_RANGE - LOWER_RANGE));
    }
}
