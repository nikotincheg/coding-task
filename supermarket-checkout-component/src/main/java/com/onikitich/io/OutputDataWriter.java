package com.onikitich.io;

public interface OutputDataWriter {
    void writeInfoMessage(String message);
    void writeErrorMessage(String errorMessage);
    void writeFormattedMessage(String format, Object... args);
    void writeFormattedErrorMessage(String format, Object... args);
}
