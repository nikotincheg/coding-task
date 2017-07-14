package com.onikitich.predicate;

import java.util.function.Predicate;

public enum ExitCommandPredicate implements Predicate<String> {
    INST;

    @Override
    public boolean test(String command) {
        return "EXIT".equalsIgnoreCase(command.trim());
    }
}
