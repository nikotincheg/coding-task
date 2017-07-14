package com.onikitich.predicate;

import java.util.function.Predicate;

public enum NewCommandPredicate implements Predicate<String> {
    INST;

    @Override
    public boolean test(String command) {
        return "NEW".equalsIgnoreCase(command.trim());
    }
}
