package com.onikitich.predicate;

import java.util.function.Predicate;

import org.springframework.util.StringUtils;

public enum EmptyInputDataPredicate implements Predicate<String> {
    INST;

    @Override
    public boolean test(String str) {
        return StringUtils.isEmpty(str);
    }
}
