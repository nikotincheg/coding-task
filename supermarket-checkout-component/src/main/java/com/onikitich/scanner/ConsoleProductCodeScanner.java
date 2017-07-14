package com.onikitich.scanner;

import com.onikitich.console.ConsoleInputLineReader;
import com.onikitich.console.ConsoleOutputMessageWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.onikitich.scanner.ScannerType.CONSOLE;

@Component
@ScannerQualifier(scannerType = CONSOLE)
@RequiredArgsConstructor
public class ConsoleProductCodeScanner implements ProductCodeScanner {

    private final ConsoleInputLineReader consoleInputLineReader;
    private final ConsoleOutputMessageWriter consoleOutputMessageWriter;

    public String scanCode() {
        consoleOutputMessageWriter.writeInfoMessage("Scanning...");
        return consoleInputLineReader.readLine();
    }
}
