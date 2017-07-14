package com.onikitich.predicate;

import java.util.function.Predicate;

public enum CompleteCommandPredicate implements Predicate<String> {
    INST;

    @Override
    public boolean test(String command) {
        return "COMPLETE".equalsIgnoreCase(command.trim());
    }
}
