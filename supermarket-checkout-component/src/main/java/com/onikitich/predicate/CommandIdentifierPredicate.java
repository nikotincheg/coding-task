package com.onikitich.predicate;

import java.util.function.Predicate;

public enum CommandIdentifierPredicate implements Predicate<String> {
    COMPLETE("complete"),
    NEW("new"),
    EXIT("exit"),
    EMPTY("");

    private String commandIdentifier;

    CommandIdentifierPredicate(String commandIdentifier) {
        this.commandIdentifier = commandIdentifier;
    }

    @Override
    public boolean test(String commandIdentifier) {
        return commandIdentifier != null && this.commandIdentifier.equalsIgnoreCase(commandIdentifier.trim());
    }
}