package com.onikitich.io;

import org.springframework.stereotype.Component;

@Component
public class ConsoleOutputDataWriter implements OutputDataWriter {

    public void writeInfoMessage(String message) {
        System.out.println(message);
    }

    public void writeErrorMessage(String errorMessage) {
        System.err.println(errorMessage);
    }

    public void writeFormattedMessage(String format, Object... args) {
        System.out.printf(format, args);
    }

    public void writeFormattedErrorMessage(String format, Object... args) {
        System.err.printf(format, args);
    }
}
